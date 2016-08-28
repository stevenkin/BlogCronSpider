package me.stevenkin.blogspider.task;

import me.stevenkin.blogspider.core.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/8/27.
 */
public class CronTask implements ApplicationContextAware{

    private ApplicationContext context;

    private int threadNum;

    private ExecutorService service = Executors.newCachedThreadPool();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void run(){
        List<Spider> spiders = new ArrayList<>();
        for(int i=0;i<threadNum;i++){
            spiders.add((Spider) context.getBean("spider"));
        }
        int siteNum = spiders.get(0).getParserList().size();
        CountDownLatch countDownLatch = new CountDownLatch(siteNum);
        for(Spider spider: spiders){
            spider.countDownLatch(countDownLatch);
        }
        Stoper stoper = new Stoper();
        stoper.setCountDownLatch(countDownLatch);
        stoper.setSpiders(spiders);
        for(Spider spider:spiders){
            service.submit(spider);
        }
        service.submit(stoper);
    }
}
