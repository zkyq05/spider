package com.zkyq.spider;

import com.zkyq.spider.modle.News;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class JianShuProcessor implements PageProcessor {
    private Site site = Site.me()
            .setDomain("jianshu.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    ;
    public static final String list = "http://www.jianshu.com";
    @Override
    public void process(Page page) {
        if (page.getUrl().regex(list).match()) {
//            System.err.println("page.getHtml():"+page.getHtml().xpath("//ul[@class='note-list']/li"));
            List<Selectable> list=page.getHtml().xpath("//ul[@class='note-list']/li").nodes();
//            System.err.println("list:"+list);
            for (Selectable s : list) {
                String title=s.xpath("//div/a/text()").toString();
                String link=s.xpath("//div/a").links().toString();
                String info=s.xpath("//div/p/text()").toString();
                String author=s.xpath("//div/div/a/text()").toString();
                System.err.println("title:"+title);
                System.err.println("link:"+link);
                System.err.println("info:"+info);
                System.err.println("author:"+author);

                News news=new News();
                news.setTitle(title);
                news.setInfo(info);
                news.setLink(link);
                news.setAuthor(author);
//                news.setSources(new Sources(5));
                page.putField("news"+title, news);
            }
        }
    }
    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args) {
        Spider spider=Spider.create(new JianShuProcessor());
        spider.addUrl("http://www.jianshu.com");
        spider.addPipeline(new NewsPipeline());
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
    }
}
