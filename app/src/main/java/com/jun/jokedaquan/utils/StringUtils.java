package com.jun.jokedaquan.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 字符串工具
 */
public class StringUtils {

    /**
     * 判断字符串是否为数字
     *
     * @param str 字符串
     * @return 是否为数字
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 字符串检查
     *
     * @param str 待检查字符串
     * @return 判断结果
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 将空字符串转为""
     *
     * @param str 待修改字符串
     * @return 修改结果
     */
    public static String setNullToEmpty(String str) {
        return str == null ? "" : str;
    }

    /**
     * 将空字符串转为"null"
     *
     * @param str 待修改字符串
     * @return 修改结果
     */
    public static String setNullToString(String str) {
        return str == null ? "null" : str;
    }


    /**
     * 转为HexString
     *
     * @param data 待转码字节数组
     * @return 转码字符串
     */
    public static String toHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int v : data) {
            int hs = v & 0xff;
            if (hs < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(hs));
        }
        return sb.toString().toLowerCase(Locale.getDefault());
    }

    /**
     * 获取SpannableStringBuilder
     *
     * @param text        字符串源
     * @param colors      颜色
     * @param foregrounds 是否为前景
     * @param args        修改的文字
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder getSpannableStringBuilder(String text,
                                                                   int[] colors, boolean[] foregrounds, String... args) {
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        int start;
        int end;
        for (int i = 0; i < args.length; i++) {
            start = text.indexOf(args[i]);
            if (start == -1) {
                continue;
            }
            end = start + args[i].length();
            boolean f;
            if (i < foregrounds.length) {
                f = foregrounds[i];
            } else {
                f = foregrounds[foregrounds.length - 1];
            }
            if (f) {
                style.setSpan(
                        new ForegroundColorSpan(colors[i % colors.length]),
                        start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                style.setSpan(
                        new BackgroundColorSpan(colors[i % colors.length]),
                        start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return style;
    }

    /**
     * 插值并获取前景SpannableStringBuilder
     *
     * @param strRes 字符串源
     * @param color  颜色
     * @param args   插值参数
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder getFormatedForegroundSpannableStringBuilder(
            String strRes, int color, Object... args) {
        return getFormatedSpannableStringBuilder(strRes, new int[]{color},
                new boolean[]{true}, args);
    }

    /**
     * 插值并获取前景SpannableStringBuilder(多个字符串源，多个插值参数)
     *
     * @param color    颜色
     * @param hintInfo 参数格式按 hintInfo[0]字符串源 hintInfo[1] 插值参数   hintInfo长度必须为偶数
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder getMuchFormatedForegroundSpannableStringBuilder(
            int color, Object... hintInfo) {
        if (hintInfo.length % 2 != 0) {
            throw new IllegalArgumentException("Illegal argument exception: The length of hintInfo must be even");
        }
        SpannableStringBuilder colorInfo = new SpannableStringBuilder();
        for (int index = 0; index < hintInfo.length; index = index + 2) {

            if (!(hintInfo[index] instanceof String)) {
                throw new ClassCastException("Class cast exception: Even the value of the index must be java.lang.String");
            }
            colorInfo.append(StringUtils.getFormatedForegroundSpannableStringBuilder(
                    (String) hintInfo[index],
                    color,
                    hintInfo[index + 1]));
        }
        return colorInfo;
    }

    /**
     * 插值并获取背景SpannableStringBuilder
     *
     * @param strRes 字符串源
     * @param color  颜色
     * @param args   插值参数
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder getFormatedBackgroundSpannableStringBuilder(
            String strRes, int color, Object... args) {
        return getFormatedSpannableStringBuilder(strRes, new int[]{color},
                new boolean[]{false}, args);
    }

    /**
     * 插值并获取SpannableStringBuilder
     *
     * @param strRes      字符串源
     * @param colors      颜色
     * @param foregrounds 是否为前景
     * @param args        插值参数
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder getFormatedSpannableStringBuilder(
            String strRes, int[] colors, boolean[] foregrounds, Object... args) {
        String strResult = String.format(Locale.getDefault(), strRes, args);
        SpannableStringBuilder style = new SpannableStringBuilder(strResult);
        int start;
        int end;
        int total = 0;
        for (int i = 0; i < args.length; i++) {
            start = strRes.indexOf("%" + (i + 1) + "$") + total - 4 * i;
            if (start == -1) {
                continue;
            }
            final int number = args[i].toString().length();
            end = start + number;
            total += number;
            boolean f;
            if (i < foregrounds.length) {
                f = foregrounds[i];
            } else {
                f = foregrounds[foregrounds.length - 1];
            }
            if (f) {
                style.setSpan(
                        new ForegroundColorSpan(colors[i % colors.length]),
                        start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                style.setSpan(
                        new BackgroundColorSpan(colors[i % colors.length]),
                        start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return style;
    }

    /**
     * 二进制转十六进制
     *
     * @param buf 二进制数组
     * @return 十六进制字符
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 十六进制转二进制
     *
     * @param hexStr 十六进制字符
     * @return 二进制数组
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 获取长宽
     *
     * @param location 长宽
     */
    public static void getLocationByUrl(String url, int[] location) {
        if (url == null)
            return;
        int width = 0;
        int height = 0;
        String[] wxh = url.split("_|\\.jpg|\\.jpeg|\\.png|\\.bmp");
        if (wxh.length == 2) {
            String[] wh = wxh[1].split("x");
            if (wh.length == 2) {
                try {
                    width = Integer.parseInt(wh[0]);
                    height = Integer.parseInt(wh[1]);
                } catch (NumberFormatException e) {
                    width = 0;
                    height = 0;
                }
            }
        }
        location[0] = width;
        location[1] = height;
    }

    /**
     * 字符串转换成int
     *
     * @param strValue     字符串
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static int convert2Int(String strValue, int defaultValue) {
        if (strValue == null || "".equals(strValue.trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(strValue);
        } catch (Exception e) {
            try {
                return Double.valueOf(strValue).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     * 字符串转换成放大倍数的int
     *
     * @param strValue     字符串
     * @param multiple     放大倍数
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static int convert2MultipleInt(String strValue, int multiple, int defaultValue) {
        if (strValue == null || "".equals(strValue.trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(strValue) * multiple;
        } catch (Exception e) {
            try {
                return (int) Math.floor(Double.valueOf(strValue) * multiple);
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     * 字符串转换成long
     *
     * @param strValue     字符串
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static long convert2Long(String strValue, int defaultValue) {
        if (strValue == null || "".equals(strValue.trim())) {
            return defaultValue;
        }
        try {
            return Long.valueOf(strValue);
        } catch (Exception e) {
            try {
                return Double.valueOf(strValue).longValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     * 获取字符串自定义的字节数(2字节以上的字符一律按2字节统计)
     *
     * @param strValue 字符串
     * @return 自定义字符串字节数
     */
    public static int getCustomBytes(String strValue) {
        int count = 0;
        if (strValue == null || "".equals(strValue.trim())) {
            return 0;
        }
        int length = strValue.length();
        for (int index = 0; index < length; index++) {
            String s = strValue.charAt(index) + "";
            byte[] by = s.getBytes();
            if (by.length == 1) {
                count++;
            } else {
                count = count + 2;
            }
        }
        return count;
    }


    public static boolean isMobilePhoneNum(String phoneNum) {
        return phoneNum != null && phoneNum.matches("[1][3458]\\d{9}");
    }

    /**
     * 手机号码，身份证证等隐私内容加*
     *
     * @param str       要加*的字符串
     * @param frontLen  *前面预留长度
     * @param behindLen *后面预留长度
     * @return 加*后的字符串
     */
    public static String convert2Star(String str, int frontLen, int behindLen) {
        int strLen = str.length();
        if (frontLen >= strLen || behindLen >= strLen || frontLen + behindLen >= strLen) {
            return str;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(str.substring(0, frontLen));
        int starCount = strLen - frontLen - behindLen;
        for (int i = 0; i < starCount; i++) {
            builder.append("\u002A");
        }
        return builder.append(str.substring(strLen - behindLen, strLen)).toString();
    }

    /**
     * 时间字符串转换为分钟数,比如10:00就是600,11:40就是700
     *
     * @param str 要转换的时间字符串(包含":")
     * @return 大于-1转换成功
     */
    public static int timeConvert2Minutes(String str) {

        int minutes = -1;

        if (str.contains(":")) {
            String[] convertedTime;
            convertedTime = str.split(":");
            if (convertedTime.length == 2) {
                int h = convert2Int(convertedTime[0], -1);
                int m = convert2Int(convertedTime[1], -1);

                //小时分钟都转换成功
                if (h != -1 && m != -1)
                    minutes = h * 60 + m;
            }
        }

        return minutes;
    }

    /**
     * 改变规定位置的字符串颜色与字号*
     *
     * @param str   变更目标内容
     * @param size  变更位置的字号（px）
     * @param star  变更起点
     * @param end   变更终点
     * @param color 变更颜色
     * @return 变更完成后的 SpannableStringBuilder
     */
    public static SpannableStringBuilder getSpannableStringBuilder(String str, int size, int star, int end, String color) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        if (size != 0)
            style.setSpan(new AbsoluteSizeSpan(size), star, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.parseColor(color)), star, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

    /**
     * 去掉字符串前后所有的空格
     *
     * @param str 字符串
     * @return 字符串
     */
    public static String trim(String str) {
        while (str.startsWith(" ")) {
            str = str.substring(1, str.length()).trim();
        }
        while (str.endsWith(" ")) {
            str = str.substring(0, str.length() - 1).trim();
        }
        return str;
    }

    /**
     * 判断是否是一个IP
     *
     * @param IP 字符串
     * @return 是否是一个IP
     */
    public static boolean isIp(String IP) {
        boolean b = false;
        IP = trim(IP);
        if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            String s[] = IP.split("\\.");
            if (Integer.parseInt(s[0]) < 255)
                if (Integer.parseInt(s[1]) < 255)
                    if (Integer.parseInt(s[2]) < 255)
                        if (Integer.parseInt(s[3]) < 255)
                            b = true;
        }
        return b;
    }

    /**
     * 判断是否中文
     * GENERAL_PUNCTUATION 判断中文的“号
     * CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
     * HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
     *
     * @param c 字符
     * @return 是否中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    /**
     * 判断字符串是否包含中文
     *
     * @param str 字符串
     * @return 字符串是否包含中文
     */
    public static boolean containsChinese(String str) {
        if (str == null)
            return false;
        for (char c : str.toCharArray()) {
            if (StringUtils.isChinese(c))
                return true;
        }
        return false;
    }
}
