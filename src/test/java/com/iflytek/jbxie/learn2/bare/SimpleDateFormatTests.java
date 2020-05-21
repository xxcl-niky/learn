package com.iflytek.jbxie.learn2.bare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * SimpleDateFormat线程不安全测试
 *
 * @author jbxie
 * @create 2020/02/15 22:06
 */

public class SimpleDateFormatTests {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String format(Date date) {
        return simpleDateFormat.format(date);
    }
    private static Date parse(String dateStr) throws ParseException {
        return simpleDateFormat.parse(dateStr);
    }

    public static void main(String [] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String [] dataStrs = new String [] {
                "2016-01-01 10:24:00",
                "2016-01-02 20:48:00",
                "2016-01-11 12:24:00"
        };
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 10; i++) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "\t" + parse(dataStrs[i % dataStrs.length]));
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
