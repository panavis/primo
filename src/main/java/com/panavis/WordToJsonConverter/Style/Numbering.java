package com.panavis.WordToJsonConverter.Style;

import com.panavis.WordToJsonConverter.Constants.Format;
import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigInteger;
import java.util.*;

public class Numbering {

    private final XWPFNumbering numbering;
    private List<String> uniqueNumIds;
    private Map<String, Integer> numberingTracker;
    private Map<String, String> numberingFormatNames;
    private Map<String, String> numberingFormatDisplays;
    private Map<String, Integer> numberingCountStart;
    private Map<Integer, UnitNumbering> unitNumberings;
    private List<XWPFParagraph> paragraphs;

    private Numbering(XWPFNumbering numbering, List<XWPFParagraph> paragraphs) {
        this.paragraphs = paragraphs;
        this.numbering = numbering;
        this.uniqueNumIds = new ArrayList<>();
        this.numberingTracker = new HashMap<>();
        this.numberingFormatNames = new HashMap<>();
        this.numberingFormatDisplays = new HashMap<>();
        this.numberingCountStart = new HashMap<>();
        this.unitNumberings = new HashMap<>();
    }

    public static Map<Integer, UnitNumbering> getParagraphsNumbering(XWPFNumbering numbering, List<XWPFParagraph> paragraphs) {
        Numbering docNumbering = new Numbering(numbering, paragraphs);

        for (int i = 0; i < paragraphs.size(); i++) {
            XWPFParagraph paragraph = paragraphs.get(i);
            String paragraphStyle = paragraph.getStyle();

            if (paragraphStyle != null &&
                    (   paragraphStyle.equals(LIST_PARAGRAPH) ||
                            (paragraphStyle.startsWith(HEADING) &&
                                    paragraph.getNumIlvl() != null)))
            {
                BigInteger numId = paragraph.getNumID();
                int levelValue = paragraph.getNumIlvl().intValue();
                String uniqueId = numId.toString() + "_" + levelValue;
                docNumbering.saveNumberingItem(uniqueId);

                if (!docNumbering.uniqueNumIds.contains(uniqueId)) {
                    docNumbering.uniqueNumIds.add(uniqueId);
                    docNumbering.saveNumIdProperties(paragraph, numId, uniqueId);
                }
                docNumbering.addParagraphNumberingPrefix(i, uniqueId, paragraphStyle);
            }
            if (!docNumbering.unitNumberings.containsKey(i))
                docNumbering.addDefaultNumberingPrefix(i);
        }
        return docNumbering.unitNumberings;
    }

    private void saveNumIdProperties(XWPFParagraph paragraph, BigInteger numId, String uniqueId) {
        CTLvl abstractNumLvl = this.getAbstractNumLvl(paragraph, numId);
        this.saveNumCountStart(uniqueId, abstractNumLvl);
        this.saveNumFormatName(uniqueId, abstractNumLvl);
        this.saveNumFormatDisplay(uniqueId, abstractNumLvl);
    }

    private CTLvl getAbstractNumLvl(XWPFParagraph paragraph, BigInteger numId) {
        BigInteger abstractNumIndex = this.numbering.getAbstractNumID(numId);
        XWPFAbstractNum abstractNum = this.numbering.getAbstractNum(abstractNumIndex);
        CTAbstractNum ctAbstractNum = abstractNum.getCTAbstractNum();
        int abstractNumLvlIndex = paragraph.getNumIlvl().intValue();
        return ctAbstractNum.getLvlArray(abstractNumLvlIndex);
    }

    private void addParagraphNumberingPrefix(int paragraphIndex, String uniqueId, String style) {
        UnitNumbering unitNumbering = this.getNumberingPrefix(uniqueId, style);
        unitNumbering.current  = unitNumbering.current + "\t";
        this.unitNumberings.put(paragraphIndex, unitNumbering);
    }

