package me.stevenkin.blogspider.core;

import me.stevenkin.blogspider.bean.Result;

/**
 * Created by Administrator on 2016/8/27.
 */
public interface PipeLine {
    public boolean checkProcess(Result result);
    public void processResult(Result result);
}
