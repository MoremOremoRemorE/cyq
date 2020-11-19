package com.cyq.cyq.controller;

import com.aspose.pdf.*;
import com.cyq.cyq.model.pdfUser;
import com.cyq.cyq.utils.pdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value ="/pdf" )
public class PdfController {

    @Autowired
    private com.cyq.cyq.service.pdfUserService pdfUserService;

    @RequestMapping("/downLoad")
    public ModelAndView downLoad() {
        ModelAndView mav = new ModelAndView("pdf/pdf");
        return mav;
    }
    @RequestMapping("/pdf")
    @ResponseBody
    public Map<String,Object> downPDF(String name,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException {
        Map<String,Object> map = new HashMap<>();
        map =  downLoadPDF(name);
        return map;
    }
    @RequestMapping("/downPdf")
    public void realDowunload(String name,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, UnsupportedEncodingException {
        realDownLoad(name,request,response);
    }

    private void realDownLoad(String name,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, UnsupportedEncodingException {
        //获取服务器文件
        String path = "C:\\pdf\\"+name+".pdf";
        File file = new File(path);

        InputStream ins = new FileInputStream(file);
        /* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
        response.setContentType("multipart/form-data");
        /* 设置文件头：最后一个参数是设置下载文件名 */
        response.setHeader("Content-Disposition", "attachment;filename="+ new String(file.getName().getBytes("utf-8"),"ISO-8859-1"));

        try{
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while((len = ins.read(b)) > 0){
                os.write(b,0,len);
            }
            os.flush();
            os.close();
            ins.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private Map<String,Object> downLoadPDF(String name) {
        Map<String,Object> map = new HashMap<>();
        String srcPath = "C:\\pdf\\test.pdf"; // 源文件路径
        String outName = "C:\\pdf\\"+name+".pdf";
        String targetPath = outName; // 输入文件路径

        String srcText = "某某某"; // 需要替换的文本
        String targetText = name;

        InputStream license = pdf.class.getClassLoader().getResourceAsStream("license.xml");
        try {
            new License().setLicense(license);
            Document pdfDoc = new Document(srcPath);
            TextFragmentAbsorber textFragmentAbsorber = new TextFragmentAbsorber(srcText);
            PageCollection pages = pdfDoc.getPages();
            System.out.println("文档总页码数：" + pages.size());
            pages.accept(textFragmentAbsorber);
            int i = 0;
            for (TextFragment textFragment : (Iterable<TextFragment>)textFragmentAbsorber.getTextFragments()) {
                textFragment.setText(targetText);
                System.out.println(++i);
            }
            pdfDoc.save(targetPath);
            System.out.println("总共替换" + i + "处");
            System.out.println("OK");
            //数据库写数据
            pdfUser pdfUser = new pdfUser();
            pdfUser.setName(name);
            pdfUser.setCountM(0);
            int count = pdfUserService.insertpdf(pdfUser);
            if(count>0){
                map.put("msg","success");
            }else {
                map.put("msg","fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("/count")
    @ResponseBody
    public int count(HttpServletRequest request,HttpServletResponse response)  {
        int count = pdfUserService.selectCount();
        return count;
    }
    @RequestMapping("/morePDF")
    @ResponseBody
    private Map<String,Object> morePDF(String userinfolist) {
        Map<String,Object> map = new HashMap<>();
        String namexx =userinfolist;
        String[] names = namexx.split(";");
            for(String name:names){
                String srcPath = "C:\\pdf\\test.pdf"; // 源文件路径
                String outName = "C:\\pdfNew\\"+name+".pdf";
                String targetPath = outName; // 输入文件路径

                String srcText = "某某某"; // 需要替换的文本
                String targetText = name;

                InputStream license = pdf.class.getClassLoader().getResourceAsStream("license.xml");
                try {
                    new License().setLicense(license);
                    Document pdfDoc = new Document(srcPath);
                    TextFragmentAbsorber textFragmentAbsorber = new TextFragmentAbsorber(srcText);
                    PageCollection pages = pdfDoc.getPages();
                    System.out.println("文档总页码数：" + pages.size());
                    pages.accept(textFragmentAbsorber);
                    int i = 0;
                    for (TextFragment textFragment : (Iterable<TextFragment>)textFragmentAbsorber.getTextFragments()) {
                        textFragment.setText(targetText);
                        System.out.println(++i);
                    }
                    pdfDoc.save(targetPath);
                    System.out.println("总共替换" + i + "处");
                    System.out.println("OK");
                    //数据库写数据
                    pdfUser pdfUser = new pdfUser();
                    pdfUser.setName(name);
                    pdfUser.setCountM(0);
                    int count = pdfUserService.insertpdf(pdfUser);
                    if(count>0){
                        System.out.println(name+"------success");
                        map.put("msg","success");
                    }else {
                        System.out.println(name+"------fail");
                        map.put("msg","fail");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return map;
        }
    }

