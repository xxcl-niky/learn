package com.iflytek.jbxie.learn2.bare;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 高精度数据处理
 *
 * @author jbxie
 * @create 2019/11/11 15:01
 */

public class BigDecimalTests {
    public static void main(String [] args) {
        roundingMode();
    }

    public static void roundingMode() {
        System.out.println((new BigDecimal("2.35")).setScale(1, RoundingMode.UP).toString());
        System.out.println((new BigDecimal("2.35")).setScale(1, RoundingMode.HALF_UP).toString());

        System.out.println((new BigDecimal("2.33")).setScale(1, RoundingMode.UP).toString());
        System.out.println((new BigDecimal("2.33")).setScale(1, RoundingMode.HALF_UP).toString());
    }
}
