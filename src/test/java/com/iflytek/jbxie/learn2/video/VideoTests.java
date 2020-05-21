package com.iflytek.jbxie.learn2.video;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.util.*;

/**
 * 视频测试
 *
 * @author jbxie
 * @create 2019/10/09 14:47
 */

public class VideoTests {
    public static void main(String []args) throws IOException {
//        watermarkTest();
//        watermarkTest2();
//        firstFrame();
//        imageAddWatermark();
//        random();
//        videoDefinition("/tmp/nfs/templateDefaultMaterial/46a9a1019f0e4e1cba80/51a3090540d543a9a32e.mp4", "/tmp/nfs/templateDefaultMaterial/46a9a1019f0e4e1cba80/t_1.mp4", 720);
//        concatTwoVideo();
        videoComplexHandleResult();
    }

    public static void watermarkTest() throws IOException {
        FFmpeg ffmpeg = new FFmpeg();
        FFprobe ffprobe = new FFprobe();

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput("v_100.mp4")     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists
                .addOutput("v_100_001.mp4")   // Filename for the destination
                .setFormat("mp4")        // Format is inferred from filename, or can be set
                .addExtraArgs()
                .setVideoCodec("libx264")     // Video using x264
                .setVideoFrameRate(24, 1)     // at 24 frames per second
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        // Run a one-pass encode
        executor.createJob(builder).run();
    }

    public static void watermarkTest2() throws IOException {
        FFmpeg ffmpeg = new FFmpeg();
        FFprobe ffprobe = new FFprobe();
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput("v_100.mp4")     // Filename, or a FFmpegProbeResult
                .addInput("/tmp/nfs/watermark.png")
                .overrideOutputFiles(true) // Override the output if it exists
                .setComplexFilter("overlay=0:0:enable='if(gte(t,1),lt(t,5))'")
                .addOutput("v_100_watermark_001.mp4")   // Filename for the destination
                .setFormat("mp4")        // Format is inferred from filename, or can be set
                .setVideoCodec("libx264")     // Video using x264
                .setVideoFrameRate(24, 1)     // at 24 frames per second
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        // Run a one-pass encode
        executor.createJob(builder).run();
    }

    public static void firstFrame() throws IOException {
        FFmpeg ffmpeg = new FFmpeg();
        FFprobe ffprobe = new FFprobe();
        FFmpegBuilder builder = new FFmpegBuilder()
                //Filename
                .setInput("v_100_watermark_001.mp4  ")
                //是否覆盖
                .overrideOutputFiles(true)
                //输出名
                .addOutput("v_100_watermark_first_frame_001.png")
                .setFrames(1)
                .setVideoFilter("select='gte(n\\,1)'")
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        // Run a one-pass encode
        executor.createJob(builder).run();
    }

    public static void imageAddWatermark() throws IOException {
        Thumbnails.of("v_100_watermark_first_frame_001.png")
                .size(1280, 720)
                .keepAspectRatio(false)
                .watermark(ImageIO.read(new File("/tmp/nfs/watermark.png")))
                .toFile("v_100_watermark_first_frame_002.png");
    }

    public static void videoDefinition(String inputFile, String outputPath, int definition) throws IOException {
        FFmpegBuilder builder = new FFmpegBuilder()
                //Filename
                .setInput(inputFile)
//                .addExtraArgs("-c", "copy")
//                .setAudioFilter("copy")
                .setAudioFilter("libx264")
                .setAudioFilter("scale=-2:"+definition)
                //是否覆盖
                .overrideOutputFiles(true)
                //输出名
                .addOutput(outputPath)
                .done();
        new FFmpegExecutor(new FFmpeg(), new FFprobe()).createJob(builder).run();
    }

    public static void concatTwoVideo() throws IOException {
        String titleFile = "/tmp/nfs/videoTitles.mp4";
        String videoFile = "/tmp/nfs/fd2c8dfbe79b4956a8f8564520c3f9a2.mp4";
        String watermarkFile = "/tmp/nfs/watermark_1_1.png";
        String outFile = "/tmp/nfs/out_1.mp4";
        int tw = 1920;
        int th = 1080;
        String cropSize = "1920:1080";
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(titleFile)
//                .setAudioFilter("crop=" + cropSize + ",scale=" + tw + ":" + th)
                .addInput(videoFile)
                .addInput(watermarkFile)
                .overrideOutputFiles(true)
                .setComplexFilter("[0:v]scale=1920:1080[v1];[v1][1:v]concat=n=2:v=1:a=0[ov1];[ov1]overlay=;[0:a][1:a]concat=n=2:v=0:a=1[oa]")
                .addOutput(outFile)
                .addExtraArgs("-map", "[ov]")
                .addExtraArgs("-map", "[oa]")
                .done();
        new FFmpegExecutor(new FFmpeg(), new FFprobe()).createJob(builder).run();
    }

