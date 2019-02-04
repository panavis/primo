package com.nexttran.WordToJsonConverter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ConverterInitializer {

    static List<XWPFParagraph> getNonEmptyParagraphs(XWPFDocument wordDocument) {
        return wordDocument.getParagraphs().stream()
                .filter(para -> !para.getText().trim().isEmpty())
                .collect(Collectors.toList());
    }

    static Map<Integer, Integer> getPostParagraphBlanks(XWPFDocument wordDocument) {
        Map<Integer, Integer> blanksAfterParagraph = new HashMap<>();
        int actualParagraph = 0;
        List<XWPFParagraph> paragraphs = wordDocument.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {

            String paragraphText = paragraph.getText().trim();
            if (!(paragraphText.isEmpty())) {
                blanksAfterParagraph.put(actualParagraph, 1);
                actualParagraph++;
            } else if (blanksAfterParagraph.size() != 0)
                increaseParagraphBlanksCount(blanksAfterParagraph);
        }
        return blanksAfterParagraph;
    }

    private static void increaseParagraphBlanksCount(Map<Integer, Integer> blanksAfterParagraph) {
        int lastParagraphIndex = blanksAfterParagraph.size() - 1;
        int currentBlanks = blanksAfterParagraph.get(lastParagraphIndex);
        blanksAfterParagraph.put(lastParagraphIndex, currentBlanks + 1);
    }

    static Map<Integer, Boolean> getListParagraphs(List<XWPFParagraph> paragraphs) {
        Map<Integer, Boolean> numberedParagraphs = new HashMap<>();

        for (int index = 0; index < paragraphs.size(); index++) {
            String paragraphStyle = paragraphs.get(index).getStyle();
            if (paragraphStyle != null && paragraphStyle.equals("ListParagraph"))
                numberedParagraphs.put(index, true);
            else
                numberedParagraphs.put(index, false);
        }
        return numberedParagraphs;
    }
}
