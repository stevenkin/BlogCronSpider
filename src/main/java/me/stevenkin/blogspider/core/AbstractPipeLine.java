package me.stevenkin.blogspider.core;

import me.stevenkin.blogspider.bean.Result;

import java.util.regex.Pattern;

/**
 * Created by wjg on 16-9-8.
 */
public abstract class AbstractPipeLine implements PipeLine {
    private PipeLine nextPipeLine;
    private String linkRegex;
    @Override
    public void processResult(Result result) {
        Pattern pattern = Pattern.compile(this.linkRegex);
        if(pattern.matcher(result.getLink()).find()){
            process(result);
        }
        if(this.nextPipeLine!=null)
            this.nextPipeLine.processResult(result);
    }

    public abstract void process(Result result);

    public PipeLine getNextPipeLine() {
        return nextPipeLine;
    }

    public void setNextPipeLine(PipeLine nextPipeLine) {
        this.nextPipeLine = nextPipeLine;
    }

    public String getLinkRegex() {
        return linkRegex;
    }

    public void setLinkRegex(String linkRegex) {
        this.linkRegex = linkRegex;
    }
}
