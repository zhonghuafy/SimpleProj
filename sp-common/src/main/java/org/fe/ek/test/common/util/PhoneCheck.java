package org.fe.ek.test.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: SimpleProj
 * @description: check Chinese mobile number
 * @author: Wang Zhenhua
 * @create: 2019-03-26
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2019-03-26
 **/
public final class PhoneCheck {

    private PhoneCheck(){}

    public final static String REGEX = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    public static boolean valid(final String phone) {
        String regex = REGEX;
        if (phone == null || phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }
}
