package com.panavis.WordToJsonConverter.Style.Numbering;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;

import static com.panavis.WordToJsonConverter.Utils.StringFormatting.*;

import com.panavis.WordToJsonConverter.Style.UnitNumbering;
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
    private Map<String, Integer> previousUnitNumbering;

    private Numbering(XWPFNumbering numbering) {
        this.numbering = numbering;
        this.uniqueNumIds = new ArrayList<>();
        this.numberingTracker = new HashMap<>();
        this.numberingFormatNames = new HashMap<>();
        this.numberingFormatDisplays = new HashMap<>();
        this.numberingCountStart = new HashMap<>();
        this.unitNumberings = new HashMap<>();
        this.previousUnitNumbering = new HashMap<>();
    }

    public static Map<Integer, UnitNumbering> getParagraphsNumbering(XWPFNumbering numbering, List<XWPFParagraph> paragraphs) {
        Numbering docNumbering = new Numbering(numbering);

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
        this.previousUnitNumbering.put(uniqueId, paragraphIndex);
    }

    private UnitNumbering getNumberingPrefix(String uniqueId, String style) {
        String formatName = this.numberingFormatNames.get(uniqueId);
        String formatDisplay = this.numberingFormatDisplays.get(uniqueId);
        int currentNumber = this.numberingTracker.get(uniqueId) +
                this.numberingCountStart.get(uniqueId) - 1;
        String digit = String.valueOf(currentNumber);
        String nextDigit = String.valueOf(currentNumber + 1);
        UnitNumbering unitNumbering = new UnitNumbering(EMPTY_STRING, EMPTY_STRING, style);
        switch (formatName) {
            case "decimal": {
                String current = formatDisplay.replaceAll("%\\d+", digit);
                String logicalNext = formatDisplay.replaceAll("%\\d+", nextDigit);
                unitNumbering = new UnitNumbering(current, logicalNext, style);
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
        // TODO move out of method. Make `currentNumber` instance variable
        if (this.previousUnitNumbering.containsKey(uniqueId)) {
            int previousParagraph = this.previousUnitNumbering.get(uniqueId);
            this.unitNumberings.get(previousParagraph).realNext = unitNumbering.current;
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
        this.unitNumberings.put(paragraphIndex, new UnitNumbering(EMPTY_STRING, EMPTY_STRING,
                "Text"));
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