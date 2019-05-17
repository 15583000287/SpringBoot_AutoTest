package com.example.jekins.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        ClassPathResource resource = new ClassPathResource("\\static\\pattern\\模板.txt");
        try {
            InputStream in = resource.getInputStream();
            downFile("模板.txt",request,response,in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 下载文件
     *
     * @param filePath(带文件名) 文件绝对路径
     * @param fileName 下载文件名称
     * @param response 响应
     * @throws IOException 异常
     */
    public static void downFile(String fileName,HttpServletRequest request,
                                HttpServletResponse response,InputStream in) throws IOException {
        OutputStream os = null;
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream; charset=UTF-8");
            byte[] b = new byte[in.available()];
            in.read(b);
            os.write(b);
            os.flush();
        } catch (Exception e) {
            System.out.println("fileName=" + fileName);
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (in != null)
                in.close();
        }
    }
}
