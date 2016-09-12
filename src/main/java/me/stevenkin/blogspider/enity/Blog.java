package me.stevenkin.blogspider.enity;

/**
 * Created by Administrator on 2016/8/23.
 */
public class Blog {
    private String title;
    private String resume;
    private String link;
    private String site;
    private String tags;

    public Blog() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", link='" + link + '\'' +
                ", site='" + site + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
