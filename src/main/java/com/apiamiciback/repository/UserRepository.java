package com.apiamiciback.repository;

import com.apiamiciback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find by email user.
     *
     * @param email the email
     * @return the user
     */
    User findByEmail (String email);
}
