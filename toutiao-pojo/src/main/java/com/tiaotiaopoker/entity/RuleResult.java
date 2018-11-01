package com.tiaotiaopoker.entity;

public class RuleResult {

    private String ruleEnglish;

    private String ruleChinese;

    public String getRuleEnglish() {
        return ruleEnglish;
    }

    public void setRuleEnglish(String ruleEnglish) {
        this.ruleEnglish = ruleEnglish;
    }

    public String getRuleChinese() {
        return ruleChinese;
    }

    public void setRuleChinese(String ruleChinese) {
        this.ruleChinese = ruleChinese;
    }

    public RuleResult(String ruleEnglish, String ruleChinese) {
        this.ruleEnglish = ruleEnglish;
        this.ruleChinese = ruleChinese;
    }
}
