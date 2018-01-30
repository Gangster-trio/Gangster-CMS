package com.ganster.cms.core.util;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author bigmeng
 */
public final class StringUtil {
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static final Locale LOCALE_INTERNAL;

    static {
        LOCALE_INTERNAL = Locale.ENGLISH;
    }

    private StringUtil() {
    }

    public static String bytesToString(byte[] bytes, int offset, int length) {
        return new String(bytes, offset, length, UTF8_CHARSET);
    }

    public static String bytesToString(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET);
    }

    public static byte[] stringToBytes(String s) {
        return s.getBytes(UTF8_CHARSET);
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNullOrEmptyAfterTrim(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static String upperCaseInternal(String s) {
        return isNullOrEmpty(s) ? s : s.toUpperCase(LOCALE_INTERNAL);
    }

    public static String lowerCaseInternal(String s) {
        return isNullOrEmpty(s) ? s : s.toLowerCase(LOCALE_INTERNAL);
    }

    public static String getLineSeperator() {
        return System.getProperty("line.separator");
    }

    /** 产生一个随机的字符串，适用于JDK 1.7 */
    public static String randomString(int length, boolean onlyLetter) {
        StringBuilder builder = new StringBuilder(length);
        if (onlyLetter) {
            String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                builder.append(str.charAt(random.nextInt(62)));
            }
        } else
            for (int i = 0; i < length; i++) {
                builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));
            }
        return builder.toString();
    }
}
