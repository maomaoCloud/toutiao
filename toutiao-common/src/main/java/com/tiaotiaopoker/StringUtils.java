package com.tiaotiaopoker;

import org.joda.time.DateTime;

import java.util.Random;
import java.util.UUID;

public class StringUtils {

    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    public static String generateShortUUID () {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i*4, i*4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x%0x3E]);
        }
        return shortBuffer.toString();
    }

    public static boolean isBlank (String target) {
        return org.apache.commons.lang3.StringUtils.isBlank(target);
    }

    public static String gen6Num () {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    public static String generateOrderNo () {
        String dateTimeKey = new DateTime().toString("yyyyMMddHHmmssSSSS");
        String _6RandNum = gen6Num();
        return dateTimeKey + _6RandNum;
    }

    public static String genRandomKey () {
        return new StringBuffer(generateShortUUID()).append(generateShortUUID()).toString();
    }

    public static void main (String[] args) {
        System.out.println(",123".split(",").length);
    }

    public static String arrayToString (String[] arrayData, String splitter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayData.length - 1; i++) {
            sb.append(arrayData[i]);
            sb.append(splitter);
        }

        sb.append(arrayData[arrayData.length - 1]);
        return sb.toString();
    }

}
