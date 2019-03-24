package com.panavis.WordToJsonConverter.Style;

import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import org.apache.poi.xwpf.usermodel.*;

import java.util.List;

class ParagraphRun {

    static boolean isFirstRunBoldAndSecondRunStartsWithColon(XWPFParagraph paragraph) {
        List<XWPFRun> runs = paragraph.getRuns();
        int numberOfRuns = runs.size();
        String secondRunText = "";
        if (numberOfRuns > 1)
            secondRunText = runs.get(1).text().trim();
        XWPFRun firstRun = runs.get(0);
        return firstRun.isBold() && secondRunText.startsWith(StringFormatting.COLON);
    }

    static boolean isFirstRunHighlyIndentedAndCapitalized(XWPFParagraph paragraph) {
        XWPFRun firstRun = getRun(paragraph, 0);
        int HEADING_INDENT = -1;
        int TWO_TABS_ESTIMATE = 2000;

        int leftIndent = paragraph.getIndentationLeft();
        return (leftIndent == HEADING_INDENT || leftIndent > TWO_TABS_ESTIMATE) &&
                StringFormatting.isTextCapitalized(firstRun.text());
    }

    static XWPFRun getRun(XWPFParagraph paragraph, int runIndex) {
        List<XWPFRun> paragraphRuns = paragraph.getRuns();
        return paragraphRuns.get(runIndex);
    }

    static boolean isFirstRunCapitalizedAndEndsWithColon(XWPFParagraph paragraph) {
        XWPFRun firstRun = getRun(paragraph, 0);
        return StringFormatting.isTextCapitalized(firstRun.text()) &&
                paragraph.getText().endsWith(StringFormatting.COLON);
    }

    static boolean isFirstRunBoldAndEndsWithColon(XWPFParagraph paragraph) {
        return ParagraphRun.isFirstRunBold(paragraph) &&
                paragraph.getText().endsWith(StringFormatting.COLON);
    }

    static boolean isFirstOrSecondRunUnderlined(XWPFParagraph paragraph) {
        XWPFRun firstRun = getRun(paragraph, 0);
        if (isRunUnderlined(firstRun)) return true;
        if (paragraph.getRuns().size() > 1) {
            XWPFRun secondRun = getRun(paragraph, 1);
            return isRunUnderlined(secondRun);
        }
        return false;
    }

    private static boolean isRunUnderlined(XWPFRun run) {
        int underlinePattern = run.getUnderline().getValue();
        return underlinePattern == 1 || underlinePattern == 4;
    }

    static boolean isFirstRunBold(XWPFParagraph paragraph) {
        List<XWPFRun> paragraphRuns = paragraph.getRuns();
        XWPFRun firstRun = paragraphRuns.get(0);
        boolean hasHeadingStyle = false;
        if (paragraph.getStyle() != null)
            hasHeadingStyle = paragraph.getStyle().equals("Heading1");
        return firstRun.isBold() || hasHeadingStyle;
    }

}
