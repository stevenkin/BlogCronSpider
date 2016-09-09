package me.stevenkin.blogspider.enity;

/**
 * Created by Administrator on 2016/8/23.
 */
public class Blog {
    private String title;
    private String resume;
    private String link;
    private String time;

    public Blog(String title, String resume, String link, String time) {
        this.title = title;
        this.resume = resume;
        this.link = link;
        this.time = time;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", link='" + link + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
