package com.apiamiciback.repository;
import com.apiamiciback.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Position repository.
 */
public interface PositionRepository extends JpaRepository<Position, Integer> {

    /**
     * Find by position position.
     *
     * @param position the position
     * @return the position
     */
    Position findByPosition(String position);
}
