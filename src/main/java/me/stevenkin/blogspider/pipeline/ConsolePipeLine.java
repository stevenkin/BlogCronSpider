package me.stevenkin.blogspider.pipeline;

import me.stevenkin.blogspider.core.AbstractPipeLine;
import me.stevenkin.blogspider.enity.Blog;
import me.stevenkin.blogspider.bean.Result;
import me.stevenkin.blogspider.core.PipeLine;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/27.
 */
@Service
public class ConsolePipeLine extends AbstractPipeLine {
    @Override
    public void process(Result result) {
        for(Object content : result.getContentList()){
            System.out.println(content);
        }
    }
}
