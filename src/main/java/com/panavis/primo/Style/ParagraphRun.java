package com.panavis.primo.Style;

import com.panavis.primo.Utils.StringFormatting;
import org.apache.poi.xwpf.usermodel.*;

import java.util.List;

class ParagraphRun {
    static boolean isFirstRunFollowedByColon(XWPFParagraph paragraph) {
        String firstRunText = ParagraphRun.getNthRunText(paragraph, 0);
        String secondRunText = ParagraphRun.getNthRunText(paragraph, 1);
        return firstRunText.endsWith(StringFormatting.COLON) ||
                firstRunText.contains(StringFormatting.COLON) ||
                secondRunText.startsWith(StringFormatting.COLON);
    }

    static boolean isFirstRunHighlyIndented(XWPFParagraph paragraph) {
        int HEADING_INDENT = -1;
        int TWO_TABS_ESTIMATE = 2000;
        int leftIndent = paragraph.getIndentationLeft();
        return (leftIndent == HEADING_INDENT || leftIndent > TWO_TABS_ESTIMATE);
    }

    static XWPFRun getNthRun(XWPFParagraph paragraph, int runIndex) {
        List<XWPFRun> paragraphRuns = paragraph.getRuns();
        return paragraphRuns.get(runIndex);
    }

    private static String getNthRunText(XWPFParagraph paragraph, int runIndex) {
        List<XWPFRun> runs = paragraph.getRuns();
        int numberOfRuns = runs.size();
        String nthRunText = "";
        if (numberOfRuns > runIndex)
            nthRunText = ParagraphRun.getNthRun(paragraph, runIndex).text().trim();
        return nthRunText;
    }

    static boolean isFirstRunCapitalized(XWPFParagraph paragraph) {
        String firstRunText = ParagraphRun.getNthRunText(paragraph, 0);
        return StringFormatting.isTextCapitalized(firstRunText);
    }

    static boolean isFirstOrSecondRunUnderlined(XWPFParagraph paragraph) {
        XWPFRun firstRun = getNthRun(paragraph, 0);
        if (isRunUnderlined(firstRun)) return true;
        if (paragraph.getRuns().size() > 1) {
            XWPFRun secondRun = getNthRun(paragraph, 1);
            return isRunUnderlined(secondRun);
        }
        return false;
    }

    private static boolean isRunUnderlined(XWPFRun run) {
        int underlinePattern = run.getUnderline().getValue();
        return underlinePattern == 1 || underlinePattern == 4;
    }

    static boolean isFirstRunBold(XWPFParagraph paragraph) {
        XWPFRun firstRun = ParagraphRun.getNthRun(paragraph, 0);
        boolean hasHeadingStyle = false;
        if (paragraph.getStyle() != null)
            hasHeadingStyle = paragraph.getStyle().equals("Heading1");
        return firstRun.isBold() || hasHeadingStyle;
    }

    static boolean hasOneRun(XWPFParagraph paragraph) {
        return paragraph.getRuns().size() == 1;
    }
}
