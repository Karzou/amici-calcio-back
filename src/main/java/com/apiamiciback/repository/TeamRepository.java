package com.apiamiciback.repository;

import com.apiamiciback.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    public Team findByTeamName(String teamName);
}
