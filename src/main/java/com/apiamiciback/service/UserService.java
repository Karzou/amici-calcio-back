package com.apiamiciback.service;

import com.apiamiciback.dto.UserRequestDto;
import com.apiamiciback.exception.NotFoundException;
import com.apiamiciback.model.Role;
import com.apiamiciback.model.User;
import com.apiamiciback.repository.RoleRepository;
import com.apiamiciback.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * The type User service.
 */
@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    @Autowired
    PositionService positionService;

    /**
     * The Role repository.
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * The Password encoder.
     */
    @Autowired
    PasswordEncoder passwordEncoder;


    /**
     * Save role to user.
     *
     * @param role the role
     * @param user the user
     */
    public void saveRoleToUser (Role role, User user) {

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = getUser(username);
        if(user == null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User found in database : {} ", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getDescription()));
        log.info("Check ==== {}", authorities + " || " + user.getEmail() + " || " + user.getPassword());

        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities);

        log.info(userDetails.getUsername() + " || " + userDetails.getPassword() + " || " + userDetails.getAuthorities().toString()); ;

        return userDetails;

    }

    /**
     * Save user user.
     *
     * @param user the user
     * @return the user
     */
    public User saveUser (UserRequestDto user) {

        log.info("Saving new user {} in database ", user.getFirstName());
        if(userRepository.findByEmail(user.getEmail()) == null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User userDB = new User();
            userDB.setPassword(user.getPassword());
            userDB.setLastName(user.getLastName());
            userDB.setEmail(user.getEmail());
            userDB.setFirstName(user.getFirstName());
            if(user.getBirthdate() != null){
                userDB.setBirthdate(user.getBirthdate());
            }
            if(user.getDescription() != null){
                userDB.setDescription(user.getDescription());
            }
            if(user.getPhone() != null){
                userDB.setPhone(user.getPhone());
            }
           /* (user.getPosition() != null){


                userDB.getPosition().setPosition();
            }*/
            if(user.getStreet() != null){
                userDB.setStreet(user.getStreet());
            }
            if(user.getNumber() != null){
                userDB.setNumber(user.getNumber());
            }
            log.info("User {} saved in database.", userDB.getEmail());
            return userRepository.save(userDB);
        }else {
            log.error("User {} already exist in database.", user.getEmail());
            return null;
        }

    }

    /**
     * Save role role.
     *
     * @param role the role
     * @return the role
     */
    public Role saveRole (Role role) {

        log.info("Saving new role {} in database ", role.getDescription());
        return roleRepository.save(role);
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public User getUser (String username) {

        log.info("Searching username {} in database ", username);

        User user = userRepository.findByEmail(username);

        return user;
    }
    public User getUserById (int id) {

        log.info("Searching username {} in database ", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User Not found in database : " + id ));


        return user;
    }
    /**
     * Gets all users.
     *
     * @return the all users
     */
    public List<User> getAllUsers () {

        log.info("Searching all users in database");

        return userRepository.findAll();
    }

    public User updateUser (int id, UserRequestDto userRequestDto){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User Not found in database : " + id + " " + userRequestDto.getEmail()));

        user.setLastName(userRequestDto.getLastName());
        user.setFirstName(user.getFirstName());
        user.setNumber(userRequestDto.getNumber());
        user.setStreet(userRequestDto.getStreet());
        user.setPhone(userRequestDto.getPhone());
        user.setDescription(user.getDescription());
        user.setBirthdate(user.getBirthdate());
        user.setLastUpdate(new Date(System.currentTimeMillis()));

        log.info("User : [} updated in databas", user.getEmail());

        return userRepository.save(user);
    }
}
