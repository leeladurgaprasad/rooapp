package com.prasad.roostack.bean.form;

import com.prasad.roostack.bean.TimeLine;
import com.prasad.roostack.constants.CommonConstants;

import java.util.List;

/**
 * Created by lgunti on 025, Dec 25.
 */
public class TimeLineForm {
    private int numberOfPixelsLoad = CommonConstants.NUMBER_OF_PIXELS_LOAD;
    private List<TimeLine> timeLineList;
    private int nextPixelFrom;

    public List<TimeLine> getTimeLineList() {
        return timeLineList;
    }

    public void setTimeLineList(List<TimeLine> timeLineList) {
        this.timeLineList = timeLineList;
    }

    public int getNextPixelFrom() {
        return nextPixelFrom;
    }

    public void setNextPixelFrom(int nextPixelFrom) {
        this.nextPixelFrom = nextPixelFrom;
    }

    public int getNumberOfPixelsLoad() {
        return numberOfPixelsLoad;
    }

    public void setNumberOfPixelsLoad(int numberOfPixelsLoad) {
        this.numberOfPixelsLoad = numberOfPixelsLoad;
    }
}
