package com.apiamiciback.repository;

import com.apiamiciback.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface File data repository.
 */
public interface FileDataRepository extends JpaRepository<FileData, Integer> {

    /**
     * Find by name optional.
     *
     * @param filename the filename
     * @return the optional
     */
    Optional<FileData> findByName(String filename);
}
