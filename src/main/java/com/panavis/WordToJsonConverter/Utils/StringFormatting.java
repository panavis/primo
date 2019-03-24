package com.panavis.WordToJsonConverter.Utils;

public class StringFormatting {

    public static final String COLON = ":";
    public static final String EMPTY_STRING = "";
    private static final String LINE_SEPARATOR = "\r\n";
    public static final String DOUBLE_BLANK = LINE_SEPARATOR.concat(LINE_SEPARATOR);

    public static boolean isTextCapitalized(String text) {
        return text.equals(text.toUpperCase());
    }

    public static boolean isCaseSensitive(String text) {
        return !(text.toUpperCase().equals(text.toLowerCase()));
    }

    public static String trimColons(String text) {
        text = text.trim();
        String modifiedText = text;
        if (text.endsWith(COLON))
            modifiedText = text.substring(0, text.length() - 1);

        if (modifiedText.startsWith(COLON))
            modifiedText = modifiedText.substring(1).trim();
        return modifiedText.trim();
    }

    public static String duplicateLineSeparator(int numberOfLines) {
        return new String(new char[numberOfLines]).replace("\0", LINE_SEPARATOR);
    }
}
