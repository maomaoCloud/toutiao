package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.SysHelp;
import org.joda.time.DateTime;

public class ApiHelpData {

    private String helpId;

    private String helpTitle;

    private String helpColor;

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
    }

    public String getHelpTitle() {
        return helpTitle;
    }

    public void setHelpTitle(String helpTitle) {
        this.helpTitle = helpTitle;
    }

    public String getHelpColor() {
        return helpColor;
    }

    public void setHelpColor(String helpColor) {
        this.helpColor = helpColor;
    }

    public static ApiHelpData genFromHelp(SysHelp help) {
        ApiHelpData data = new ApiHelpData();
        data.setHelpId(help.getHelpId());
        data.setHelpTitle(help.getHelpTitle());
        data.setHelpColor(help.getHelpColor());
        return data;
    }
}
