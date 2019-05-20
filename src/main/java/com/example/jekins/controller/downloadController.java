package com.example.jekins.controller;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
public class downloadController {

    @GetMapping("/download/pattern")
    public void downloadPattern(HttpServletRequest request, HttpServletResponse response){
        System.out.println("下载文件.....");

//        ClassPathResource resource = new ClassPathResource("\\static\\pattern\\test.xlsx");
        try {
//            InputStream in = resource.getInputStream();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("\\static\\pattern\\test.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            downFile("test",request,response,workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/download/pattern2")
    public void down(HttpServletRequest request, HttpServletResponse response){
        templateDownload(response);
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




    public void templateDownload(HttpServletResponse response) {
        try {
            InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream("\\static\\pattern\\test.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            response.setContentType("application/binary;charset=ISO8859-1");
            String fileName = java.net.URLEncoder.encode("template", "UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
            ServletOutputStream out = null;
            out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件输出流

        }
        return;
    }

}
