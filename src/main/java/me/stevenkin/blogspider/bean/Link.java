package me.stevenkin.blogspider.bean;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Link {
    private String link;
    private boolean isSkip;

    public Link(String link, boolean isSkip) {
        this.link = link;
        this.isSkip = isSkip;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isSkip() {
        return isSkip;
    }

    public void setIsSkip(boolean isSkip) {
        isSkip = isSkip;
    }
}
