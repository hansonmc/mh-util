package com.mh.util.poi.mypoi;

import com.mh.util.date.DateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * excel工具 (目前只有导出功能)
 * @author mh
 * @Date 2017/8/16
 */
public class ExcelUtil {
    private SXSSFWorkbook wb;
    private Sheet sheet;
    private CellStyle style;

    public Sheet getSheet(){
        return sheet;
    }
    public ExcelUtil(String sheetName, String[] head){
        wb = new SXSSFWorkbook();
        sheet = wb.createSheet(sheetName);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        setHead(head);
    }

    private void setHead(String[] head){
        if(head == null){
            throw new IllegalArgumentException("表头不能为空");
        }
        Row row = sheet.createRow(0);
        Cell cell;
        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
            cell.setCellStyle(style);
        }
    }

    public void insertCell(Row row, int i, Object object) {
        if (object == null) {
            row.createCell(i).setCellValue("");
        } else {
            if (object instanceof Date) {
                row.createCell(i).setCellValue(DateUtil.format((Date)object));
            } else {
                row.createCell(i).setCellValue(object.toString());
            }
        }
    }
    public void doResponse(String excelName, HttpServletResponse response) throws IOException {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            wb.write(out);
            byte[] bytes = out.toByteArray();

            String str = DateUtil.getCurrentDatetime() + excelName + ".xlsx";
            String filename = new String(str.getBytes(), "ISO-8859-1");
            response.setContentType("application/x-msdownload");
            response.setContentLength(bytes.length);
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            response.getOutputStream().write(bytes);
        }finally {
            if(out != null){
                out.close();
            }
        }
    }

    public void save(String excelName, String targetPath){
        if(targetPath == null || excelName == null){
            return;
        }
        OutputStream out = null;
        try{
            out = new FileOutputStream(targetPath + File.separator + excelName + ".xlsx");
            wb.write(out);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
