package me.stevenkin.blogspider.pipeline;

import me.stevenkin.blogspider.bean.Result;
import me.stevenkin.blogspider.core.AbstractPipeLine;
import me.stevenkin.blogspider.dao.BlogMapper;
import me.stevenkin.blogspider.enity.Blog;

/**
 * Created by wjg on 2016/9/17.
 */
public class BlogDBPipeLine extends AbstractPipeLine {
    private BlogMapper blogMapper;

    @Override
    public boolean checkProcess(Result result) {
        return true;
    }

    @Override
    public void process(Result result) {
        for(Object object:result.getContentList())
            blogMapper.insertBlog((Blog) object);
    }

    public BlogMapper getBlogMapper() {
        return blogMapper;
    }

    public void setBlogMapper(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }
}
