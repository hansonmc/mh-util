package com.mh.util.io;

import java.io.*;

/**
 * 复制器
 * @author MH
 */
public class Replicator {

    public static void copy(String src, String desc) {
        copy(new File(src), new File(desc));
    }

    public static void copy(File srcFile, File descFile) {
        if (!descFile.exists()) {
            boolean isMkdirs = descFile.mkdirs();
            System.out.println("创建目录[" + descFile + "]:" + (isMkdirs ? "成功" : "失败"));
        }
        if (srcFile.isFile()) {
            String desc = descFile.getAbsolutePath() + File.separator + srcFile.getName();
            write(srcFile, new File(desc));
        } else {
            String desc = descFile.getAbsolutePath() + File.separator + srcFile.getName();
            File descFileInner = new File(desc);
            File[] fileInners = srcFile.listFiles();
            if (fileInners != null && fileInners.length > 0) {
                for (File fileInner : fileInners) {
                    copy(fileInner, descFileInner);
                }
            }else{
                if(!descFileInner.exists()){
                 boolean isMkdirs = descFileInner.mkdirs();
                System.out.println("创建目录[" + descFileInner + "]:" + (isMkdirs ? "成功" : "失败"));
                }
            }
        }
    }

    private static void write(File srcFile, File descFile) {
        InputStream input;
        OutputStream output;
        BufferedInputStream bInput = null;
        BufferedOutputStream bOutput = null;
        try {
            input = new FileInputStream(srcFile);
            output = new FileOutputStream(descFile);
            bInput = new BufferedInputStream(input);
            bOutput = new BufferedOutputStream(output);
            byte[] b = new byte[1024];
            int i;
            while ((i = bInput.read(b)) != -1) {
                bOutput.write(b, 0, i);
            }
            bOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bInput != null) {
                try {
                    bInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bOutput != null) {
                try {
                    bOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args){
        copy(new File("E:\\a_tuling"), new File("E:\\aa\\"));
    }
}