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
import java.util.List;

@RestController
@RequestMapping(path = "/ueditor")
public class UeditorController {

    /**
     * 获取 ueditor 的配置
     */
    @GetMapping
    public void getConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
    @PostMapping
    public String upload(@RequestParam("upfile") MultipartFile multipartFile) throws IOException {

//        Map<String, String> resultMap = new HashMap<>();
//        resultMap.put("state", "SUCCESS");
//        resultMap.put("original", multipartFile.getOriginalFilename());
//        resultMap.put("size", "1111111");
//        resultMap.put("title", "123.jpg");
//        resultMap.put("type", ".jpg");
//        resultMap.put("url", "/123.jpg");
//        return resultMap;

        String str = "{\"state\": \"SUCCESS\",\"original\": \"8235345_8235345_1309860288554.jpg\",\"size\": \"731183\",\"title\": \"1486366738678046082.jpg\",\"type\": \".jpg\",\"url\": \"/ueditor/jsp/upload/image/20170206/1486366738678046082.jpg\"}";
        return str;
    }

}