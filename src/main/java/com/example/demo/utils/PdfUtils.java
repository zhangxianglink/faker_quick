package com.example.demo.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(PdfUtils.class);
	
	public static float getPermillage(){
		Random random = new Random();
		int i = random.nextInt(800)+ 100;
		float permillage = i / 1000f;
		return permillage;
	}
	
	public static float getPermillage2(){
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyyHHmmss-SSS");
		String formatStr =formatter.format(new Date());
		formatStr = formatStr.substring(formatStr.length()-3);
		int parseInt = Integer.parseInt(formatStr);
		return parseInt / 1000f;
	}
	
	public static byte[] imageToPdf(byte[] file) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		try {
			Image image = Image.getInstance(file);
			float height = image.getScaledHeight();
			float width = image.getScaledWidth();
			Document document = null;
			if ((height*3/2) < width) {
				//当长宽比例 超出横版a4比例时,进行转向
				image.setRotation((float)(Math.PI/2));
			} 
			image.setAbsolutePosition(0, 0);
			//设置缩放比例, 详情可以看源码   percentX < percentY ? percentX : percentY
			image.scaleToFit(PageSize.A4);
			com.itextpdf.text.Rectangle rectangle = new com.itextpdf.text.Rectangle(image.getScaledWidth(), image.getScaledHeight());
			document = new Document(rectangle);
			PdfWriter.getInstance(document, bout);
			document.open();
			document.add(image);
			document.close();
		} catch (Exception e) {
            logger.error("convert image to pdf file error {}", e.getMessage());
//            throw new CertificationAuthorityException("500", "原始图像文件转换成PDF文件出错！");
        }
		return bout.toByteArray();
	}
	
	public  byte[] addWatermarktext(byte[] infile,  String text ) {
		if(text.equals(null)){
			return infile;
		}
		try {
			PdfReader reader = new PdfReader(infile);
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			PdfStamper stamper = new PdfStamper(reader, bao);
			// 字体设置支持中文
			BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
			int total = reader.getNumberOfPages() + 1;
			PdfContentByte under;
			Rectangle pageRect = null;
			Random random = new Random();
			for (int i = 1; i < total; i++) {
				pageRect = stamper.getReader().getPageSizeWithRotation(i);
				//计算水印的位置
				float x = random.nextInt((int)(pageRect.getWidth()/5 -25))+ 25f;
				float y = random.nextInt((int)(pageRect.getHeight()/2 - 50)) + 25f;
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
				under.setColorFill(BaseColor.RED);
				// 水印文字成45度角倾斜
				under.showTextAligned(Element.ALIGN_LEFT, text, x, y, 0);
				// 添加水印文字
				under.endText();
				under.setLineWidth(1f);
				under.stroke();
			}
			stamper.close();
			byte[] byteArray = bao.toByteArray();
			return byteArray;
		} catch (Exception e) {
			throw new RuntimeException("pdf文件添加水印失败,请检查文件是否存在");
		}

	}
	
	 public byte[] grayImage(byte[] fileByte,String suffix) {
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				ByteArrayInputStream in = new ByteArrayInputStream(fileByte);
				BufferedImage image = ImageIO.read(in);
				int width = image.getWidth();
				int height = image.getHeight();
				BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						int rgb = image.getRGB(i, j);
						grayImage.setRGB(i, j, rgb);
					}
				}
				ImageIO.write(grayImage,suffix,bos);
			} catch (Exception e) {
				logger.error("convert image to grayImage file error {}", e);
			}
			return bos.toByteArray();
		}	
	 
	private void byteToFile(byte[] bytes,String filePath,String fileName){
		try {
			File file = new File(filePath + File.separator + fileName);
			try (FileOutputStream fileOutputStream = new FileOutputStream(file);
				 BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);){
				 // 检查文件地址是否存在
				   File dir = new File(filePath);
			       if (!dir.exists() && dir.isDirectory())
			       {
			           dir.mkdirs();
			       }
			       bos.write(bytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
