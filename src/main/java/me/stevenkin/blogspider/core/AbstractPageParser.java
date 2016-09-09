package me.stevenkin.blogspider.core;

import me.stevenkin.blogspider.bean.Response;
import me.stevenkin.blogspider.bean.Result;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/27.
 */
public abstract class AbstractPageParser implements PageParser {
    private String webSiteRegex;

    private PageParser nextParser;

    @Override
    public Result parserPage(Response response){
        Pattern pattern = Pattern.compile(this.webSiteRegex);
        if(pattern.matcher(response.getLink()).find()) {
            return parserHtml(response.getContent());
        }
        return this.nextParser==null?null:this.nextParser.parserPage(response);
    }

    public abstract Result parserHtml(String html);

    public String getWebSiteRegex() {
        return webSiteRegex;
    }

    public void setWebSiteRegex(String webSiteRegex) {
        this.webSiteRegex = webSiteRegex;
    }

    public PageParser getNextParser() {
        return nextParser;
    }

    public void setNextParser(PageParser nextParser) {
        this.nextParser = nextParser;
    }
}
