package com.example.jekins.controller;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
public class downloadController {

    @GetMapping("/download/pattern")
    public void downloadPattern(HttpServletRequest request, HttpServletResponse response){
        System.out.println("下载文件.....");
        ClassPathResource resource = new ClassPathResource("\\static\\pattern\\test.xlsx");
        try {
            FileInputStream in = (FileInputStream) resource.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            downFile("test.xlsx",request,response,workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 下载文件
     * @param fileName 下载文件名称
     * @param response 响应
     * @throws IOException 异常
     */
    public static void downFile(String fileName,HttpServletRequest request,
                                HttpServletResponse response,XSSFWorkbook workbook) throws IOException {
        OutputStream os = null;
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            os = response.getOutputStream();
            response.reset();
//            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//            response.setContentType("application/octet-stream; charset=UTF-8");
            response.setContentType("application/binary;charset=utf-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            System.out.println("fileName=" + fileName);
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
        }
    }
}
