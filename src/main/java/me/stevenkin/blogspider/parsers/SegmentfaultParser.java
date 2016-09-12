package me.stevenkin.blogspider.spiders.Segmentfault.parser;

import me.stevenkin.blogspider.enity.Blog;
import me.stevenkin.blogspider.bean.Link;
import me.stevenkin.blogspider.bean.Result;
import me.stevenkin.blogspider.core.AbstractPageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2016/8/28.
 */
public class SegmentfaultParser extends AbstractPageParser {

    private static final String BASIC_URL = "https://segmentfault.com";

    @Override
    public Result parserHtml(String html) {
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
            result.addContent(blog);
        }
        Element nextElem = document.select("li.next a").first();
        if(nextElem!=null) {
            String nextLink = BASIC_URL+nextElem.attr("href");
            result.addLink(new Link(nextLink,false));
        }
        return result;
    }
}
