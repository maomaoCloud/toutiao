package com.tiaotiaopoker.entity;

import java.util.List;

public class DoubleList<T> {
    private List<T> mainList;
    private List<T> subList;

    public List<T> getMainList () {
        return mainList;
    }

    public void setMainList (List<T> mainList) {
        this.mainList = mainList;
    }

    public List<T> getSubList () {
        return subList;
    }

    public void setSubList (List<T> subList) {
        this.subList = subList;
    }

}
