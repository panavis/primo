package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Format;
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

class DocNumbering {

    private final XWPFNumbering numbering;
    private List<Integer> uniqueNumIds;
    private Map<Integer, Integer> numberingTracker;
    private Map<Integer, String> numberingFormatNames;
    private Map<Integer, String> numberingFormatDisplays;
    private Map<Integer, Integer> numberingCountStart;
    private Map<Integer, String> paragraphsNumbering;
    private List<XWPFParagraph> paragraphs;

    private DocNumbering(XWPFNumbering numbering, List<XWPFParagraph> paragraphs) {
        this.paragraphs = paragraphs;
        this.numbering = numbering;
        this.uniqueNumIds = new ArrayList<>();
        this.numberingTracker = new HashMap<>();
        this.numberingFormatNames = new HashMap<>();
        this.numberingFormatDisplays = new HashMap<>();
        this.numberingCountStart = new HashMap<>();
        this.paragraphsNumbering = new HashMap<>();
    }

    static Map<Integer, String> getParagraphsNumbering(XWPFNumbering numbering, List<XWPFParagraph> paragraphs) {
        DocNumbering docNumbering = new DocNumbering(numbering, paragraphs);

        for (int i = 0; i < paragraphs.size(); i++) {
            XWPFParagraph paragraph = paragraphs.get(i);
            String paragraphStyle = paragraph.getStyle();

            if (paragraphStyle != null && paragraphStyle.equals("ListParagraph")) {
                BigInteger numId = paragraph.getNumID();
                int numIdValue = numId.intValue();
                docNumbering.saveNumberingItem(numIdValue);

                if (!docNumbering.uniqueNumIds.contains(numIdValue)) {
                    docNumbering.uniqueNumIds.add(numIdValue);
                    docNumbering.saveNumIdProperties(paragraph, numId, numIdValue);
                }
                docNumbering.addParagraphNumberingPrefix(i, numIdValue);
            }
            if (!docNumbering.paragraphsNumbering.containsKey(i))
                docNumbering.addDefaultNumberingPrefix(i);
        }
        return docNumbering.paragraphsNumbering;
    }

    private void saveNumIdProperties(XWPFParagraph paragraph, BigInteger numId, int numIdValue) {
        CTLvl abstractNumLvl = this.getAbstractNumLvl(paragraph, numId);
        this.saveNumCountStart(numIdValue, abstractNumLvl);
        this.saveNumFormatName(numIdValue, abstractNumLvl);
        this.saveNumFormatDisplay(numIdValue, abstractNumLvl);
    }

    private CTLvl getAbstractNumLvl(XWPFParagraph paragraph, BigInteger numId) {
        BigInteger abstractNumIndex = this.numbering.getAbstractNumID(numId);
        XWPFAbstractNum abstractNum = this.numbering.getAbstractNum(abstractNumIndex);
        CTAbstractNum ctAbstractNum = abstractNum.getCTAbstractNum();
        int abstractNumLvlIndex = paragraph.getNumIlvl().intValue();
        return ctAbstractNum.getLvlArray(abstractNumLvlIndex);
    }

    private void addParagraphNumberingPrefix(int paragraphIndex, int numIdValue) {
        String numberingPrefix = this.getNumberingPrefix(numIdValue);
        this.paragraphsNumbering.put(paragraphIndex, numberingPrefix + "\t");
    }

    private String getNumberingPrefix(int numIdValue) {
        String formatName = this.numberingFormatNames.get(numIdValue);
        String formatDisplay = this.numberingFormatDisplays.get(numIdValue);
        int currentNumber = this.numberingTracker.get(numIdValue) +
                this.numberingCountStart.get(numIdValue) - 1;

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
            default:
                numberingString = "";
        }
        return numberingString;
    }

    private void addDefaultNumberingPrefix(int paragraphIndex) {
        this.paragraphsNumbering.put(paragraphIndex, Format.EMPTY_STRING);
    }

    private void saveNumFormatDisplay(int numIdValue, CTLvl abstractNumLvl) {
        String numDisplay = abstractNumLvl.getLvlText().getVal();
        this.numberingFormatDisplays.put(numIdValue, numDisplay);
    }

    private void saveNumFormatName(int numIdValue, CTLvl abstractNumLvl) {
        String numFormat = abstractNumLvl.getNumFmt().getVal().toString();
        this.numberingFormatNames.put(numIdValue, numFormat);

    }

    private void saveNumCountStart(int numIdValue, CTLvl abstractNumLvl) {
        try {
            int numStart = abstractNumLvl.getStart().getVal().intValue();
            this.numberingCountStart.put(numIdValue, numStart);
        }
        catch (NullPointerException e) {
            this.numberingCountStart.put(numIdValue, 0);
        }
    }

    private void saveNumberingItem(int numIdValue) {
        if (this.numberingTracker.containsKey(numIdValue)) {
            int currentValue = this.numberingTracker.get(numIdValue);
            this.numberingTracker.put(numIdValue, currentValue + 1);
        }
        else {
            this.numberingTracker.put(numIdValue, 1);
        }
    }

}
