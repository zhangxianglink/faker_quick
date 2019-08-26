package com.example.demo.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class WaterMarkProcessor {

    @Test
    public void addWatermark() throws IOException {
        String text = "[新垣结衣fighting]";
        File inFile = new File("C:/Users/Administrator/Desktop/xsss.jpeg");
        File outFile = new File("C:/Users/Administrator/Desktop/xyjy.jpeg");
        textWatermark(text,inFile,outFile,"jpg");
    }

    private static void textWatermark(String text, File inFile , File outFile, String type) throws IOException {
        BufferedImage read = ImageIO.read(inFile);
        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage image = new BufferedImage(read.getWidth(), read.getHeight(), imageType);
        //初始化图片属性
        Graphics2D w = (Graphics2D)image.getGraphics();
        w.drawImage(read,0,0,null);
        AlphaComposite instance = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
        w.setComposite(instance);
        //旋转水印
        w.rotate(Math.toRadians(45),(double)read.getWidth()/2,(double)read.getHeight()/2);
        w.setColor(Color.GRAY);
        w.setFont(new Font("宋体", Font.BOLD, 70));
        FontMetrics fontMetrics = w.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(text, w);
        //书写位置为图片的中心
        int centerx = (read.getWidth() - (int)rect.getWidth()) / 2;
        int centery = (read.getHeight()  - 80) / 2;
        //开始书写文字
        w.drawString(text,centerx,centery);
        ImageIO.write(image,type,outFile);
        w.dispose();
    }
}
