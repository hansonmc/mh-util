package com.mh.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/23.
 */
public class StringUtil {

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String capitalize(final String str){
        int strLen;
        if(str == null || (strLen = str.length()) == 0){
            return str;
        }

        final char firstChar = str.charAt(0);
        final char newChar = Character.toTitleCase(firstChar);
        if(firstChar == newChar){
            return str;
        }

        char[] newChars = new char[strLen];
        newChars[0] = newChar;
        str.getChars(1, strLen, newChars, 1);
        return String.valueOf(newChars);
    }

    /**
     * 去除空格和换行符的字符串
     * @param str
     * @return
     */
    public static String getStringNoBlank(String str) {
        if (str != null && !"".equals(str)) {
            Pattern p = Pattern.compile("\\s|\t|\r|\n");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll("");
            return strNoBlank;
        } else {
            return str;
        }
    }

    public static void main(String[] args) {
        String str = capitalize("aaaaa");
        System.out.println(str);
    }
}
