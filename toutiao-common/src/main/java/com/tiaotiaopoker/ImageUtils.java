package com.tiaotiaopoker;

import sun.swing.SwingUtilities2;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class ImageUtils {

    public static byte[] createImage(String str) throws Exception {
        String filepath = "D://" + getDate() + ".png";
        File outFile = new File(filepath);

        Font font = new Font("黑体", Font.BOLD, 18);
        //获取font的样式应用在str上的整个矩形
        Rectangle2D r = font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));
        //获取单个字符的高度
        int unitHeight = (int) Math.floor(r.getHeight());
        // 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int width = (int) Math.round(r.getWidth()) + 1;
        // 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
        int height = unitHeight + 3;
        // 创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        // 先用白色填充整张图片,也就是背景
        g.fillRect(0, 0, width, height);
        //在换成黑色
        g.setColor(Color.black);
        // 设置画笔字体
        g.setFont(font);
        // 画出字符串
        g.drawString(str, 0, font.getSize());
        g.dispose();
        if (outFile != null) {
            // 输出png图片到指定目录
            ImageIO.write(image, "png", outFile);
        }
        // 返回数据流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean flag = ImageIO.write(image, "png", out);
        byte[] b = out.toByteArray();
        return b;
    }

    public static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }

    public static String TextToPic(String text, int width, int height,int fontSize) {
        try {
            String filepath = "D://" + getDate() + ".png";
            File file = new File(filepath);
            System.out.println("topic=" + text);
            Font font = new Font("微软雅黑", Font.BOLD, fontSize);
            BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = (Graphics2D) bi.getGraphics();
            g2.setBackground(null);
            g2.clearRect(0, 0, width, height);
            g2.setFont(font);
            g2.setPaint(Color.BLACK);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.3f));
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            printString(g2, text, (width - (text.length() * fontSize)) / 2 + 0,(height - fontSize) / 2 + 40, fontSize);
            g2.dispose();
            ImageIO.write(bi, "png", file);
            return "image" + getDate() + ".png";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void printString(Graphics2D g2d, String str, int x, int y,int fontSize) {
        FontMetrics metrics = SwingUtilities2.getFontMetrics(null,g2d.getFont());
        for (char ca : str.toCharArray()) {
            int px = metrics.stringWidth("" + ca);
            g2d.drawString("" + ca, x + (fontSize - px) / 2, y);
            x += fontSize;
        }
    }

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(new Date());
    }

    /*public static void main(String[] args) throws IOException {
        TextToPic("中文生成图片", 50, 50, 50);
    }*/

    public static void main(String[] args) throws Exception {
        createImage("好好好哈哈哈哈");
    }

    // 根据str,font的样式以及输出文件目录
    public static void createImage(String str, Font font, File outFile,
                                   Integer width, Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
        g.setColor(Color.black);// 在换成黑色
        g.setFont(font);// 设置画笔字体
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        for (int i = 0; i < 6; i++) {// 256 340 0 680
            g.drawString(str, i * 680, y);// 画出字符串
        }
        g.dispose();
        ImageIO.write(image, "png", outFile);// 输出png图片
    }
}
