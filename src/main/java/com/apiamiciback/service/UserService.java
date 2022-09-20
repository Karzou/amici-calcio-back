package com.apiamiciback.service;

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
    public User saveUser (User user) {

        log.info("Saving new user {} in database ", user.getFirstName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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

        log.info("Test password ||| " + user.getPassword());

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
}
