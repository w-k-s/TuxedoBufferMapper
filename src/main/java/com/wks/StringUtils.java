package com.wks;

class StringUtils {

    static String padLeft(String inputString, int length, char padding) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append(padding);
        }
        sb.append(inputString);

        return sb.toString();
    }

    static String padRight(String inputString, int length, char padding) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder(inputString);
        while (sb.length() < length) {
            sb.append(padding);
        }

        return sb.toString();
    }
}
