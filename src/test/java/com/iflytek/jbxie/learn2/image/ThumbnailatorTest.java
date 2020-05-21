package com.iflytek.jbxie.learn2.image;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;
import org.junit.Test;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片处理
 *
 * @author jbxie
 * @create 2019/09/24 20:33
 */

public class ThumbnailatorTest {

    public static void main(String[] args) throws IOException {

        //  自适应
//        adaptiveTest();
        // 自适应
//        adaptiveTest1();
//        adaptiveTest2();
//        // 缩放指定比例
//        scaleTest();
//        scale1Test();
//        // 缩放指定宽高
//        sizeTest();
//        // 旋转
//        rotateTest();
//        // 无损压缩
//        losslessTest();
//        // 裁剪region
//        regionTest();

        // 获取原始图片路径（原始图片放在resources）
//        int width = 5761;
//        int height = 3841;
//        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("003.jpg").getPath();
//         // 无损压缩
//        ImageUtil.losslessCompression(srcImg, "003_lossless_10.jpg", width, height);
//        // 指定固定宽高
//        ImageUtil.featureSet(srcImg, "003_featureSet_size_10.jpg", 300, 300, false,0, 0, null);
//        // 指定旋转
//        ImageUtil.featureSet(srcImg, "003_featureSet_rotate_10.jpg", width, height, true, 0, 90, null);
//        // 裁剪region
//        Map<String, Integer> rectangle = new HashMap<>();
//        rectangle.put("x", 0);
//        rectangle.put("y", 0);
//        rectangle.put("width", 400);
//        rectangle.put("height", 400);
//        ImageUtil.featureSet(srcImg, "003_featureSet_region_10.jpg", 0, 0, true, 0, 0, rectangle);
        // 缩放
//        ImageUtil.featureSet(srcImg, "003_featureSet_scale_10.jpg", 0, 0, true, 0.5, 0, null);
        // 旋转+缩放+裁剪
        // 裁剪region
//        Map<String, Integer> rectangle1 = new HashMap<>();
//        rectangle1.put("x", 307);
//        rectangle1.put("y", 352);
//        rectangle1.put("width", 100);
//        rectangle1.put("height", 100);
//        srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("img1.jpg").getPath();
//        ImageUtil.featureSet1(srcImg, "img1_featureSet_scale_region_10.jpg", 0, 0, true, 0.5, 90, rectangle1);
        // 自适应

//        ImageUtil.adaptive("xzy.jpg", "xzy_200.jpg", 1920,720, 0);
//
//        ImageUtil.adaptive("xzy.jpg", "xzy_201.jpg", 600,720, 0);
//
//        ImageUtil.adaptive("xzy.jpg", "xzy_90_200.jpg", 1920,720, 90);
//
//        ImageUtil.adaptive("xzy.jpg", "xzy_90_201.jpg", 600,720, 90);
    }

    public static void scaleTest() throws IOException {
        // 获取原始图片路径（原始图片放在resources）
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("img1.jpg").getPath();
        double scaleRatio = 0.5;
        String descImg = "img1_scale_01.jpg";
        Thumbnails.of(srcImg).scale(scaleRatio).toFile(descImg);
    }

    public static void scale1Test() throws IOException {
        // 获取原始图片路径（原始图片放在resources）
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("img1.jpg").getPath();
        double scaleRatio = 1.2;
        String descImg = "img1_scale1_01.jpg";
        Thumbnails.of(srcImg).scale(scaleRatio).toFile(descImg);
    }

    public static void sizeTest() throws IOException {
        // 获取原始图片路径（原始图片放在resources）
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("img1.jpg").getPath();
        String descImg = "img1_size_01.jpg";
        Thumbnails.of(srcImg)
                .size(200, 200)
                .keepAspectRatio(false)
                .toFile(descImg);
    }

    public static void rotateTest() throws IOException {
        // 获取原始图片路径（原始图片放在resources）
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("003.jpg").getPath();
        String descImg = "003_rotate_03.jpg";
        Thumbnails.of(srcImg)
                .size(5761, 3841)
                .rotate(90)
                .toFile(descImg);
    }

    public static void losslessTest() throws IOException {
        // 获取原始图片路径（原始图片放在resources）
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("003.jpg").getPath();
        String descImg = "img1_lossless_20.jpg";
        Thumbnails.of(srcImg)
                .scale(1)
                .toFile(descImg);
    }

    public static void regionTest() throws IOException {
        // 获取原始图片路径（原始图片放在resources）
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("003.jpg").getPath();
        String descImg = "003_region_01.jpg";
        Rectangle rectangle = new Rectangle(0, 0, 400, 400);
        Thumbnails.of(srcImg)
                .sourceRegion(rectangle)
                .size((int) rectangle.getWidth(), (int) rectangle.getHeight())
                .toFile(descImg);
    }

