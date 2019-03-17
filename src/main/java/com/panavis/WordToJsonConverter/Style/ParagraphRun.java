package com.panavis.WordToJsonConverter.Style;

import com.panavis.WordToJsonConverter.Constants.Format;
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
        return firstRun.isBold() && secondRunText.startsWith(Format.COLON);
    }

    static boolean isFirstRunHighlyIndentedAndCapitalized(XWPFParagraph paragraph) {
        XWPFRun firstRun = getFirstRun(paragraph);
        int HEADING_INDENT = -1;
        int TWO_TABS_ESTIMATE = 2000;

        int leftIndent = paragraph.getIndentationLeft();
        return (leftIndent == HEADING_INDENT || leftIndent > TWO_TABS_ESTIMATE) &&
                StringFormatting.isTextCapitalized(firstRun.text());
    }

    static XWPFRun getFirstRun(XWPFParagraph paragraph) {
        List<XWPFRun> paragraphRuns = paragraph.getRuns();
        return paragraphRuns.get(0);
    }

    static boolean isFirstRunCapitalizedAndEndsWithColon(XWPFParagraph paragraph) {
        XWPFRun firstRun = getFirstRun(paragraph);
        return StringFormatting.isTextCapitalized(firstRun.text()) &&
                paragraph.getText().endsWith(Format.COLON);
    }

    static boolean isFirstRunBoldAndEndsWithColon(XWPFParagraph paragraph) {
        return ParagraphRun.isFirstRunBold(paragraph) &&
                paragraph.getText().endsWith(Format.COLON);
    }

    static boolean isFirstRunUnderlined(XWPFParagraph paragraph) {
        XWPFRun firstRun = getFirstRun(paragraph);
        int underlinePattern = firstRun.getUnderline().getValue();
        return underlinePattern == 1 || underlinePattern == 4;
    }

    static boolean isFirstRunBold(XWPFParagraph paragraph) {
        List<XWPFRun> paragraphRuns = paragraph.getRuns();
        XWPFRun firstRun = paragraphRuns.get(0);
        return firstRun.isBold();
    }

}
