package me.stevenkin.blogspider.core;

/**
 * Created by Administrator on 2016/8/27.
 */
public abstract class AbstractPageParser implements PageParser {
    private String webSite;

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
