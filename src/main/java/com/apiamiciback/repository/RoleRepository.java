package com.apiamiciback.repository;

import com.apiamiciback.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
