package me.stevenkin.blogspider.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class Result {
    private List<Blog> blogList = new LinkedList<>();
    private List<String> linkList = new LinkedList<>();

    public List<Blog> getBlogList() {
        return blogList;
    }

    public List<String> getLinkList() {
        return linkList;
    }

    public void addBlog(Blog blog){
        this.blogList.add(blog);
    }

    public void addLink(String link){
        this.linkList.add(link);
    }
}
