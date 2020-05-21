package com.iflytek.jbxie.learn2.bare;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 随机数测试
 *
 * @author jbxie
 * @create 2020/01/02 9:30
 */

public class RandomTests {
    public static final String ALLCHAR = "abcdefghijklmnopqrstuvwxyz0123456789!@#";

    public static void main(String[]args) {
        randomCodeTest();
    }

    public static void randomCodeTest() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            boolean cycle = true;
            while (cycle) {
                String t =  "yz" + getRandomString(6);
                if (!list.contains(t)) {
                    list.add(t);
                    cycle = false;
                }
            }
        }

        // 生成sql语句
        StringBuilder stringBuilder = new StringBuilder();
        String template = "INSERT INTO `ai_video_user_recharge_card_info` (`amount`, `type`, `code`, `applicant`, `client`, `status`) VALUES (20000, 3, 'REPLACE_COE', '华丽', '邮政', 0);";
        list.stream().forEach(e -> {
            stringBuilder.append(template.replace("REPLACE_COE", e));
        });
        System.out.println(stringBuilder.toString());
    }

    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int j = 0; j < length; j++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }
}
