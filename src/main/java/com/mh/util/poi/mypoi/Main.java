package com.mh.util.poi.mypoi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Stu> list = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            Stu stu = new Stu(i+"", "李" + i, i+"", new Date());
            list.add(stu);
        }
        try {
            ExcelExport.export(null, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ExcelExport{
        // 表名与字段
        public static final String EXCEL_ACTIVITY_NAME = "学生";
        public static final String[] HEAD_ACTIVITY_NAME = {"id", "姓名", "年龄", "生日"};

        public static void export(HttpServletResponse response, List<Stu> list) throws IOException {
            String excelName = ExcelExport.EXCEL_ACTIVITY_NAME;
            String[] head = ExcelExport.HEAD_ACTIVITY_NAME;
            ExcelUtil excelUtil = new ExcelUtil(excelName, head);
            ExcelExport.insert(excelUtil,list);
            // web中使用
//            excelUtil.doResponse(excelName,response);
            excelUtil.save(excelName, "c://aa");
        }

        private static void insert(ExcelUtil excelUtil, List<Stu> list){
            Stu voBean;
            Sheet sheet = excelUtil.getSheet();
            Row row;
            for(int i = 0; i < list.size(); i++){
                row = sheet.createRow(i + 1);
                voBean = list.get(i);
                int j = 0;
                excelUtil.insertCell(row, j++, voBean.getId());
                excelUtil.insertCell(row, j++, voBean.getName());
                excelUtil.insertCell(row, j++, voBean.getAge());
                excelUtil.insertCell(row, j++, voBean.getBirthday());
            }
        }
    }
}
