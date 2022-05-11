package com.atguigu.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传测试
 */
@Slf4j
@Controller
public class FormTestController {
    @GetMapping("/form_layouts")
    public String form_layouts() {

        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImage") MultipartFile headerImage,
                         @RequestPart("photos") MultipartFile[] photos) {

        String fileName = headerImage.getOriginalFilename();
        String suffixNamme = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixNamme;
        fileName = fileName.replaceAll("-", "");
        File file = new File("C:\\image");
        if (!file.exists()) {
            file.mkdirs();
        }
        String finalPath = file.getPath() + File.separator + fileName;
        File finalFile = new File(finalPath);
        try {
            headerImage.transferTo(finalFile);
        } catch (IOException e) {
            e.printStackTrace();
        }



        for (MultipartFile photo : photos) {

            String fileLable = photo.getOriginalFilename();
            String suffixNammes = fileName.substring(fileName.lastIndexOf("."));
            fileLable = UUID.randomUUID() + suffixNammes;
            fileLable = fileLable.replaceAll("-", "");
            File files = new File("C:\\image");
            if (!files.exists()) {
                files.mkdirs();
            }
            String finalPaths = file.getPath() + File.separator + fileLable;
            File finalFiles = new File(finalPaths);
            try {
                photo.transferTo(finalFiles);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/main.html";
    }
}
