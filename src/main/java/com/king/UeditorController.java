package com.king;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ueditor 相关控制层
 * 2017.02.07
 */
@RestController
@RequestMapping(path = "/ueditor")
public class UeditorController {

    /**
     * 获取 ueditor 的配置
     * 其实就是读取 ueditor-config.json 文件中的内容并以字符串的形式返回
     */
    @RequestMapping(method = RequestMethod.GET)
    public void getConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        response.setCharacterEncoding("utf-8");
        String url = UeditorController.class.getResource("/ueditor-config.json").getPath();
        Path path = Paths.get(url);
        List<String> lines = Files.readAllLines(path);
        StringBuilder sb = new StringBuilder();
        lines.forEach(line -> sb.append(line));
        String content = sb.toString();
        PrintWriter writer = response.getWriter();
        String callback = request.getParameter("callback");
        if (callback != null) {
            writer.write(callback + "(" + content + ")");
        } else {
            writer.write(content);
        }
        writer.flush();
        writer.close();
    }


    /**
     * ueditor 上传图片
     */
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, String> upload(@RequestParam("upfile") MultipartFile multipartFile, HttpServletResponse response) throws IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "x_requested_with");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("state", "SUCCESS");
        resultMap.put("original", multipartFile.getOriginalFilename());
        resultMap.put("size", "1111111");
        resultMap.put("title", "123.jpg");
        resultMap.put("type", ".jpg");
        resultMap.put("url", "/123.jpg");
        return resultMap;
    }


    @RequestMapping(method = RequestMethod.OPTIONS)
    public void fun(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "x_requested_with");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
    }

}