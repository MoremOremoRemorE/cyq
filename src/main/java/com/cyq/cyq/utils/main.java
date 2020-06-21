package com.cyq.cyq.utils;

import com.aspose.pdf.*;
import java.io.InputStream;

 public class main {
    private static String srcPath = "G:\\ws.pdf"; // 源文件路径
    private static String targetPath = "G:\\ws1.pdf"; // 输入文件路径

    private static String srcText = "某某某"; // 需要替换的文本TNBBFZ.pdf /  TNBZQSC.pdf
    private static String targetText = "小白";
    public static void main(String[] args) {
        InputStream license = main.class.getClassLoader().getResourceAsStream("\\license.xml");
        try {
           new License().setLicense(license);
        } catch (Exception e) {
           e.printStackTrace();
        }

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
    }

}

