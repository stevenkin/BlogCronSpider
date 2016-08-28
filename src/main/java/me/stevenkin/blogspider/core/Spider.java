package me.stevenkin.blogspider.core;

import me.stevenkin.blogspider.bean.Link;
import me.stevenkin.blogspider.bean.Result;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Spider implements Runnable {
    private volatile boolean isStop = false;

    private CloseableHttpClient httpClient;

    private RequestConfig requestConfig;

    private List<AbstractPageParser> parserList;

    private PipeLine pipeLine;

    private LinkQueue linkQueue;

    private Map<String,PageParser> map = new HashMap<>();

    public void init(){
        System.out.println("spider init");
        for(AbstractPageParser parser:parserList){
            map.put(parser.getWebSite(),parser);
            System.out.println(parser.getWebSite()+" "+parser);
        }
    }

    @Override
    public void run() {
        while(!this.isStop){
            Link link = this.linkQueue.getLink();
            if(link==null)
                continue;
            if(link.isSkip()){
                continue;
            }
            String linkStr = link.getLink();
            int mon = 0;
            int httpIndex = linkStr.indexOf("http://");
            mon = 7;
            if(httpIndex<0) {
                httpIndex = linkStr.indexOf("https://");
                mon = 8;
            }
            String linkStr1 = httpIndex>=0?linkStr.substring(httpIndex+mon):linkStr;
            int index = linkStr1.indexOf("/");
            String linkStr2 = index>=0?linkStr1.substring(0,index):linkStr1;
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

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public RequestConfig getRequestConfig() {
        return requestConfig;
    }

    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }

    public List<AbstractPageParser> getParserList() {
        return parserList;
    }

    public void setParserList(List<AbstractPageParser> parserList) {
        this.parserList = parserList;
    }

    public PipeLine getPipeLine() {
        return pipeLine;
    }

    public void setPipeLine(PipeLine pipeLine) {
        this.pipeLine = pipeLine;
    }

    public LinkQueue getLinkQueue() {
        return linkQueue;
    }

    public void setLinkQueue(LinkQueue linkQueue) {
        this.linkQueue = linkQueue;
    }
}
