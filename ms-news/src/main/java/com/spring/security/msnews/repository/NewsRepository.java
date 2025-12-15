package com.spring.security.msnews.repository;

import com.spring.security.msnews.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByPublishedAtAfter(LocalDateTime since);
}
