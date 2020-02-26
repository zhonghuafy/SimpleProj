package org.fe.ek.test.common.util;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;

/**
 * @program: SimpleProj
 * @description: a date format tool
 * @author: Wang Zhenhua
 * @create: 2019-11-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2019-11-20
 **/
@Validated
public class DateFormatUtil {

    private DateFormatUtil(){}

    /**
     * convert given date to a string with 'YYYY-MM-dd HH:mm:ss'
     * @param date
     * @return
     */
    public static String formatDateTime(@NotNull Date date) {
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return date == null ? "" : format.format(date);
    }

    /**
     * format given date to a date string with 'YYYY-MM-dd' format
     * @param date
     * @return
     */
    public static String formatDate(@NotNull Date date) {
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        return date == null ? "" : format.format(date);
    }

    /**
     * format given param to date
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getDate(int year, int month, int day) {
        return Date.from(LocalDate.of(year,month,day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * convert yyyy-MM-dd string to date
     * @param yyyyMMdd
     * @return
     */
    public static Date getDate(@NotNull String yyyyMMdd) {
        LocalDate localDate = LocalDate.parse(yyyyMMdd);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * convert millisecond to date
     * @param millisecond
     * @return
     */
    public static Date getDate(long millisecond) {
        Instant instant = Instant.ofEpochMilli(millisecond);
        return Date.from(instant);
    }

    /**
     * get current year
     * @return
     */
    public static  int getCurYear() {
        return Year.now().getValue();
    }

}
