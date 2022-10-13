package com.apiamiciback.controller;

import com.apiamiciback.dto.UserRequestDto;
import com.apiamiciback.model.User;
import com.apiamiciback.service.FileStorageService;
import com.apiamiciback.service.RoleService;
import com.apiamiciback.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type User controller.
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    /**
     * The Role service.
     */
    @Autowired
    RoleService roleService;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * The Storage service.
     */
    @Autowired
    FileStorageService storageService;

    /**
     * Refresh token.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    @GetMapping ("/refreshtoken")
    public void refreshToken (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        log.info("Entry in refreshtoken servlet");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                log.info("Refresh token : {}", refresh_token);
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifer = JWT.require(algorithm).build();
                log.info("Verifer : {}", verifer);
                DecodedJWT decodedJWT = verifer.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole().getDescription()));
                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e) {
                log.error("Error in verify token : {}", e.getMessage());
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> errors = new HashMap<>();
                errors.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errors);
            }
        }else{
            log.error("Refresh token is missing");
            throw new RuntimeException("Refresh token is missing");

        }
    }

    /**
     * Save user response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @PostMapping(value = "/saveuser", consumes = {"application/json"})
    public ResponseEntity<?>saveUser(@RequestBody @Valid UserRequestDto user){
        log.info("Call saveUser POST : {}", user.getEmail() + ' ' + user.getFirstName() + ' ' + user.getLastName());
        if (userService.getUser(user.getEmail()) != null){
            log.error("User {} already exist.", user.getEmail());

            return ResponseEntity.badRequest().body("User already exist");
        } else {
            log.info("User {} saved successfully.", user.getEmail());
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/saveuser").toUriString());
            return ResponseEntity.created(uri).body(userService.saveUser(user));
        }
    }

    /**
     * Get all users response entity.
     *
     * @return the response entity
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>>getAllUsers(){
        log.info("Call get all users.");
        List<User>users = userService.getAllUsers();
        log.info(" {} users will be send to front.", users.size());
        return ResponseEntity.ok().body(users) ;
    }

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        log.info("GetUser : " + id);
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    /**
     * Update user response entity.
     *
     * @param id   the id
     * @param user the user
     * @return the response entity
     */
    @PutMapping(value = "update/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateUser(@PathVariable("id") int id ,@RequestBody @Valid UserRequestDto user){
        log.info("update user : " + user.toString());

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/update").toUriString());
        return ResponseEntity.created(uri).body(userService.updateUser(id, user));
    }

    /**
     * Save img user response entity.
     *
     * @param idUser the id user
     * @param file   the file
     * @return the response entity
     */
    @PostMapping(value = "/saveImg", consumes = {"multipart/form-data"})
    public ResponseEntity<?>saveImgUser(@RequestParam("id") int idUser, @RequestParam("file") MultipartFile file){

        log.info("Call save image user for : {}.", idUser + " "  + file.getOriginalFilename());

        String message = "";
        String url = "";
        try {
            url = storageService.save(file, file.getOriginalFilename());

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            log.info(message + "URL : " + url);

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            log.error(message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/saveImg").toUriString());
        return ResponseEntity.created(uri).body(userService.savImgUser(idUser, url));
    }

    /**
     * Delete bool user response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?>deleteBoolUser (@PathVariable int id){

        log.info("Delete user {}", id);

        userService.deleteUser(id);

        return ResponseEntity.ok().body( id + " User deleted");
    }

}
