package me.stevenkin.blogspider.core;

import me.stevenkin.blogspider.bean.Link;
import me.stevenkin.blogspider.bean.Result;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Spider implements Runnable {
    private volatile boolean isStop = false;
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig requestConfig;
    @Autowired
    private LinkQueue linkQueue;
    @Autowired
    private List<AbstractPageParser> parserList;
    @Autowired
    private PipeLine pipeLine;

    private Map<String,PageParser> map = new HashMap<>();

    private CountDownLatch countDownLatch;

    private Set<String> allWebSites = new HashSet<>();

    @PostConstruct
    public void init(){
        for(AbstractPageParser parser:parserList){
            map.put(parser.getWebSite(),parser);
            allWebSites.add(parser.getWebSite());
        }
    }

    @Override
    public void run() {
        while(!this.isStop){
            Link link = this.linkQueue.getLink();
            if(link==null)
                continue;
            if(link.isEnd()&&allWebSites.remove(link.getLink())){
                this.countDownLatch.countDown();
                continue;
            }
            String linkStr = link.getLink();
            int httpIndex = linkStr.indexOf("http://");
            String linkStr1 = httpIndex>0?linkStr.substring(httpIndex+7):linkStr;
            int index = linkStr1.indexOf("/");
            String linkStr2 = index>0?linkStr1.substring(0,index):linkStr1;
            PageParser parser = map.get(linkStr2);
            HttpGet get = new HttpGet(linkStr);
            get.setConfig(this.requestConfig);
            try {
                HttpResponse response = httpClient.execute(get);
                if(response.getStatusLine().getStatusCode()==200) {
                    String html = EntityUtils.toString(response.getEntity(), EntityUtils.getContentCharSet(response.getEntity()));
                    Result result = parser.parserPage(html);
                    for(Link link1:result.getLinkList()){
                        linkQueue.addLink(link1);
                    }
                    pipeLine.processResult(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    public Spider stop(boolean isStop){
        this.isStop = isStop;
        return this;
    }

    public Spider countDownLatch(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
        return this;
    }

    public boolean isStop() {
        return isStop;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public RequestConfig getRequestConfig() {
        return requestConfig;
    }

    public LinkQueue getLinkQueue() {
        return linkQueue;
    }

    public List<AbstractPageParser> getParserList() {
        return parserList;
    }

    public PipeLine getPipeLine() {
        return pipeLine;
    }

    public Map<String, PageParser> getMap() {
        return map;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public Set<String> getAllWebSites() {
        return allWebSites;
    }
}
