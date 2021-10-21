package com.hope.one;

import cn.hutool.core.img.Img;
import cn.hutool.core.io.FileUtil;
import com.hope.one.common.RhdShop;
import com.hope.one.common.ShareCourseUtils;
import com.vdurmont.emoji.EmojiParser;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Coordinate;
import net.coobird.thumbnailator.geometry.Positions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class OneApplicationTests {

    @Test
    void aaa() throws IOException {
        //size(width, height) --> 指定宽高按比例缩放，图片宽高比指定大，就按比例饿缩小到指定数值
//        Thumbnails.of("C:\\Users\\tumingzhi\\Desktop\\imageAnswer\\微信图片_20210603151744.jpg")
//                .size(200, 300)
//                .toFile("C:\\Users\\tumingzhi\\Desktop\\imageAnswer\\微信图片_202106031517441111.jpg");

        //watermark(location, file, clarity) --> location: 位置	file: 水印图	clarity: 透明度
        Thumbnails
                .of("C:\\Users\\tumingzhi\\Downloads\\分享海报_slices\\1.jpg")
                .size(750, 1334)
                .watermark(
                        new Coordinate(40, 633), ImageIO.read(new File("C:\\Users\\tumingzhi\\Downloads\\分享海报_slices\\2.png")), 1f
                ).toFile("C:\\Users\\tumingzhi\\Downloads\\分享海报_slices\\3.jpg");

//
//
//        // 将face.jpg切割为原型保存为face_radis.png
        Img.from(FileUtil.file("C:\\\\Users\\\\tumingzhi\\\\Desktop\\\\imageAnswer\\\\20201231124839_ohgja.png"))
                .cut(0, 0, 200)
                .round(200)
                .write(FileUtil.file("C:\\\\Users\\\\tumingzhi\\\\Desktop\\\\imageAnswer\\\\20201231124839_ohgja1.png"));


        BufferedImage bi1 = ImageIO.read(FileUtil.file("C:\\\\Users\\\\tumingzhi\\\\Desktop\\\\imageAnswer\\\\20201126214708_2c331.jpg"));

        int width = bi1.getWidth();
        int height = bi1.getHeight();

        BufferedImage target = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = (Graphics2D) target.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(bi1.getScaledInstance(width, height, Image.SCALE_SMOOTH), width, height, null);

        g2d.dispose();
        Thumbnails.Builder thumbnails = Thumbnails.of(FileUtil.file("C:\\\\Users\\\\tumingzhi\\\\Desktop\\\\imageAnswer\\\\微信图片_20210603151744.jpg"));
        System.out.println(EmojiParser.parseToUnicode("\uD83C\uDF3B \uD83C\uDF3B \uD83C\uDF3B \uD83D\uDC3C鳏寡腌臜楶娯"));
        thumbnails.watermark(new Coordinate(197, 835), ShareCourseUtils.handleTextWaterMark(EmojiParser.parseToHtmlDecimal("\uD83C\uDF3B \uD83C\uDF3B \uD83C\uDF3B \uD83D\uDC3C鳏寡腌臜楶娯") + "", ShareCourseUtils.font32, 32, Color.getHSBColor(0f, 0f, 0.2f)), 1);
        thumbnails.scale(1);
        thumbnails.toFile("C:\\\\Users\\\\tumingzhi\\\\Desktop\\\\imageAnswer\\\\20201126214708_2c3351231231231.jpg");

        //ImageIO.write(target, "png", new File("C:\\Users\\tumingzhi\\Desktop\\imageAnswer\\20201126214708_2c335.jpg"));

        // ImageIO.write(bi1, "png", new File("C:\\\\Users\\\\tumingzhi\\\\Desktop\\\\imageAnswer\\\\20201126214708_2c335.jpg"));

    }


    @Test
    void contextLoads() {

        List<RhdShop> rhdShops = Lists.newArrayList();
        RhdShop a = new RhdShop();
        a.setBrand("aBrand");
        rhdShops.add(a);

        RhdShop b = new RhdShop();
        b.setBrand("bBrand");
        rhdShops.add(b);

        RhdShop c = new RhdShop();
        c.setBrand("cBrand");
        rhdShops.add(c);

        RhdShop d = new RhdShop();
        d.setBrand("dBrand");
        rhdShops.add(d);

        RhdShop e = new RhdShop();
        e.setBrand("aaa");
        rhdShops.add(e);

        Map<String, List<RhdShop>> listMap = rhdShops.stream().collect(Collectors.groupingBy(RhdShop::getBrand));

        System.out.println(listMap);
    }

    @Test
    public void teaaa() throws IOException {
        BufferedImage avatarImage = ImageIO.read(FileUtil.file("C:\\\\Users\\\\tumingzhi\\\\Desktop\\\\imageAnswer\\\\20201126214708_2c331.jpg"));
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
        try (OutputStream os = new FileOutputStream("C:\\\\Users\\\\tumingzhi\\\\Desktop\\\\imageAnswer\\\\20201126214708_2c331222222.jpg")) {
            ImageIO.write(formatAvatarImage, "PNG", os);
        }
        {
//            url = "https://img-blog.csdn.net/20180529213113521";
//            BufferedImage srcImg = ImageIO.read(new URL(url));
//            //scrImg加载完之后没有任何颜色
//            BufferedImage blankImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
//            graphics = blankImage.createGraphics();
//            graphics.drawImage(srcImg, 0, 0, null);
//
//            int x = (blankImage.getWidth() - width) / 2;
//            int y = (blankImage.getHeight() - width) / 2;
//            graphics.drawImage(formatAvatarImage, x, y, width, width, null);
//            try (OutputStream os = new FileOutputStream("/data/temp/temp-blank.png")) {
//                ImageIO.write(blankImage, "PNG", os);
//            }
        }

    }

    @Test
    public void str() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
//        String str = "苍天厚土\uD83D\uDE01 \uD83D\uDC36 \uD83E\uDD14 \uD83D\uDC7B \uD83D\uDE92";
//        System.out.println("原始字符为：\n" + str);
//
//        System.out.println("to aliases 之后：");
//        System.out.println(EmojiParser.parseToAliases(str));
//        System.out.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.PARSE));
//        System.out.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.REMOVE));
//        System.out.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.IGNORE));
//        System.out.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.IGNORE));
//        System.out.println(EmojiParser.parseToUnicode(str));
    }

}

class Demo {
    private Long id;

    public Demo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
