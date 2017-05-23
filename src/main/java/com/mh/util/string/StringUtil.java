package com.mh.util.string;

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

    public static void main(String[] args) {
        String str = capitalize("aaaaa");
        System.out.println(str);
    }
}
