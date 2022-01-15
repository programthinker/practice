package iText;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

/**
 * @ProjectName practice
 * @PackageName PACKAGE_NAME
 * @ClassName iText.IText
 * @Author zhanggeyang
 * @Date 2021-12-20 23:58
 * @Description
 * @Version 1.0
 */

public class IText {
    public static void main(String[] args) throws Exception{
        String pdf = "/Users/zhanggeyang/Downloads/test.pdf";
        String text = "/Users/zhanggeyang/Downloads/《女生寝室》全集.txt";
        String FONT = "/Users/zhanggeyang/Library/Fonts/CONSOLA.TTF";
        Document document = new Document();
        OutputStream os = new FileOutputStream(new File(pdf));
        PdfWriter.getInstance(document, os);
        document.open();
        //方法一：使用Windows系统字体(TrueType)
        BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(text)), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(isr);
         String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            document.add(new Paragraph(str, font));
        }
        document.close();
    }
}
