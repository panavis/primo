package com.panavis.WordToJsonConverter.Utils;

import com.panavis.WordToJsonConverter.Constants.Format;

public class StringFormatting {

    public static boolean isTextCapitalized(String text) {
        return text.equals(text.toUpperCase());
    }

    public static boolean isCaseSensitive(String text) {
        return !(text.toUpperCase().equals(text.toLowerCase()));
    }

    public static String removeStartingOrTrailingColons(String text) {
        text = text.trim();
        String modifiedText = text;
        if (text.endsWith(Format.COLON))
            modifiedText = text.substring(0, text.length() - 1);

        if (modifiedText.startsWith(Format.COLON))
            modifiedText = modifiedText.substring(1).trim();
        return modifiedText.trim();
    }

    public static String duplicateLineSeparator(int numberOfLines) {
        return new String(new char[numberOfLines]).replace("\0", Format.LINE_SEPARATOR);
    }
}
