package core_java_base.dateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Demo1 {
    public static void main(String[] args) {
        // 获取Date对象， 存放的是时间戳
        Date date = new Date();
        // 获取时间戳（毫秒）
        long time = date.getTime();
        System.out.println("当前时间戳："+time);

        // 当前GMT（格林威治）时间、当前计算机系统所在时区时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("本地（东八区）时间："+simpleDateFormat.format(date)+";GMT时间："+date.toGMTString());

        // 东八区时间转换成东九区（东京）时间，比北京早一个小时
        SimpleDateFormat tokyoFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tokyoFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        System.out.println("东京（东九区）时间："+tokyoFormat.format(date));

        // 时间戳转化成Date
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = timestampFormat.format(time);
        System.out.println(format);

        try {
            Date parseDate = timestampFormat.parse(format);
            System.out.println("时间戳转化成Date之后的时间："+parseDate);

            System.out.println(System.currentTimeMillis());

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
