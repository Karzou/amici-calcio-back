package com.apiamiciback.repository;

import com.apiamiciback.model.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamUserRepository extends JpaRepository<TeamUser, Integer> {
}
