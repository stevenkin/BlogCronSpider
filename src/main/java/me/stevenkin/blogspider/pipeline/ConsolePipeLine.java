package me.stevenkin.blogspider.pipeline;

import me.stevenkin.blogspider.core.AbstractPipeLine;
import me.stevenkin.blogspider.bean.Result;
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

    @Override
    public boolean checkProcess(Result result) {
        return true;
    }
}
