package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.SysHelp;
import org.joda.time.DateTime;

public class ApiHelpData {

    private String helpId;

    private String helpTitle;

    private String helpColor;

    private String helpContent;

    private String helpCreateTime;

    public String getHelpId () {
        return helpId;
    }

    public void setHelpId (String helpId) {
        this.helpId = helpId;
    }

    public String getHelpTitle () {
        return helpTitle;
    }

    public void setHelpTitle (String helpTitle) {
        this.helpTitle = helpTitle;
    }

    public String getHelpColor () {
        return helpColor;
    }

    public void setHelpColor (String helpColor) {
        this.helpColor = helpColor;
    }

    public String getHelpContent () {
        return helpContent;
    }

    public void setHelpContent (String helpContent) {
        this.helpContent = helpContent;
    }

    public String getHelpCreateTime () {
        return helpCreateTime;
    }

    public void setHelpCreateTime (String helpCreateTime) {
        this.helpCreateTime = helpCreateTime;
    }

    public static ApiHelpData genFromHelp (SysHelp help) {
        ApiHelpData data = new ApiHelpData();
        data.setHelpId(help.getHelpId());
        data.setHelpTitle(help.getHelpTitle());
        data.setHelpColor(help.getHelpColor());
        return data;
    }

    public static ApiHelpData genDetailDataFromHelp (SysHelp help) {
        ApiHelpData data = genFromHelp(help);
        data.setHelpContent(help.getHelpContent());
        data.setHelpCreateTime(new DateTime(help.getHelpCreateTime()).toString("yyyy-MM-dd"));
        return data;
    }


}