    public static void videoComplexHandleResult() throws  IOException{
        String prodWatermark = "/tmp/nfs/prodWatermark.png";
        String videoTitles = "/tmp/nfs/videoTitles.mp4";
        String outputPath = "/tmp/nfs/v3_1.mp4";
//        String productionUrl = "/tmp/nfs/e0c6e444-bab2-44d6-987e-b85228581e29_test.mp4";
        String productionUrl = "/tmp/nfs/test.mp4";
        Map<String, String> videos = new HashMap<>();
        videos.put("width", "1080");
        videos.put("height", "1920");
        videos.put("videoFile", productionUrl);
        Map<String, String> titleVideos = new HashMap<>();
        Map<String, String> watermarks = new HashMap<>();
        // 判断是否需要加片头和品牌logo
        // 所有免费模板&&非企业模板 添加成品片头
        titleVideos.put("isAddVideoTitle", "false");
        titleVideos.put("width", "1920");
        titleVideos.put("height", "1080");
        titleVideos.put("titleVideoFile", videoTitles);
        // 非企业模板添加品牌logo
        watermarks.put("isAddWatermark", "false");
        watermarks.put("overlay", "overlay=" + 630 + ":" + 1800);
        watermarks.put("watermarkFile", prodWatermark);
        videoComplexHandle(videos, titleVideos, watermarks, outputPath);
    }

    /**
     * 视频复杂处理(isAddVideoTitle||isAddWatermark需要为true才处理)
     * @param videos 原视频
     * @param titleVideos 片头视频
     * @param watermarks 水印
     * @param outputPath 输出路径
     * @throws IOException
     */
    public static void videoComplexHandle(Map<String, String> videos, Map<String, String> titleVideos, Map<String, String> watermarks, String outputPath) throws IOException {
        boolean isAddVideoTitle = Boolean.parseBoolean(titleVideos.get("isAddVideoTitle"));
        boolean isAddWatermark = Boolean.parseBoolean(watermarks.get("isAddWatermark"));
        int videoWidth = Integer.parseInt(videos.get("width"));
        boolean originalNeedCrop = false;
        if (videoWidth == 1088) {
            videoWidth = videoWidth - 8;
            originalNeedCrop = true;
        }
        if (!isAddVideoTitle && !isAddWatermark && !originalNeedCrop) {
            return;
        }
        int videoHeight = Integer.parseInt(videos.get("height"));
        List<String> inputs = new ArrayList<>();
        String titleScale = "";
        String concatVideo = "";
        String concatAudio = "";
        String cropSize = "0:0";
        String originalCrop = "";
        String ov = "[ov]";
        String oa = "[oa]";
        if (originalNeedCrop) {
            originalCrop = "[0:v]crop=" + videoWidth + ":"+ videoHeight + ":" + 4 + ":" + 0 + ",scale=" + videoWidth + ":" + videoHeight;
            if (!isAddVideoTitle && !isAddWatermark) {
                originalCrop = originalCrop + ov + ";";
                concatAudio = "[0:a]concat=n=1:v=0:a=1" + oa;
            } else {
                originalCrop = originalCrop + "[0ov];";
            }
        }
        if (isAddVideoTitle) {
            cropSize = getCropSize(Integer.parseInt(titleVideos.get("width")), Integer.parseInt(titleVideos.get("height")), videoWidth, videoHeight);
            titleScale = "[1:v]crop=" + cropSize + ",scale=" + videoWidth + ":" + videoHeight+ "[v1];";
            inputs.add(titleVideos.get("titleVideoFile"));
            if (originalNeedCrop) {
                concatVideo = "[v1][0ov]concat=n=2:v=1:a=0";
            } else {
                concatVideo = "[v1][0:v]concat=n=2:v=1:a=0";
            }
            concatAudio = "[1:a][0:a]concat=n=2:v=0:a=1[oa]";
        }
        if (isAddWatermark) {
            inputs.add(watermarks.get("watermarkFile"));
            if (isAddVideoTitle) {
                concatVideo = concatVideo + "[ov1];[ov1][2:v]" + watermarks.get("overlay") + ov + ";";
            } else {
                if (originalNeedCrop) {
                    concatVideo = concatVideo + "[0ov][1:v]" + watermarks.get("overlay") + ov + ";";
                } else {
                    concatVideo = concatVideo + "[0:v][1:v]" + watermarks.get("overlay") + ov + ";";
                }
                concatAudio = "[0:a]concat=n=1:v=0:a=1" + oa;
            }
        } else {
            if (isAddVideoTitle) {
                concatVideo = concatVideo + ov + ";";
            }
        }
        String complexFilter = originalCrop + titleScale + concatVideo + concatAudio;
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(videos.get("videoFile"))
                .addExtraArgs("-threads", "8");
        for (int i = 0; i < inputs.size(); i ++) {
            builder.addInput(inputs.get(i));
        }
        builder = builder.overrideOutputFiles(true)
                .setComplexFilter(complexFilter)
                .addExtraArgs("-threads", "8")
                .addOutput(outputPath)
                .addExtraArgs("-map", ov)
                .addExtraArgs("-map", oa)
                .done();
        new FFmpegExecutor(new FFmpeg(), new FFprobe()).createJob(builder).run();
    }

