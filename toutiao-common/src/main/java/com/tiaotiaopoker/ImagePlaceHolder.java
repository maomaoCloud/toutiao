package com.tiaotiaopoker;

import sun.swing.SwingUtilities2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

public class ImagePlaceHolder {

    public static void TextToPic (String text, int width, int height, int fontSize, String bgRGB, String textRGB, OutputStream outputStream) {
        try {
            System.out.println("topic=" + text);
            Font          font = new Font("微软雅黑", Font.BOLD, fontSize);
            BufferedImage bi   = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D    g2   = (Graphics2D) bi.getGraphics();
            g2.setBackground(genFromGRB(bgRGB));
            g2.clearRect(0, 0, width, height);
            g2.setFont(font);
            g2.setPaint(genFromGRB(textRGB));
            //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            printString(g2, text, (width - (text.length()*fontSize))/2, (height - fontSize)/2 + fontSize*4/5, fontSize);
            g2.dispose();
            ImageIO.write(bi, "png", outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printString (Graphics2D g2d, String str, int x, int y, int fontSize) {
        FontMetrics metrics = SwingUtilities2.getFontMetrics(null, g2d.getFont());
        for (char ca : str.toCharArray()) {
            int px = metrics.stringWidth("" + ca);
            g2d.drawString("" + ca, x + (fontSize - px)/2, y);
            x += fontSize;
        }
    }

    public static Color genFromGRB (String rgb) {
        try {
            return new Color(Integer.valueOf(rgb.substring(0, 2), 16), Integer.valueOf(rgb.substring(2, 4), 16), Integer.valueOf(rgb.substring(4, 6), 16));
        } catch (Exception e) {
        }
        return null;
    }

}