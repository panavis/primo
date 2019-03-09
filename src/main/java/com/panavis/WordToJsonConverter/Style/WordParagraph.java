package com.panavis.WordToJsonConverter.Style;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.ConverterInitializer;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.panavis.WordToJsonConverter.Utils.StringFormatting.removeStartingOrTrailingColons;

public class WordParagraph {

    private final List<XWPFParagraph> paragraphs;
    private final Map<Integer, Integer> postParagraphBlanks;
    private final Map<Integer, Boolean> listParagraphs;
    private final Map<Integer, String> paragraphsNumbering;

    public WordParagraph(XWPFDocument wordDocument) {
        this.paragraphs = ConverterInitializer.getNonEmptyParagraphs(wordDocument);
        this.postParagraphBlanks = ConverterInitializer.getPostParagraphBlanks(wordDocument);
        this.listParagraphs = ConverterInitializer.getListParagraphs(this.paragraphs);
        XWPFNumbering numbering = wordDocument.getNumbering();
        this.paragraphsNumbering = ConverterInitializer.getParagraphsNumbering(numbering, this.paragraphs);
    }


    public XWPFParagraph getParagraph(int paragraphIndex) {
        return this.paragraphs.get(paragraphIndex);
    }

    public String getParagraphText(int paragraphIndex) {
        String text = getParagraph(paragraphIndex).getText().trim();
        if (this.listParagraphs.get(paragraphIndex))
            text = this.paragraphsNumbering.get(paragraphIndex) + text;
        return text;
    }

    public int numberOfParagraphs() {
        return this.paragraphs.size();
    }

    public boolean isSectionHeading(int paragraphIndex) {
        XWPFParagraph paragraph = getParagraph(paragraphIndex);
        List<XWPFRun> paragraphRuns = paragraph.getRuns();
        XWPFRun firstRun = paragraphRuns.get(0);

        return (ParagraphRun.isFirstRunUnderlined(firstRun) ||
                ParagraphRun.isFirstRunBoldAndSecondRunsStartsWithColon(paragraphRuns) ||
                ParagraphRun.isFirstRunCapitalizedAndEndsWithColon(paragraph, firstRun) ||
                ParagraphRun.isFirstRunHighlyIndentedAndCapitalized(paragraph, firstRun) ||
                ParagraphRun.isFirstRunBoldAndEndsWithColon(paragraph) ||
                hasColonAndContentOnSameLine(paragraphIndex) ||
                isOneWordAndIsUpperCase(paragraphIndex)
        );
    }

    private boolean hasColonAndContentOnSameLine(int paragraphIndex) {
        String paragraphText = getParagraphText(paragraphIndex);
        String[] textParts = paragraphText.split(Format.COLON);
        int numberOfParts = textParts.length;
        String firstPart = textParts[0];

        boolean colonAndContent = false;
        if (numberOfParts == 2 && firstPart.length() > 3 &&
                (StringFormatting.isTextCapitalized(firstPart) || ParagraphRun.isFirstRunBold(getParagraph(paragraphIndex))))
            colonAndContent = true;
        return colonAndContent;
    }

    private boolean isOneWordAndIsUpperCase(int paragraphIndex) {
        String text = getParagraphText(paragraphIndex);
        return text.equals(text.toUpperCase()) && text.split(" ").length == 1 &&
                text.matches("^[a-zA-Z]");
    }

    public boolean startsSubjectMatterSection(int paragraphIndex) {
        String paragraphText = getParagraphText(paragraphIndex);
        boolean subjectMatterStart = false;
        for (String heading : Headings.SUBJECT_MATTER_HEADINGS) {
            if (paragraphText.toUpperCase().startsWith(heading))
                subjectMatterStart = true;
        }
        return subjectMatterStart;
    }

    public String getBlankLinesAfterParagraph(int paragraphIndex) {
        int blanks = this.postParagraphBlanks.get(paragraphIndex);
        return StringFormatting.duplicateLineSeparator(blanks);
    }

    public String getCaseSensitiveRunText(int paragraphIndex) {
        String runText = "";
        List<XWPFRun> runs = getParagraph(paragraphIndex).getRuns();
        for (XWPFRun run : runs) {
            if (StringFormatting.isCaseSensitive(run.text()))
                runText = run.text();
        }
        return StringFormatting.removeStartingOrTrailingColons(runText).trim();
    }

    public String getParagraphFirstWord(int paragraphIndex) {
        String paragraphText = getParagraphText(paragraphIndex).trim();
        return paragraphText.split(" ")[0];
    }

    public String getInlineHeadingFirstParagraph(int paragraphIndex) {
        String[] bodyArray = getParagraphText(paragraphIndex).split(":");
        String[] bodyNoHeading = Arrays.copyOfRange(bodyArray, 1, bodyArray.length);
        return String.join(" ", bodyNoHeading).trim();
    }

    public String getHeadingFromParagraph(int paragraphIndex) {
        String currentParagraph = getParagraphText(paragraphIndex);
        String sectionHeading = currentParagraph;

        if (hasColonAndContentOnSameLine(paragraphIndex))
            sectionHeading = currentParagraph.split(Format.COLON)[0];
        return removeStartingOrTrailingColons(sectionHeading);
    }
}
