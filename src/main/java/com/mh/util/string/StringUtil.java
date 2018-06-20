package com.mh.util.string;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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

    /**
     * 中国 转换为 \xe4\xb8\xad\xe5\x9b\xbd
     * @param str 常规字符
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String str2Hex(String str) throws UnsupportedEncodingException {
        String hexRaw = String.format("%x", new BigInteger(1, str.getBytes("UTF-8")));
        char[] hexRawArr = hexRaw.toCharArray();
        StringBuilder hexFmtStr = new StringBuilder();
        final String SEP = "\\x";
        for (int i = 0; i < hexRawArr.length; i++) {
            hexFmtStr.append(SEP).append(hexRawArr[i]).append(hexRawArr[++i]);
        }
        return hexFmtStr.toString();
    }

    /**
     * \xe4\xb8\xad\xe5\x9b\xbd 转换为 中国
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String hex2Str(String str) throws UnsupportedEncodingException {
        String strArr[] = str.split("\\\\"); // 分割拿到形如 xE9 的16进制数据
        byte[] byteArr = new byte[strArr.length - 1];
        for (int i = 1; i < strArr.length; i++) {
            Integer hexInt = Integer.decode("0" + strArr[i]);
            byteArr[i - 1] = hexInt.byteValue();
        }

        return new String(byteArr, "UTF-8");
    }

    public static void main(String[] args) {
        String str = capitalize("aaaaa");
        System.out.println(str);
    }
}