    public static void outputImage(ServletResponse response) throws IOException {
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("003.jpg").getPath();
        FileInputStream fileInputStream = new FileInputStream(srcImg);
        int i = fileInputStream.available();
        byte data[] = new byte[i];
        fileInputStream.read(data);
        response.setContentType("image/jpg");
        OutputStream os = response.getOutputStream();
        os.write(data);
        os.flush();
        os.close();
        fileInputStream.close();
    }

    public static void adaptiveTest() throws IOException {
        // xzy.jpg
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("xzy.jpg").getPath();
        double yw = 1920;
        double yh = 720;
        double sw = 1440;
        double sh = 1080;
        double tw = 0;
        double th = 0;
        if (sw > yw) {
            tw = yw;
            th = Integer.parseInt(new DecimalFormat("0").format(Math.floor((yw / sw) * sh)));
        } else if (sh > yh) {
            th = yh;
            tw = Integer.parseInt(new DecimalFormat("0").format(Math.floor((yh / sh) * sw)));
        } else {
            tw = yw;
            th = yh;
        }
        System.out.println("tw:th=" + tw + ":" + th);

//        Thumbnails.of(srcImg)
//                .size((int)tw, (int)th)
//                .keepAspectRatio(false)
//                .toFile("xzy_001.jpg");

    }

    public static void adaptiveTest1() throws IOException {
        // xzy.jpg
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("xzy.jpg").getPath();
        double yw = 1920;
        double yh = 720;
        double sw = 1440;
        double sh = 1080;
        double tw = 0;
        double th = 0;
        if (sw > yw) {
            tw = yw;
            th = Integer.parseInt(new DecimalFormat("0").format(Math.floor((yw / sw) * sh)));
        } else if (sh > yh) {
            th = yh;
            tw = Integer.parseInt(new DecimalFormat("0").format(Math.floor((yh / sh) * sw)));
        } else {
            tw = yw;
            th = yh;
        }
        System.out.println("tw:th=" + tw + ":" + th);

//        Position position, BufferedImage image, float opacity

//        Position position = Positions.BOTTOM_LEFT;
        Position position1 = new Position() {
            @Override
            public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
                return new Point(300, 300);
            }
        };
        String watermarkImg = ThumbnailatorUtils.class.getClassLoader().getResource("logo2.png").getPath();
        Thumbnails.of(srcImg)
                .size((int) yw, (int) yh)
                .keepAspectRatio(false)
                .watermark(position1, ImageIO.read(new File(watermarkImg)), 0.5f)
                .toFile("xzy_watermark_001.jpg");

    }

    public static void adaptiveTest2() throws IOException {
        // xzy.jpg
        String srcImg = ThumbnailatorUtils.class.getClassLoader().getResource("xzy.jpg").getPath();
        double yw = 1920;
        double yh = 720;
        double sw = 1440;
        double sh = 1080;
        double tw = 0;
        double th = 0;
        if (sw > yw) {
            tw = yw;
            th = Integer.parseInt(new DecimalFormat("0").format(Math.floor((yw / sw) * sh)));
        } else if (sh > yh) {
            th = yh;
            tw = Integer.parseInt(new DecimalFormat("0").format(Math.floor((yh / sh) * sw)));
        } else {
            tw = yw;
            th = yh;
        }
        System.out.println("tw:th=" + tw + ":" + th);

//        Position position, BufferedImage image, float opacity

//        Position position = Positions.BOTTOM_LEFT;
        Position position1 = new Position() {
            @Override
            public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
                return new Point(300, 300);
            }
        };
        Thumbnails.of(srcImg)
                .size((int) tw, (int) th)
                .keepAspectRatio(false)
                .toFile("xzy_watermark_100.jpg");

        Thumbnails.of(srcImg)
                .size((int) yw, (int) yh)
                .keepAspectRatio(false)
                .addFilter(new OpacityFilter(0.1f))
                .watermark(ImageIO.read(new File("xzy_watermark_100.jpg")), 1.0f)
                .toFile("xzy_100.jpg");
    }

}

/**
 * 图片处理
 *
 * @author jbxie
 * @create 2019/09/25 19:50
 */

class ImageUtil {

    /**
     * 无损压缩
     *
     * @param srcImg  原图片路径（绝对路径）
     * @param descImg 目标图片路径（绝对路径）
     * @param width   输出图片宽
     * @param height  输出图片高
     * @throws IOException
     */
    public static void losslessCompression(String srcImg, String descImg, int width, int height) throws IOException {
        Thumbnails.of(srcImg)
                .size(width, height)
                .toFile(descImg);
    }

