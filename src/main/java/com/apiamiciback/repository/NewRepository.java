package com.apiamiciback.repository;

import com.apiamiciback.model.News;
import com.apiamiciback.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface New repository.
 */
public interface NewRepository extends JpaRepository<News, Integer> {
    @Query("SELECT n FROM News n ORDER BY n.createdAt DESC")
    List<News> findAllByCreatedDesc();

    News findByCreator(User user);
}
