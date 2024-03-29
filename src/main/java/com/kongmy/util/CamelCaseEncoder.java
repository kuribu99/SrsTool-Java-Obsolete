/*
 */
package com.kongmy.util;

/**
 *
 * @author Kong My
 */
public class CamelCaseEncoder {

    public static String encode(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String[] arr = str.trim().split("[\\W+]");
        StringBuilder builder = new StringBuilder(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length() > 0) {
                builder.append(Character.toUpperCase(arr[i].charAt(0)));
                if (arr[i].length() > 1) {
                    builder.append(arr[i].substring(1));
                }
            }
        }
        return builder.toString().trim();
    }

    public static String decode(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                builder.append(" ");
            }
            builder.append(Character.toLowerCase(ch));
        }
        return builder.toString().trim();
    }

    public static String transform(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return decode(encode(str));
    }

}
