package com.zkyq.spider;

import com.zkyq.spider.modle.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> getNewsList() {
        return newsRepository.findAll();
    }

    public Optional<News> findNewsById(long id) {
        return newsRepository.findById(id);
    }

    public void save(News News) {
        newsRepository.save(News);
    }

    public void edit(News News) {
        newsRepository.save(News);
    }

    public void delete(long id) {
        newsRepository.deleteById(id);
    }
}


