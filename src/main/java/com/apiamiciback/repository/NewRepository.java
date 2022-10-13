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
    /**
     * Find all by created desc list.
     *
     * @return the list
     */
    @Query("SELECT n FROM News n ORDER BY n.createdAt DESC")
    List<News> findAllByCreatedDesc();

    /**
     * Find by creator news.
     *
     * @param user the user
     * @return the news
     */
    News findByCreator(User user);
}
