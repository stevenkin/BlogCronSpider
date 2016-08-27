package me.stevenkin.blogspider.bean;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Link {
    private String link;
    private boolean isEnd;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
