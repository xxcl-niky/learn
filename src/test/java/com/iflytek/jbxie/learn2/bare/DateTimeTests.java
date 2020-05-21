package com.iflytek.jbxie.learn2.bare;

import org.apache.tomcat.jni.Local;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * 日期时间测试
 *
 * @author jbxie
 * @create 2019/09/18 13:49
 */

public class DateTimeTests {
    public static void main(String [] args) {
//        diffTest();
//        timestampTest();
//        compareDate();
        diffTest2();
    }

    public static void compareDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime2 = LocalDateTime.parse("2019-10-10 23:23:59", dtf1);
        System.out.println("before:" + localDateTime.isBefore(localDateTime2));
        System.out.println("after:" + localDateTime.isAfter(localDateTime2));

    }

    public static void diffTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String localDateTimeStr = localDateTime.format(dtf);
        LocalDateTime localDateTime2 = LocalDateTime.parse(localDateTimeStr + " 23:59:59", dtf1);
        LocalDateTime localDateTime3 = localDateTime2.with(TemporalAdjusters.lastDayOfMonth());
        Long second1 = localDateTime.toEpochSecond(ZoneOffset.of("+8"));
        Long second2 = localDateTime3.toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(localDateTime);
        System.out.println(localDateTimeStr);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
        System.out.println(second1);
        System.out.println(second2);
        System.out.println(second2 - second1);

        System.out.println(localDateTime.isAfter(localDateTime3));
        System.out.println(localDateTime.isBefore(localDateTime3));
    }

    public static void diffTest2() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String localDateTimeStr = localDateTime.format(dtf);
        LocalDateTime localDateTime2 = LocalDateTime.parse(localDateTimeStr + " 00:00:00", dtf1);
        LocalDateTime localDateTime3 = localDateTime2.plusDays(1);
        LocalDateTime localDateTime4 = localDateTime2.plusDays(-1);

        Integer templateUseDays = 0 - 90;
        LocalDateTime compareTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00", dtf1).plusDays(templateUseDays);

    }

    public static void timestampTest() {
        Timestamp t1 = Timestamp.valueOf(LocalDateTime.now());
        System.out.println(t1);
    }
}
