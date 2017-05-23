package com.mh.util.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public class PoiUtil {

    /**
     * 导出Excel
     * @param list 数据列表
     * @param filePathName 文件保存路径
     * @param sheetName sheet页名称
     * @param titles 表头
     * @param filedNames 字段名称
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> File export(List<T> list, String filePathName, String sheetName, String[] titles, String[] filedNames)
            throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = null;
        // 写入sheet名称
        if(sheetName == null){
            sheetName = "sheet1";
        }
        sheet = wb.createSheet(sheetName);
        // 设置表头
        HSSFRow topRow = sheet.createRow(0);
        for(int i = 0; i < titles.length; i++){
            setCellValue(topRow.createCell(i),titles[i]);
        }
        String methodName = "";
        Method method = null;
        T t = null;
        HSSFRow row = null;
        Object obj = null;
        // 遍历list,生成数据行
        for(int i = 0; i < list.size(); i++){
            t = list.get(i);
            row = sheet.createRow(i + 1);
            Class<? extends Object> clazz = t.getClass();
            for(int j = 0; j < filedNames.length; j++){
                methodName = "get" + capitalize(filedNames[j]);
                try{
                    method = clazz.getDeclaredMethod(methodName);
                }catch(NoSuchMethodException e){
                        if(clazz.getSuperclass() != null){
                            method = clazz.getSuperclass().getDeclaredMethod(methodName);
                        }
                }
                obj = method.invoke(t);
                setCellValue(row.createCell(j), (String) obj);
            }
        }
        // 根据指定的文件写入路径
        File file = null;
        OutputStream out = null;
        try{
            file = new File(filePathName);
            out = new FileOutputStream(file);
            wb.write(out);
        }catch(Exception e){
            out.flush();
            out.close();
            throw new Exception("导出Excel失败");
        }
        return null;
    }
    // 设置表头
    public static void setCellValue(Cell cell, String title){
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(title);
    }
    // 首字母大写
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
        List stuList = new ArrayList<Stu>();
        Stu stu0 = new Stu("1","Lucy","23");
        Stu stu1 = new Stu("2","John","11");
        stuList.add(stu0);
        stuList.add(stu1);
        String[] fieldNames = {"id","name","age"};
        try {
            export(stuList,"E:/stu.xls","stu1",fieldNames,fieldNames);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
