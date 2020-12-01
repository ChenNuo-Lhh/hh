package com.baizhi.hh.test;


import com.baizhi.hh.poi.PoiImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
public class TestPoi {

    @Autowired
    private PoiImpl poi;

    @Test
    public void PioImport() {
        File file = new File("F:\\testpoi.xls");
        poi.PoiImport(file, "学生信息表1");
    }

    @Test
    public void PoiExport() {
        File file = new File("F:\\testpoi.xls");
        poi.PoiExport(file, "学生信息表1");
    }

    @Test
    public void Test() {

        //创建一个Excel文档
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作表   参数:工作表表明  默认:sheet1,sheet2....
        Sheet sheet1 = workbook.createSheet("学生信息表1");
        Sheet sheet2 = workbook.createSheet("学生信息表2");

        //创建一行   参数:行下标(从0开始)
        Row row = sheet1.createRow(5);

        //创建一个单元格  参数:单元格下标(从0开始)
        Cell cell = row.createCell(2);

        //给单元格设置数据
        cell.setCellValue("这是第六行,第3个单元格");

        try {
            //导出
            workbook.write(new FileOutputStream(new File("F:\\testpoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
