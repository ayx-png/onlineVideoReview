package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     *      若空，返回true；
     *      若非空，返回false；
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断字符长度是否在3-20之内
     *      若在，返回true
     *      若不在，返回false
     * @param str
     * @return
     */
    public static boolean isLegal(String str){
        int strLen = str.length();
        return strLen >= 3 && strLen <= 20;
    }

    /**
     * 判断是否是有效手机号
     *      若是，返回true
     *      若不是返回false
     * @param str
     * @return
     */
    public static boolean isPhoneNumber(String str){
        String regex = "^1(3([0-35-9]\\d|4[1-8])|4[14-9]\\d|5([0-35689]\\d|7[1-79])|66\\d|7[2-35-8]\\d|8\\d{2}|9[13589]\\d)\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断是否是有效邮箱
     *      若是，返回true
     *      若不是返回false
     * @param str
     * @return
     */
    public static boolean isMail(String str) {
        String regex = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
