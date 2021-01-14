package com.github.shuaidd;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-14 09:47
 **/
public class MainTest {
    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtils.parseDate("2020-01-14 8:00:00","yyyy-MM-dd HH:mm:ss").getTime());
        System.out.println(DateUtils.parseDate("2020-01-14 10:00:00","yyyy-MM-dd HH:mm:ss").getTime());
        System.out.println(DateFormatUtils.format(new Date(1571274600),"yyyy-MM-dd HH:mm:ss"));
    }
}
