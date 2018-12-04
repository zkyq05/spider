package com.zkyq.spider;

import com.zkyq.spider.modle.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository
public class NewsPipeline implements Pipeline {
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().contains("news")) {
                News news=(News) entry.getValue();
                if (newsRepository.findByLink(news.getLink())== null) {//检查链接是否已存在
                    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    news.setCreateDate(df.format(new java.util.Date()));
//                    news.setModifyDate(df.format(new java.util.Date()));

                    System.err.println(news.getTitle()+":"+news.getLink());

                    newsRepository.save(news);
                }else {
                    System.err.println("已经有该数据...");
                }
            }
        }
    }
}
