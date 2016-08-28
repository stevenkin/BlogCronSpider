package me.stevenkin.blogspider.pipeline;

import me.stevenkin.blogspider.bean.Blog;
import me.stevenkin.blogspider.bean.Result;
import me.stevenkin.blogspider.core.PipeLine;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/27.
 */
@Service
public class ConsolePipeLine implements PipeLine {
    @Override
    public void processResult(Result result) {
        for(Blog blog : result.getBlogList()){
            System.out.println(blog);
        }
    }
}
