package me.stevenkin.blogspider.core;

import me.stevenkin.blogspider.bean.Response;
import me.stevenkin.blogspider.bean.Result;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/27.
 */
public abstract class AbstractPageParser implements PageParser {

    private PageParser nextParser;

    @Override
    public Result parserPage(Response response){
        if(checkParser(response)) {
            return parserHtml(response.getContent());
        }
        return this.nextParser==null?null:this.nextParser.parserPage(response);
    }

    public abstract Result parserHtml(String html);

    public PageParser getNextParser() {
        return nextParser;
    }

    public void setNextParser(PageParser nextParser) {
        this.nextParser = nextParser;
    }
}
