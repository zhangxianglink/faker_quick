package com.example.demo.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.util.ResourceUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @author zhang
 *
 */
public class WatermarkPdfUtils {
	/**
	 * 给pdf文件添加中文水印
	 */
	public static boolean addWatermarktext(byte[] infile, String outfile, String text ) {
		try {
			PdfReader reader = new PdfReader(infile);
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			PdfStamper stamper = new PdfStamper(reader, bao);
//			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outfile));
			// 字体设置支持中文
			BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
			int total = reader.getNumberOfPages() + 1;
			PdfContentByte under;
			Rectangle pageRect = null;
			Random random = new Random();
			for (int i = 1; i < total; i++) {
				pageRect = stamper.getReader().getPageSizeWithRotation(i);
				//计算水印的位置  , 随机
				float x = random.nextInt((int)(pageRect.getWidth()/5 -25))+ 25f;
				float y = random.nextInt((int)(pageRect.getHeight() - 50)) + 25f;
				// 获得PDF最顶层
				under = stamper.getOverContent(i);
				under.saveState();
				// set Transparency
				PdfGState gs = new PdfGState();
				// 设置透明度为0.3
				gs.setFillOpacity(0.3f);
				under.setGState(gs);
				under.beginText();
				under.setFontAndSize(base, 22);
				under.setTextMatrix(70, 200);
				under.setColorFill(BaseColor.BLUE);
				// 水印文字成45度角倾斜
				under.showTextAligned(Element.ALIGN_LEFT, text, x, y, 0);
				// 添加水印文字
				under.endText();
				under.setLineWidth(1f);
				under.stroke();
			}
			stamper.close();
			byte[] byteArray = bao.toByteArray();
//			System.out.println(new String(byteArray));
			byte2File(byteArray, "d:/data", "a.pdf");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	

	
	 public static void byte2File(byte[] buf, String filePath, String fileName)
	    {
	        BufferedOutputStream bos = null;
	        FileOutputStream fos = null;
	        File file = null;
	        try
	        {
	            File dir = new File(filePath);
	            if (!dir.exists() && dir.isDirectory())
	            {
	                dir.mkdirs();
	            }
	            file = new File(filePath + File.separator + fileName);
	            fos = new FileOutputStream(file);
	            bos = new BufferedOutputStream(fos);
	            bos.write(buf);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            if (bos != null)
	            {
	                try
	                {
	                    bos.close();
	                }
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	            if (fos != null)
	            {
	                try
	                {
	                    fos.close();
	                }
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	
	/*
	 * 使用pdfBox添加水印
	 */
	private static byte[] watermarkPDF (byte[] fileStored){
        byte[] byteArray = null;
        try{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //byte[]
        PDDocument doc = PDDocument.load(fileStored);
        doc.setAllSecurityToBeRemoved(true);
        for(PDPage page:doc.getPages()){
            PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true);
            String ts = "confidential document";
            PDFont font = PDType1Font.HELVETICA_OBLIQUE;
            float fontSize = 50.0f;
            PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
            // 透明度
            r0.setNonStrokingAlphaConstant(0.2f);
            r0.setAlphaSourceFlag(true);
            cs.setGraphicsStateParameters(r0);
            cs.setNonStrokingColor(200,0,0);//Red
            cs.beginText();
            cs.setFont(font, fontSize);
            // 获取旋转实例
            cs.setTextMatrix(Matrix.getRotateInstance(45,150f,150f));
            cs.showText(ts);
            cs.endText();
 
            cs.close();
        }
        doc.save(bos);
        byteArray = bos.toByteArray();
        }catch (Exception e) {
			e.printStackTrace();
		}
        return byteArray;
    }
	


	
	public static void main(String[] args) throws Exception {
		File file = new File("D:/data/zk.pdf");
		FileInputStream stream = new  FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int n;
		while((n = stream.read(b))!= -1){
			bos.write(b, 0, n);
		}
		stream.close();
		bos.close();
		byte[] array = bos.toByteArray();
		
//		File file1 = ResourceUtils.getFile("classpath:zzd/a.txt");
//		byte[] pdf = watermarkPDF(array);
//		byte2File(pdf, "D:/data", "zk.pdf");
		
		addWatermarktext(array, "D:/data/zk.pdf", "仅供XXX公司首营备案");
//		File file2 = new File("D:/data/a.jpg");
//		byte[] createImage = createImage("仅供XXX公司首营备案", file2);
	}
	
	
}
