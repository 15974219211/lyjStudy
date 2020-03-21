package com.my.stufy.utlis;



import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
@Slf4j
public class StringToListHelper {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("([,0-9a-zA-Z_]*)");

    /**
     * 将字符串中的数字转整型集合
     * 举例：1,2,3,4, , ->  整型集合
     */
    public static List<Integer> splitToListInt(String str){
        // 判断字符串中是否含有字母或特殊符号
        if (!isNumber(str)) {
            throw new IllegalArgumentException("无法转成集合");
        }
        // 按逗号分隔所有整型，并移除空格
        List<String> stringList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        return stringList.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    /**
     * 将字符串中的数字转整型集合
     * 举例：1,2,3,4, , ->  整型集合
     */
    public static List<String> splitToListString(String str){
        // 判断字符串中是否含有字母或特殊符号
        if (!isNumber(str)) {
            throw new IllegalArgumentException("无法转成集合");
        }
        // 按逗号分隔所有整型，并移除空格
        return  Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
    }

    /**
     * 判断一个字符串是否都为数字和逗号
     */
    public static boolean isNumber(String strNum) {
        Matcher matcher = NUMBER_PATTERN.matcher((CharSequence) strNum);
        return matcher.matches();
    }
}
