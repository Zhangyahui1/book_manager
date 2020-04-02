package com.qlu;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class TestImage {
    public void replaceImageColor(String file, Color srcColor, Color targetColor) throws IOException {
        URL http;
        if(file.trim().startsWith("https")){
            http = new URL(file);
            HttpsURLConnection conn = (HttpsURLConnection) http.openConnection();
            conn.setRequestMethod("GET");
        }else if(file.trim().startsWith("http")){
            http = new URL(file);
            HttpURLConnection conn = (HttpURLConnection) http.openConnection();
            conn.setRequestMethod("GET");
        }else{
            http = new File(file).toURI().toURL();
        }
        BufferedImage bi = ImageIO.read(http.openStream());

        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                System.out.println(bi.getRGB(i, j));
                if(srcColor.getRGB()==bi.getRGB(i, j)){
                    System.out.println(i+","+j+"  from:"+srcColor.getRGB()+"to"+targetColor.getRGB());
                    bi.setRGB(i, j, targetColor.getRGB());
                }
            }
        }
        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = it.next();
        File f = new File("c://test02.png");
        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
        writer.setOutput(ios);
        writer.write(bi);
        bi.flush();
        ios.flush();
        ios.close();
    }

    public static void createImage(int width, int height) throws IOException{
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphic = bi.createGraphics();
        graphic.setColor(new Color(0.2f,0.3f,0.4f,0.4f));
        graphic.fillRect(0, 0, width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //result[i][j] = bi.getRGB(i, j) & 0xFFFFFF;
                System.out.println(bi.getRGB(i, j));
                // bi.setRGB(i, j, 0xFFFFFF);
            }
        }

        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = it.next();
        File f = new File("D://test02.png");
        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
        writer.setOutput(ios);

        writer.write(bi);
    }

    /*
     * 图片缩放,w，h为缩放的目标宽度和高度
     * src为源文件目录，dest为缩放后保存目录
     */
    public static void zoomImage(String src,String dest,int w,int h) throws Exception {

        double wr=0,hr=0;
        File srcFile = new File(src);
        File destFile = new File(dest);

        BufferedImage bufImg = ImageIO.read(srcFile); //读取图片
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//设置缩放目标图片模板

        wr=w*1.0/bufImg.getWidth();     //获取缩放比例
        hr=h*1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /*
     * 图片按比率缩放
     * size为文件大小
     */
    public static void zoomImage(String src,String dest,Integer size) throws Exception {
        File srcFile = new File(src);
        File destFile = new File(dest);

        long fileSize = srcFile.length();
        if(fileSize < size * 1024)   //文件大于size k时，才进行缩放,注意：size以K为单位
            return;

        Double rate = (size * 1024 * 0.5) / fileSize; // 获取长宽缩放比例

        BufferedImage bufImg = ImageIO.read(srcFile);
        Image Itemp = bufImg.getScaledInstance(bufImg.getWidth(), bufImg.getHeight(), bufImg.SCALE_SMOOTH);

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(rate, rate), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        zoomImage("d://library.jpg","d://library2.jpg",430,500);
    }
}
