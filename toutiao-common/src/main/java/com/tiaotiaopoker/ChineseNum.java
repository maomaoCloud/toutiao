package com.tiaotiaopoker;

public enum ChineseNum {
    ONE("一", 1), TWO("二", 2), THREE("三", 3), FOUE("四", 4), FIVE("五", 5), SIX("六", 6), SEVVEN("七", 7), EIGHT("八", 8), NINE("九", 9), TEN("十", 10);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private ChineseNum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getChineseNum(int index) {
        for (ChineseNum num : ChineseNum.values()) {
            if (num.getIndex() == index) {
                return num.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
