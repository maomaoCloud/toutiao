package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.AdvertRecommend;

public class AdvertRecommendDto extends AdvertRecommend {

    private String advertStartHour;

    private String advertStartMin;

    private String advertEndHour;

    private String advertEndMin;

    public String getAdvertStartHour() {
        return advertStartHour;
    }

    public void setAdvertStartHour(String advertStartHour) {
        this.advertStartHour = advertStartHour;
    }

    public String getAdvertStartMin() {
        return advertStartMin;
    }

    public void setAdvertStartMin(String advertStartMin) {
        this.advertStartMin = advertStartMin;
    }

    public String getAdvertEndHour() {
        return advertEndHour;
    }

    public void setAdvertEndHour(String advertEndHour) {
        this.advertEndHour = advertEndHour;
    }

    public String getAdvertEndMin() {
        return advertEndMin;
    }

    public void setAdvertEndMin(String advertEndMin) {
        this.advertEndMin = advertEndMin;
    }
}
