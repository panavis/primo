package com.panavis.primo.Style;

import com.panavis.primo.Style.Numbering.NumberingParser;
import com.panavis.primo.Style.Numbering.UnitNumbering;
import org.apache.poi.xwpf.usermodel.*;

import java.util.*;
import java.util.stream.Collectors;

class WordPreprocessor {

    static List<XWPFParagraph> getNonEmptyParagraphs(XWPFDocument wordDocument) {
        List<XWPFParagraph> bodyElements = getRawParagraphs(wordDocument);
        return bodyElements.stream()
                .filter(WordPreprocessor::paragraphHasContent)
                .collect(Collectors.toList());
    }

    private static List<XWPFParagraph> getRawParagraphs(XWPFDocument wordDocument) {
        List<XWPFParagraph> bodyElements = new ArrayList<>();

        for (int i = 0; i < wordDocument.getBodyElements().size(); i++) {
            try {
                XWPFParagraph paragraph = (XWPFParagraph) wordDocument.getBodyElements().get(i);
                bodyElements.add(paragraph);
            }
            catch (ClassCastException e) {
                XWPFTable table = (XWPFTable) wordDocument.getBodyElements().get(i);
                XWPFParagraph tableTextAsParagraph = new XWPFDocument().createParagraph();
                tableTextAsParagraph.createRun();
                XWPFRun createdRun = tableTextAsParagraph.getRuns().get(0);
                createdRun.setText(table.getText());
                createdRun.setStyle("BodyText");
                bodyElements.add(tableTextAsParagraph);
            }
        }
        return bodyElements;
    }

    private static boolean paragraphHasContent(XWPFParagraph paragraph) {
        String paragraphText = paragraph.getText().trim();
        return !(paragraphText.isEmpty());
    }

    static Map<Integer, Integer> getPostParagraphBlanks(XWPFDocument wordDocument) {
        Map<Integer, Integer> postParagraphBlanks = new HashMap<>();
        int actualParagraph = 0;
        List<XWPFParagraph> paragraphs = getRawParagraphs(wordDocument);
        for (XWPFParagraph paragraph : paragraphs) {
            boolean hasContent = paragraphHasContent(paragraph);
            if (hasContent) {
                postParagraphBlanks.put(actualParagraph, 1);
                actualParagraph++;
            }
            if (!hasContent && followsActualParagraph(postParagraphBlanks))
                increaseParagraphBlanksCount(postParagraphBlanks);
        }
        return postParagraphBlanks;
    }

    private static boolean followsActualParagraph(Map<Integer, Integer> blanksAfterParagraph) {
        return blanksAfterParagraph.size() != 0;
    }

    private static void increaseParagraphBlanksCount(Map<Integer, Integer> blanksAfterParagraph) {
        int lastParagraphIndex = blanksAfterParagraph.size() - 1;
        int currentBlanks = blanksAfterParagraph.get(lastParagraphIndex);
        blanksAfterParagraph.put(lastParagraphIndex, currentBlanks + 1);
    }

    static Map<Integer, Boolean> getNumberedParagraphs(List<XWPFParagraph> paragraphs) {
        Map<Integer, Boolean> numberedParagraphs = new HashMap<>();
        for (int index = 0; index < paragraphs.size(); index++) {
            XWPFParagraph paragraph = paragraphs.get(index);
            String paragraphStyle = paragraph.getStyle();
            boolean hasNumbering = NumberingParser.paragraphHasNumbering(paragraph, paragraphStyle);
            numberedParagraphs.put(index, hasNumbering);
        }
        return numberedParagraphs;
    }

    static Map<Integer, UnitNumbering> getUnitNumberings(XWPFNumbering numbering,
                                                         List<XWPFParagraph> paragraphs) {
        NumberingParser numberingParser = new NumberingParser(numbering);
        return numberingParser.getParagraphsNumbering(paragraphs);
    }
}
