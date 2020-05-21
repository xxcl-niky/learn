package com.iflytek.jbxie.learn2.image;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.imgcodecs.Imgcodecs.imwrite;

/**
 * @author jbxie
 * @create 2020/02/19 13:14
 */

public class OpenCvTest {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径
        //-Djava.library.path=$PROJECT_DIR$\opencv\x64
    }
    public static void main(String[] args) {
        Mat mat = Imgcodecs.imread("D:\\tmp\\image-processing\\source.jpg");
        System.out.println(mat);
    }
}