    private UnitNumbering getNumberingPrefix(String uniqueId, String style) {
        String formatName = this.numberingFormatNames.get(uniqueId);
        String formatDisplay = this.numberingFormatDisplays.get(uniqueId);
        int currentNumber = this.numberingTracker.get(uniqueId) +
                this.numberingCountStart.get(uniqueId) - 1;

        UnitNumbering unitNumbering = new UnitNumbering("", "", style);
        String digit = String.valueOf(currentNumber);
        String nextDigit = String.valueOf(currentNumber + 1);
        switch (formatName) {
            case "decimal": {
                String current = formatDisplay.replaceAll("%\\d+", digit);
                String next = formatDisplay.replaceAll("%\\d+", nextDigit);
                unitNumbering = new UnitNumbering(current, next, style);
                break;
            }
            case "lowerLetter": {
                String currentLetter = getLowerLetter(currentNumber);
                String nextLetter = getLowerLetter(currentNumber + 1);
                String currentString = getFormattedString(formatDisplay, currentLetter);
                String nextString = getFormattedString(formatDisplay, nextLetter);
                unitNumbering = new UnitNumbering(currentString, nextString, style);
                break;
            }
            case "upperLetter": {
                String currentLetter = getUpperLetter(currentNumber);
                String nextLetter = getUpperLetter(currentNumber + 1);
                String currentString = getFormattedString(formatDisplay, currentLetter);
                String nextString = getFormattedString(formatDisplay, nextLetter);
                unitNumbering = new UnitNumbering(currentString, nextString, style);
                break;
            }
            case "upperRoman": {
                String currentLetter = RomanNumber.toRoman(currentNumber);
                String nextLetter = RomanNumber.toRoman(currentNumber + 1);
                String currentString = getFormattedString(formatDisplay, currentLetter);
                String nextString = getFormattedString(formatDisplay, nextLetter);
                unitNumbering = new UnitNumbering(currentString, nextString, style);
                break;
            }
            case "lowerRoman": {
                String currentLetter = RomanNumber.toRoman(currentNumber).toLowerCase();
                String nextLetter = RomanNumber.toRoman(currentNumber + 1).toLowerCase();
                String currentString = getFormattedString(formatDisplay, currentLetter);
                String nextString = getFormattedString(formatDisplay, nextLetter);
                unitNumbering = new UnitNumbering(currentString, nextString, style);
                break;
            }
            case "bullet": {
                unitNumbering = new UnitNumbering("-", "-", style);
                break;
            }
        }
        return unitNumbering;
    }

    private String getLowerLetter(int code) {
        return Character.toString((char) (96 + code));
    }

    private String getUpperLetter(int code) {
        return Character.toString((char) (64 + code));
    }

    private String getFormattedString(String template, String letter) {
        return template.replaceAll("%\\d", letter);
    }

    private void addDefaultNumberingPrefix(int paragraphIndex) {
        this.unitNumberings.put(paragraphIndex, new UnitNumbering(Format.EMPTY_STRING,
                Format.EMPTY_STRING, "Text"));
    }

    private void saveNumFormatDisplay(String uniqueid, CTLvl abstractNumLvl) {
        String numDisplay = abstractNumLvl.getLvlText().getVal();
        this.numberingFormatDisplays.put(uniqueid, numDisplay);
    }

    private void saveNumFormatName(String uniqueId, CTLvl abstractNumLvl) {
        String numFormat = abstractNumLvl.getNumFmt().getVal().toString();
        this.numberingFormatNames.put(uniqueId, numFormat);

    }

    private void saveNumCountStart(String uniqueId, CTLvl abstractNumLvl) {
        try {
            int numStart = abstractNumLvl.getStart().getVal().intValue();
            this.numberingCountStart.put(uniqueId, numStart);
        }
        catch (NullPointerException e) {
            this.numberingCountStart.put(uniqueId, 0);
        }
    }

    private void saveNumberingItem(String uniqueId) {
        if (this.numberingTracker.containsKey(uniqueId)) {
            int currentValue = this.numberingTracker.get(uniqueId);
            this.numberingTracker.put(uniqueId, currentValue + 1);
        }
        else {
            this.numberingTracker.put(uniqueId, 1);
        }
    }
}

class RomanNumber {

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }

}