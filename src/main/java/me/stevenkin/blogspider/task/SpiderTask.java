package me.stevenkin.blogspider.task;


import me.stevenkin.blogspider.bean.Blog;
import me.stevenkin.blogspider.bean.Result;
import me.stevenkin.blogspider.util.HttpClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 2016/8/24.
 */
public class SpiderTask {
    private static final String BASIC_URL = "https://segmentfault.com";
    private Queue<String> linkQueue = new LinkedList<>();
    private CloseableHttpClient httpclient = HttpClientUtil.createHttpsClient();

    public SpiderTask(){
        linkQueue.add("https://segmentfault.com/blogs/newest");
    }
    public void run() throws Exception {
        while(!linkQueue.isEmpty()){
            String link = linkQueue.remove();
            HttpGet get = new HttpGet(link);
            CloseableHttpResponse response = httpclient.execute(get);
            if(response.getStatusLine().getStatusCode()==200){
                String html = EntityUtils.toString(response.getEntity(),EntityUtils.getContentCharSet(response.getEntity()));
                Result result = parse(html);
                for(Blog blog:result.getBlogList()){
                    System.out.println(blog);
                }
                for(String link1:result.getLinkList()){
                    this.linkQueue.add(link1);
                }
            }
        }
    }

    private Result parse(String html){
        Result result = new Result();
        Document document = Jsoup.parse(html);
        Elements sections = document.select("section.stream-list__item");
        for(Element section:sections){
            String title = section.select("a[href]").text();
            String link = BASIC_URL+section.select("a[href").attr("href");
            String resume = section.select("p.excerpt.wordbreak.hidden-xs").text();
            Blog blog = new Blog();
            blog.setLink(link);
            blog.setResume(resume);
            blog.setTitle(title);
            result.addBlog(blog);
        }
        Element nextElem = document.select("li.next a").first();
        if(nextElem!=null) {
            String nextLink = BASIC_URL+nextElem.attr("href");
            result.addLink(nextLink);
        }
        return result;
    }

    public static void main(String[] args){
        try {
            new SpiderTask().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
