package com.panavis.WordToJsonConverter.Style;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;

class ParagraphRun {

    static boolean isFirstRunBoldAndSecondRunsStartsWithColon(List<XWPFRun> paragraphRuns) {
        int numberOfRuns = paragraphRuns.size();
        String secondRunText = "";
        if (numberOfRuns > 1)
            secondRunText = paragraphRuns.get(1).text().trim();
        XWPFRun firstRun = paragraphRuns.get(0);
        return firstRun.isBold() && secondRunText.startsWith(Format.COLON);
    }

    static boolean isFirstRunHighlyIndentedAndCapitalized(XWPFParagraph paragraph, XWPFRun firstRun) {
        return (paragraph.getIndentationLeft() == -1) && StringFormatting.isTextCapitalized(firstRun.text());
    }

    static boolean isFirstRunCapitalizedAndEndsWithColon(XWPFParagraph paragraph, XWPFRun firstRun) {
        return StringFormatting.isTextCapitalized(firstRun.text()) && paragraph.getText().endsWith(Format.COLON);
    }

    static boolean isFirstRunBoldAndEndsWithColon(XWPFParagraph paragraph) {
        return ParagraphRun.isFirstRunBold(paragraph) && paragraph.getText().endsWith(Format.COLON);
    }

    static boolean isFirstRunUnderlined(XWPFRun firstRun) {
        int underlinePattern = firstRun.getUnderline().getValue();
        return underlinePattern == 1 || underlinePattern == 4;
    }

    static boolean isFirstRunBold(XWPFParagraph paragraph) {
        List<XWPFRun> paragraphRuns = paragraph.getRuns();
        XWPFRun firstRun = paragraphRuns.get(0);
        return firstRun.isBold();
    }

}
