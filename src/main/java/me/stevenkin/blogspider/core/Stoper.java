package me.stevenkin.blogspider.core;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/8/27.
 */
public class Stoper implements Runnable {
    private CountDownLatch countDownLatch;

    private List<Spider> spiders;

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Spider spider:spiders){
            spider.stop(true);
        }
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public List<Spider> getSpiders() {
        return spiders;
    }

    public void setSpiders(List<Spider> spiders) {
        this.spiders = spiders;
    }
}
