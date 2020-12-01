package com.baizhi.hh.poi;

import com.baizhi.hh.entity.Video;
import com.baizhi.hh.service.VideoService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class PoiImpl implements Poi {

    private static final Logger log = LoggerFactory.getLogger(PoiImpl.class);
    @Resource
    private VideoService videoService;

    /**
     * 导入
     * 参数:
     * file : 导入表的路径
     * sheet : 导入表的工作名
     */
    @Override
    public void PoiImport(File file, String Sheet) {
        log.debug("进入导入方法");
        List<Video> list = new ArrayList<>();

        try {
//        创建Excel文档
            Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
//            获取Sheet
            Sheet sheet = workbook.getSheet(Sheet);
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
//                 获取行
                Row row = sheet.getRow(i);
//                获取单元格
                Cell cell = row.getCell(0);
//                获取单元格内容
                String id = cell.getStringCellValue();

                String title = row.getCell(1).getStringCellValue();
                String brief = row.getCell(2).getStringCellValue();
                String coverPath = row.getCell(3).getStringCellValue();
                String videoPath = row.getCell(4).getStringCellValue();
                Date uploadTime = row.getCell(5).getDateCellValue();
                String categoryId = row.getCell(6).getStringCellValue();
                String userId = row.getCell(7).getStringCellValue();
                String groupId = row.getCell(8).getStringCellValue();
                int status = (int) row.getCell(9).getNumericCellValue();

                Video video = new Video(id, title, brief, coverPath, videoPath, uploadTime, categoryId, userId, groupId, status);

                list.add(video);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(a -> System.out.println(a));

    }

    /**
     * 导出
     * 参数:
     * file : 导出表的路径
     * sheet : 导出表的工作名
     */
    @Override
    public void PoiExport(File file, String Sheet) {
        log.debug("进入导出方法");
        List<Video> list = videoService.findAll(0, 0);
        list.forEach(a -> System.out.println(a));
//        创建一个Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
//        创建一个工作表
        HSSFSheet workbookSheet = workbook.createSheet(Sheet);
//        设置列宽  参数:列索引,列宽  单位 1/256
        workbookSheet.setColumnWidth(0, 35 * 256);
//        创建合并对象单元格 参数  开始行,结束行,开始列,结束列
        CellRangeAddress addresses = new CellRangeAddress(0, 0, 0, 9);
//        合并单元格
        workbookSheet.addMergedRegion(addresses);
//        创建字体对象
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.GREEN.getIndex());
        font.setUnderline(FontFormatting.U_SINGLE);//下划线
//         创建单元格样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(font);
//        创建标题行
        HSSFRow r = workbookSheet.createRow(0);
//        创建标题单元格
        Cell c = r.createCell(0);
//        设置单元格样式
        c.setCellStyle(cellStyle);
//        单元格数据
        c.setCellValue("视频表数据");

//        目录行
        String[] titles = {"id", "标题", "简介", "封面路径", "视频连接", "时间", "类别", "用户id", "组别", "状态"};
//        创建一行
        HSSFRow row = workbookSheet.createRow(1);
//        处理表头
        for (int i = 0; i < titles.length; i++) {
//            创建数据
            Cell cell = row.createCell(i);
//            设置数据
            cell.setCellValue(titles[i]);
        }
//        处理数据
        for (int i = 0; i < list.size(); i++) {
//            创建一行
            Row rows = workbookSheet.createRow(i + 2);
//            创建单元格   设置数据
            rows.createCell(0).setCellValue(list.get(i).getId());
            rows.createCell(1).setCellValue(list.get(i).getTitle());
            rows.createCell(2).setCellValue(list.get(i).getBrief());
            rows.createCell(3).setCellValue(list.get(i).getCoverPath());
            rows.createCell(4).setCellValue(list.get(i).getVideoPath());
            rows.createCell(5).setCellValue(list.get(i).getUploadTime());
            rows.createCell(6).setCellValue(list.get(i).getCategoryId());
            rows.createCell(7).setCellValue(list.get(i).getUserId());
            rows.createCell(8).setCellValue(list.get(i).getGroupId());
            rows.createCell(9).setCellValue(list.get(i).getStatus());

//            创建单元格
            Cell cell = rows.createCell(3);
//            设置数据
//            cell.setCellValue(list.get(i).);

        }

//        导出
        try {
            workbook.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