    /**
     * 图片特性处理集
     *
     * @param srcImg          原图片路径（绝对路径）
     * @param descImg         目标图片路径（绝对路径）
     * @param width           输出图片宽
     * @param height          输出图片高
     * @param keepAspectRatio true: 保持原始图像的长宽比  false:不保持原始图像的长宽比
     * @param scale           通过比例控制原始图片宽高缩放（>1 : 放大  <1 缩小 =1：不变）
     * @param angle           顺时针 旋转角度
     * @param rectangle       裁剪区域 x:指定的x坐标  y:指定的y坐标 width:裁剪宽度 height:裁剪高度
     */
    public static void featureSet(String srcImg, String descImg, int width, int height, boolean keepAspectRatio, double scale, double angle, Map<String, Integer> rectangle) throws IOException {
        Thumbnails.Builder<File> builder = Thumbnails.of(srcImg);
        if (width > 0 && height > 0) {
            builder = builder.size(width, height);
        }
        if (scale > 0) {
            builder = builder.scale(scale);
        }
        if (!keepAspectRatio) {
            builder = builder.keepAspectRatio(false);
        }
        int num = 4;
        if (rectangle != null && num == rectangle.size()) {
            Rectangle rectangleObj = new Rectangle(rectangle.get("x"), rectangle.get("y"), rectangle.get("width"), rectangle.get("height"));
            builder = builder
                    .sourceRegion(rectangleObj)
                    .size((int) rectangleObj.getWidth(), (int) rectangleObj.getHeight());
        }
        if (angle > 0) {
            builder = builder.rotate(angle);
        }
        builder.toFile(descImg);
    }

    /**
     * 图片特性处理集
     *
     * @param srcImg          原图片路径（绝对路径）
     * @param descImg         目标图片路径（绝对路径）
     * @param width           输出图片宽
     * @param height          输出图片高
     * @param keepAspectRatio true: 保持原始图像的长宽比  false:不保持原始图像的长宽比
     * @param scale           通过比例控制原始图片宽高缩放（>1 : 放大  <1 缩小 =1：不变）
     * @param angle           顺时针 旋转角度
     * @param rectangle       裁剪区域 x:指定的x坐标  y:指定的y坐标 width:裁剪宽度 height:裁剪高度
     */
    public static void featureSet1(String srcImg, String descImg, int width, int height, boolean keepAspectRatio, double scale, double angle, Map<String, Integer> rectangle) throws IOException {
        Thumbnails.Builder<File> builder = Thumbnails.of(srcImg);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        builder.scale(scale).rotate(angle).toOutputStream(outStream);
        byte[] b = outStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);

        Rectangle rectangleObj = new Rectangle(rectangle.get("x"), rectangle.get("y"), rectangle.get("width"), rectangle.get("height"));
        Thumbnails.of(byteArrayInputStream).sourceRegion(rectangleObj)
                .size((int) rectangleObj.getWidth(), (int) rectangleObj.getHeight())
                .toFile(descImg);
        outStream.close();
        byteArrayInputStream.close();
    }

    public static void adaptive(String srcImg, String descImg, int demandWidth, int demandHeight, double angle) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(srcImg);
        BufferedImage bufferedImg = ImageIO.read(fileInputStream);
        int imgWidth = bufferedImg.getWidth();
        int imgHeight = bufferedImg.getHeight();
        int tw = 0;
        int th = 0;
        double num1 = 0;
        double num2 = 0;
        if (imgWidth > demandWidth) {
            tw = demandWidth;
            num1 = demandWidth;
            num2 = imgWidth;
            th = Integer.parseInt(new DecimalFormat("0").format(Math.floor((num1 / num2) * imgHeight)));
        } else if (imgHeight > demandHeight) {
            th = demandHeight;
            num1 = demandHeight;
            num2 = imgHeight;
            tw = Integer.parseInt(new DecimalFormat("0").format(Math.floor((num1 / num2) * imgWidth)));
        } else {
            tw = demandWidth;
            th = demandHeight;
        }
        Thumbnails.Builder<File> builder = Thumbnails.of(srcImg)
                .size(tw, th)
                .rotate(angle);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        builder.toOutputStream(outStream);
        byte[] b = outStream.toByteArray();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(b);
        outStream.close();
        Thumbnails.of(srcImg)
                .size(demandWidth, demandHeight)
                .keepAspectRatio(false)
                .addFilter(new OpacityFilter(0.1f))
                .watermark(ImageIO.read(inputStream), 1.0f)
                .toFile(descImg);
        inputStream.close();
    }
}

class OpacityFilter implements ImageFilter {

    private float opacity = 0.1f;
    public OpacityFilter(float opacity) {
        this.opacity = opacity;
    }

    @Override
    public BufferedImage apply(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int type = img.getType();
        BufferedImage imgWithOpacity =
                new BufferedImageBuilder(width, height, type).build();
        Graphics2D g = imgWithOpacity.createGraphics();
        g.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity)
        );
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return imgWithOpacity;
    }
}
