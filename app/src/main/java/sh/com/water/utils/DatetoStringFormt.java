package sh.com.water.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/29.
 */

public class DatetoStringFormt {

    public static String strToDateHH(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        //转换
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
        String dateString = formatter2.format(strtodate);
        return dateString;
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
