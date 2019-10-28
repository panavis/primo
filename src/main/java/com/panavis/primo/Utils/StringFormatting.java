package com.panavis.primo.Utils;

import java.util.regex.Pattern;

public class StringFormatting {

    public static final String COLON = ":";
    public static final String SEMI_COLON = ";";
    public static final String EMPTY_STRING = "";
    private static final String LINE_SEPARATOR = "\r\n";
    public static final String DOUBLE_BLANK = LINE_SEPARATOR.concat(LINE_SEPARATOR);

    public static boolean isTextCapitalized(String text) {
        return !text.equals(StringFormatting.EMPTY_STRING) && text.equals(text.toUpperCase());
    }

    public static boolean isCaseSensitive(String text) {
        return !(text.toUpperCase().equals(text.toLowerCase()));
    }

    public static String trimColonsOrSemicolons(String text) {
        if (text == null || text.equals(EMPTY_STRING)) return EMPTY_STRING;

        text = text.trim();
        String modifiedText = text;
        if (text.endsWith(COLON))
            modifiedText = text.substring(0, text.length() - 1).trim();

        if (modifiedText.startsWith(COLON))
            modifiedText = modifiedText.substring(1).trim();

        if (modifiedText.endsWith(SEMI_COLON))
            modifiedText = modifiedText.substring(0, text.length() - 1).trim();

        return modifiedText;
    }

    public static boolean includesNumbers(String text) {
        Pattern digits = Pattern.compile("[0-9]");
        return digits.matcher(text).find();
    }

    public static boolean isFirstLetterCapitalized(String text) {
        if (isTextTrivial(text)) return false;
        String firstLetter = text.substring(0,1);
        return isTextCapitalized(firstLetter);
    }

    public static boolean hasComma(String text) {
        return text.contains(",");
    }

    public static boolean isFirstLetterCaseSensitive(String text) {
        if (isTextTrivial(text)) return false;
        String firstLetter = text.substring(0,1);
        return isCaseSensitive(firstLetter);
    }

    private static boolean isTextTrivial(String text) {
        return text == null || text.equals(EMPTY_STRING);
    }

    public static boolean hasAtLeastNCharacters(String text, int minLength) {
        if (isTextTrivial(text)) return false;
        return text.length() >= minLength;
    }

    public static String duplicateLineSeparator(int numberOfLines) {
        return new String(new char[numberOfLines]).replace("\0", LINE_SEPARATOR);
    }
}
