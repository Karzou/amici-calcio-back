package com.apiamiciback.repository;

import com.apiamiciback.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface New repository.
 */
public interface NewRepository extends JpaRepository<News, Integer> {

}
