package utils;

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
        return str == null || "".equals(str.trim()) ;
    }
}
