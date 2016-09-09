package me.stevenkin.blogspider.core;

import me.stevenkin.blogspider.bean.Response;
import me.stevenkin.blogspider.bean.Result;

/**
 * Created by Administrator on 2016/8/27.
 */
public interface PageParser {
    public Result parserPage(Response response);
}
