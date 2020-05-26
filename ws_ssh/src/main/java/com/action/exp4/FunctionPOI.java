package com.action.exp4;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/CreateExcel")
public class FunctionPOI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HSSFWorkbook wb = new HSSFWorkbook();//创建HSSFWorkbook对象
        HSSFSheet sheet=wb.createSheet("成绩表");//建立sheet对象
        HSSFRow row1=sheet.createRow(0); //在sheet里创建第一行，参数为行索引
        HSSFCell cell=row1.createCell(0); //创建单元格
        cell.setCellValue("学生成绩表");        //设置单元格内容
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("密码");
        row2.createCell(2).setCellValue("最后登陆时间");

        //在sheet里创建第三行
        HSSFRow row3=sheet.createRow(2);
        //创建单元格并设置单元格内容
        row3.createCell(0).setCellValue((String) req.getSession().getAttribute("username"));
        row3.createCell(1).setCellValue((String) req.getSession().getAttribute("password"));
        row3.createCell(2).setCellValue((String) req.getSession().getAttribute("loginTime"));
        OutputStream output=resp.getOutputStream();
        resp.reset();
        resp.setHeader("Content-disposition", "attachment; filename=Student.xls");
        resp.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }
}
