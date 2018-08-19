package com.mh.util.security.messageDigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Demo {

    public static void main(String[] args) {
        String md5Code = getMD5Code("111");
        System.out.println(md5Code);
        System.out.println(md5Code.toUpperCase());
    }

    public static String getMD5Code(String message){
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(message.getBytes());
            result = bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String bytesToHex(byte[] bytes){
        StringBuilder result = new StringBuilder();
        int temp;
        try{

            for(int i = 0; i < bytes.length; i++){
                temp = bytes[i];
                if(temp < 0){
                    temp += 256;
                }

                if(temp < 16){
                    result.append("0");
                }
                result.append(Integer.toHexString(temp));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }


}
