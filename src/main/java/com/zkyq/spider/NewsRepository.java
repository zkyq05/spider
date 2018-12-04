package com.zkyq.spider;

import com.zkyq.spider.modle.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findByLink(String link);
}
