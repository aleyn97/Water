package sh.com.water.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/29.
 */

public class DatetoStringFormt {

    public static String StringToStrLong(String strDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");        // 实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        // 实例化模板对象
        Date d = null;
        try {
            d = sdf1.parse(strDate);   // 将给定的字符串中的日期提取出来
        } catch (Exception e) {        // 如果提供的字符串格式有错误，则进行异常处理
            e.printStackTrace();       // 打印异常信息
        }
        return sdf2.format(d);
    }

    public static String getDateShort(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(date);
        return startTime;
    }

    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
}