    /**
     * 视频复杂处理(isAddVideoTitle||isAddWatermark需要为true才处理)
     * @param videos 原视频
     * @param titleVideos 片头视频
     * @param watermarks 水印
     * @param outputPath 输出路径
     * @throws IOException
     */
    public static void videoComplexHandle_bak(Map<String, String> videos, Map<String, String> titleVideos, Map<String, String> watermarks, String outputPath) throws IOException {
        boolean isAddVideoTitle = Boolean.parseBoolean(titleVideos.get("isAddVideoTitle"));
        boolean isAddWatermark = Boolean.parseBoolean(watermarks.get("isAddWatermark"));
        int videoWidth = Integer.parseInt(videos.get("width"));
        int videoHeight = Integer.parseInt(videos.get("height"));
        List<String> inputs = new ArrayList<>();
        String titleScale = "";
        String concatVideo = "";
        String concatAudio = "";
        String cropSize = "0:0";
        if (isAddVideoTitle) {
            cropSize = getCropSize(Integer.parseInt(titleVideos.get("width")), Integer.parseInt(titleVideos.get("height")), videoWidth, videoHeight);
            titleScale = "[1:v]crop=" + cropSize + ",scale=" + videoWidth + ":" + videoHeight+ "[v1];";
            inputs.add(titleVideos.get("titleVideoFile"));
            concatVideo = "[v1][0:v]concat=n=2:v=1:a=0";
            concatAudio = "[1:a][0:a]concat=n=2:v=0:a=1[oa]";
        }
        if (isAddWatermark) {
            inputs.add(watermarks.get("watermarkFile"));
            if (isAddVideoTitle) {
                concatVideo = concatVideo + "[ov1];[ov1][2:v]" + watermarks.get("overlay") + "[ov];";
            } else {
                concatVideo = concatVideo + "[0:v][1:v]" + watermarks.get("overlay") + "[ov];";
                concatAudio = "[0:a]concat=n=1:v=0:a=1[oa]";
            }
        } else {
            if (isAddVideoTitle) {
                concatVideo = concatVideo + "[ov];";
            }
        }
        String complexFilter = titleScale + concatVideo + concatAudio;
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(videos.get("videoFile"));
        for (int i = 0; i < inputs.size(); i ++) {
            builder.addInput(inputs.get(i));
        }
        builder = builder.overrideOutputFiles(true)
                .setComplexFilter(complexFilter)
                .addOutput(outputPath)
                .addExtraArgs("-map", "[ov]")
                .addExtraArgs("-map", "[oa]")
                .done();
        new FFmpegExecutor(new FFmpeg(), new FFprobe()).createJob(builder).run();
    }

    /**
     * 获取裁剪作为高斯模糊背景size
     *
     * @param videoWidth    videoWidth
     * @param videoHeight   videoHeight
     * @param tw            targetWidth
     * @param th            targetHeight
     * @return              String
     */
    private static String getCropSize(int videoWidth, int videoHeight, int tw, int th) {

        BigDecimal bdWidthRatio = new BigDecimal(tw).divide(new BigDecimal(videoWidth), 2, RoundingMode.HALF_EVEN);
        BigDecimal bdHeightRatio = new BigDecimal(th).divide(new BigDecimal(videoHeight), 2, RoundingMode.HALF_EVEN);

        return bdWidthRatio.compareTo(bdHeightRatio) >= 0 ? resizeByWidth(videoWidth, videoHeight, tw, th) :
                resizeByHeight(videoWidth, videoHeight, tw, th);
    }

    /**
     * 通过宽度resize
     *
     * @param videoWidth
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    private static String resizeByWidth(int videoWidth, int videoHeight, int targetWidth, int targetHeight) {
        int resizeHeight = new BigDecimal(videoWidth).divide(new BigDecimal(targetWidth), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(targetHeight)).intValue();
        resizeHeight = resizeHeight > videoHeight ? videoHeight : resizeHeight;
        return  videoWidth + ":" + resizeHeight;
    }

    /**
     * 通过高度resize
     *
     * @param videoHeight
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    private static String resizeByHeight(int videoWidth, int videoHeight, int targetWidth, int targetHeight) {
        int resizeWidth = new BigDecimal(videoHeight).divide(new BigDecimal(targetHeight), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(targetWidth)).intValue();
        resizeWidth = resizeWidth > videoWidth ? videoWidth : resizeWidth;
        return  resizeWidth + ":" + videoHeight;
    }

    public static void random() {
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID().toString());
        System.out.println( UUID.randomUUID().toString().replace("-", ""));
    }

}
