package com.panavis.WordToJsonConverter.Style;

import com.panavis.WordToJsonConverter.Constants.Format;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Numbering {

    private final XWPFNumbering numbering;
    private List<String> uniqueNumIds;
    private Map<String, Integer> numberingTracker;
    private Map<String, String> numberingFormatNames;
    private Map<String, String> numberingFormatDisplays;
    private Map<String, Integer> numberingCountStart;
    private Map<Integer, String> paragraphsNumbering;
    private List<XWPFParagraph> paragraphs;

    private Numbering(XWPFNumbering numbering, List<XWPFParagraph> paragraphs) {
        this.paragraphs = paragraphs;
        this.numbering = numbering;
        this.uniqueNumIds = new ArrayList<>();
        this.numberingTracker = new HashMap<>();
        this.numberingFormatNames = new HashMap<>();
        this.numberingFormatDisplays = new HashMap<>();
        this.numberingCountStart = new HashMap<>();
        this.paragraphsNumbering = new HashMap<>();
    }

    public static Map<Integer, String> getParagraphsNumbering(XWPFNumbering numbering, List<XWPFParagraph> paragraphs) {
        Numbering docNumbering = new Numbering(numbering, paragraphs);

        for (int i = 0; i < paragraphs.size(); i++) {
            XWPFParagraph paragraph = paragraphs.get(i);
            String paragraphStyle = paragraph.getStyle();

            if (paragraphStyle != null && paragraphStyle.equals("ListParagraph")) {
                BigInteger numId = paragraph.getNumID();

                int numIdValue = numId.intValue();
                int levelValue = paragraph.getNumIlvl().intValue();
                String uniqueId = numId.toString() + "_" + levelValue;
                docNumbering.saveNumberingItem(uniqueId);

                if (!docNumbering.uniqueNumIds.contains(uniqueId)) {
                    docNumbering.uniqueNumIds.add(uniqueId);
                    docNumbering.saveNumIdProperties(paragraph, numId, uniqueId);
                }
                docNumbering.addParagraphNumberingPrefix(i, uniqueId);
            }
            if (!docNumbering.paragraphsNumbering.containsKey(i))
                docNumbering.addDefaultNumberingPrefix(i);
        }
        return docNumbering.paragraphsNumbering;
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

    private void addParagraphNumberingPrefix(int paragraphIndex, String uniqueId) {
        String numberingPrefix = this.getNumberingPrefix(uniqueId);
        this.paragraphsNumbering.put(paragraphIndex, numberingPrefix + "\t");
    }

    private String getNumberingPrefix(String uniqueId) {
        String formatName = this.numberingFormatNames.get(uniqueId);
        String formatDisplay = this.numberingFormatDisplays.get(uniqueId);
        int currentNumber = this.numberingTracker.get(uniqueId) +
                this.numberingCountStart.get(uniqueId) - 1;

        String numberingString;
        switch (formatName) {
            case "decimal": {
                String numberingDigit = String.valueOf(currentNumber);
                numberingString = formatDisplay.replaceAll("%\\d+", numberingDigit);
                break;
            }
            case "lowerLetter": {
                String numberingLetter = Character.toString((char) (96 + currentNumber));
                numberingString = formatDisplay.replaceAll("%\\d", numberingLetter);

                break;
            }
            case "upperLetter": {
                String numberingLetter = Character.toString((char) (64 + currentNumber));
                numberingString = formatDisplay.replaceAll("%\\d", numberingLetter);
                break;
            }
            case "bullet": {
                numberingString = "-";
                break;
            }
            default:
                numberingString = "";
        }
        return numberingString;
    }

    private void addDefaultNumberingPrefix(int paragraphIndex) {
        this.paragraphsNumbering.put(paragraphIndex, Format.EMPTY_STRING);
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
