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
        mkdirs(descFile);
        if (srcFile.isFile()) {
            String desc = descFile.getAbsolutePath() + File.separator + srcFile.getName();
            // 写内容
            write(srcFile, new File(desc));
        } else {
            String desc = descFile.getAbsolutePath() + File.separator + srcFile.getName();
            File descFileInner = new File(desc);
            File[] fileInners = srcFile.listFiles();
            if (fileInners != null && fileInners.length > 0) {
                for (File fileInner : fileInners) {
                    // 递归调用创建文件夹或写内容
                    copy(fileInner, descFileInner);
                }
            }else{
                // 空文件夹也创建
                mkdirs(descFileInner);
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

    private static void mkdirs(File file){
        if (!file.exists()) {
            boolean isMkdirs = file.mkdirs();
            System.out.println("创建目录[" + file + "]:" + (isMkdirs ? "成功" : "失败"));
        }
    }

    public static void main(String[] args){
        copy(new File("E:\\a_tuling"), new File("E:\\aa\\"));
    }
}