package me.stevenkin.blogspider.task;

import me.stevenkin.blogspider.bean.Link;
import me.stevenkin.blogspider.core.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/8/27.
 */
public class CronTask implements ApplicationContextAware{

    private ApplicationContext context;

    private int threadNum;

    private List<String> startLinks;

    private ExecutorService service = Executors.newCachedThreadPool();

    private LinkQueue linkQueue = new LinkQueue();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void init(){
        System.out.println("cron task init");
        Spider spider = (Spider) context.getBean("spider");
        spider.setLinkQueue(linkQueue);
        for(int i=0;i<threadNum;i++){
            service.submit(spider);
        }
    }

    public void run(){
        System.out.println("run");
        for(String link:this.startLinks){
            this.linkQueue.addLink(new Link(link,false));
        }
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public List<String> getStartLinks() {
        return startLinks;
    }

    public void setStartLinks(List<String> startLinks) {
        this.startLinks = startLinks;
    }
}
