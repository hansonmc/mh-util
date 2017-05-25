package com.mh.util.uuid;

import com.mh.util.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 生成32位的随机单号
 *
 */
public class UUIDUtils {
    private static Object lock = new Object();

    public UUIDUtils() {
    }


    private static int seq = 0;
    private static final int maxVal = 99999;

    /**
     * UUID
     * 生成格式为 yyMMddHHmmss
     * @return String UUID
     */
    public static String getUUID() {

        synchronized(lock) {
            String date = StringUtils.substring(DateUtil.getCurrentDatetime(), 2);
            seq++;
            if (seq > maxVal) {
                seq = 1;
            }
            return date + StringUtils.leftPad(String.valueOf(seq), 5, '0');
        }

    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
