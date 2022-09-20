package com.apiamiciback.repository;

import com.apiamiciback.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Team repository.
 */
public interface TeamRepository extends JpaRepository<Team, Integer> {

    /**
     * Find by team name team.
     *
     * @param teamName the team name
     * @return the team
     */
    public Team findByTeamName(String teamName);
}
