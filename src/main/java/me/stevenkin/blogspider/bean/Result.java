package me.stevenkin.blogspider.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class Result {
    private String link;
    private List contentList = new LinkedList<>();
    private List<Link> linkList = new LinkedList<>();

    public List getContentList() {
        return contentList;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void addContent(Object object){
        this.contentList.add(object);
    }

    public void addLink(Link link){
        this.linkList.add(link);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
