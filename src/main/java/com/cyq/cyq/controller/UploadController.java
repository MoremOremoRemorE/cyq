package com.cyq.cyq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {
    @GetMapping(value = "/file")
    public ModelAndView goodsell(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("video/uploadfile");
        return mav;
    }

    @PostMapping(value = "/fileUpload")
    public ModelAndView fileUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("video/uploadfile");
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://temp-rainy//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = request.getContextPath()+"/WEB-INF/images/" + fileName;
        mav.addObject("filename", filename);
        return mav;
    }

}
