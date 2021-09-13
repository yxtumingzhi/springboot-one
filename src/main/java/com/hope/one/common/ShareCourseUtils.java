package com.hope.one.common;


import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
public class ShareCourseUtils implements CommandLineRunner {

    public static Font font75;
    public static Font font60;
    public static Font font32;
    public static Font font29;
    public static Font font28;

    /**
     * @return java.awt.image.BufferedImage
     * @throws
     * @description 操纵文字型水印
     * @params [address, dealerName, latitudeCommaLongitude, date]
     */
    public static BufferedImage handleTextWaterMark(String text, Font font, int fontSize, Color color) {
        BufferedImage image = new BufferedImage(text.length() * fontSize, fontSize * 3, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        image = g.getDeviceConfiguration().createCompatibleImage(text.length() * fontSize, fontSize * 3, Transparency.TRANSLUCENT);

        g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.setFont(font);


        g.drawString(text, 0, fontSize);

        g.dispose();

        return image;
    }

    public static BufferedImage getPieChart(String url) throws IOException {
        BufferedImage avatarImage = ImageIO.read(new URL(url));
        int width = 120;
        // 透明底的图片
        BufferedImage formatAvatarImage = new BufferedImage(width, width, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = formatAvatarImage.createGraphics();
        //把图片切成一个圓
        {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //留一个像素的空白区域，这个很重要，画圆的时候把这个覆盖
            int border = 0;
            //图片是一个圆型
            Ellipse2D.Double shape = new Ellipse2D.Double(border, border, width - border * 2, width - border * 2);
            //需要保留的区域
            graphics.setClip(shape);
            graphics.drawImage(avatarImage, border, border, width - border * 2, width - border * 2, null);
            graphics.dispose();
        }
        //在圆图外面再画一个圆
        {
            //新创建一个graphics，这样画的圆不会有锯齿
            graphics = formatAvatarImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int border = 0;
            //画笔是4.5个像素，BasicStroke的使用可以查看下面的参考文档
            //使画笔时基本会像外延伸一定像素，具体可以自己使用的时候测试
            Stroke s = new BasicStroke(1F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            graphics.setStroke(s);
            graphics.setColor(Color.WHITE);
            graphics.drawOval(border, border, width - border * 2, width - border * 2);
            graphics.dispose();
        }
        return formatAvatarImage;
    }

    @Override
    public void run(String... args) throws Exception {
        ClassPathResource siYuanFontResource = new ClassPathResource("fonts/SourceHanSansCN-Regular.ttf");
        try (InputStream inputStream = siYuanFontResource.getInputStream()) {
            font75 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font75 = font75.deriveFont(Font.PLAIN, 75);
        }
        try (InputStream inputStream = siYuanFontResource.getInputStream()) {
            font60 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font60 = font60.deriveFont(Font.PLAIN, 60);
        }
        try (InputStream inputStream = siYuanFontResource.getInputStream()) {
            font32 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font32 = font32.deriveFont(Font.PLAIN, 32);
        }
        try (InputStream inputStream = siYuanFontResource.getInputStream()) {
            font29 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font29 = font29.deriveFont(Font.PLAIN, 29);
        }
        try (InputStream inputStream = siYuanFontResource.getInputStream()) {
            font28 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font28 = font28.deriveFont(Font.PLAIN, 28);
        }
    }
}
