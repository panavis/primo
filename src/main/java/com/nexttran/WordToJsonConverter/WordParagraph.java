package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Format;
import com.nexttran.WordToJsonConverter.Constants.Headings;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.util.List;
import java.util.Map;

import static com.nexttran.WordToJsonConverter.StringFormatting.removeStartingOrTrailingColons;

class WordParagraph {

    private final List<XWPFParagraph> paragraphs;
    private final Map<Integer, Integer> postParagraphBlanks;
    private final Map<Integer, Boolean> listParagraphs;
    private final Map<Integer, String> paragraphsNumbering;

    WordParagraph(XWPFDocument wordDocument) {
        this.paragraphs = ConverterInitializer.getNonEmptyParagraphs(wordDocument);
        this.postParagraphBlanks = ConverterInitializer.getPostParagraphBlanks(wordDocument);
        this.listParagraphs = ConverterInitializer.getListParagraphs(this.paragraphs);
        XWPFNumbering numbering = wordDocument.getNumbering();
        this.paragraphsNumbering = ConverterInitializer.getParagraphsNumbering(numbering, this.paragraphs);
    }


    XWPFParagraph getParagraph(int paragraphIndex) {
        return this.paragraphs.get(paragraphIndex);
    }

    int numberOfParagraphs() {
        return this.paragraphs.size();
    }

    boolean isSectionHeading(int paragraphIndex) {
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
        XWPFParagraph paragraph = getParagraph(paragraphIndex);
        String paragraphText = paragraph.getText();
        String[] textParts = paragraphText.split(Format.COLON);
        int numberOfParts = textParts.length;
        String firstPart = textParts[0];

        boolean colonAndContent = false;
        if (numberOfParts == 2 && firstPart.length() > 3 &&
                (StringFormatting.isTextCapitalized(firstPart) || ParagraphRun.isFirstRunBold(paragraph)))
            colonAndContent = true;
        return colonAndContent;
    }

    private boolean isOneWordAndIsUpperCase(int paragraphIndex) {
        String text = this.paragraphs.get(paragraphIndex).getText();
        return text.equals(text.toUpperCase()) && text.split(" ").length == 1 &&
                text.matches("^[a-zA-Z]");
    }

    boolean startsSubjectMatterSection(int paragraphIndex) {
        XWPFParagraph currentParagraph = getParagraph(paragraphIndex);
        boolean subjectMatterStart = false;
        for (String heading : Headings.SUBJECT_MATTER_HEADINGS) {
            if (currentParagraph.getText().toUpperCase().startsWith(heading))
                subjectMatterStart = true;
        }
        return subjectMatterStart;
    }

    String getBlankLines(int paragraphIndex) {
        int blanks = this.postParagraphBlanks.get(paragraphIndex);
        if (isListParagraphAndHasNumbering(paragraphIndex)) blanks = 2;
        return StringFormatting.duplicateLineSeparator(blanks);
    }

    private Boolean isListParagraphAndHasNumbering(int paragraphIndex) {
        return this.listParagraphs.get(paragraphIndex) &&
                this.paragraphsNumbering.get(paragraphIndex).length() != 0;
    }

    boolean isContentOnSameLine(int paragraphIndex) {
        String paragraphText = getParagraph(paragraphIndex).getText();
        String[] textParts = paragraphText.split(Format.COLON);
        boolean onSameLine = false;
        if (textParts.length == 2 && !textParts[1].isEmpty())
            onSameLine = true;
        return onSameLine;
    }

    String getCaseSensitiveRunText(int paragraphIndex) {
        String runText = "";
        List<XWPFRun> runs = getParagraph(paragraphIndex).getRuns();
        for (XWPFRun run : runs) {
            if (StringFormatting.isCaseSensitive(run.text()))
                runText = run.text();
        }
        return StringFormatting.removeStartingOrTrailingColons(runText).trim();
    }

    String getParagraphFirstWord(int paragraphIndex) {
        String paragraphText = getParagraph(paragraphIndex).getText().trim();
        return paragraphText.split(" ")[0];
    }

    boolean isContentOnNextLine(int paragraphIndex) {
        return lineEndsWithColon(paragraphIndex) || lineHasHeadingOnly(paragraphIndex);
    }

    private boolean lineEndsWithColon(int paragraphIndex) {
        return getParagraph(paragraphIndex).getText().endsWith(Format.COLON);
    }

    private boolean lineHasHeadingOnly(int paragraphIndex) {
        return !(getParagraph(paragraphIndex).getText().contains(Format.COLON));
    }

    String getHeadingFromParagraph(int paragraphIndex) {
        String currentParagraph = getParagraph(paragraphIndex).getText();
        String sectionHeading = currentParagraph;

        if (hasColonAndContentOnSameLine(paragraphIndex))
            sectionHeading = currentParagraph.split(Format.COLON)[0];
        return removeStartingOrTrailingColons(sectionHeading);
    }

    String getParagraphWithNumbering(int paragraphIndex) {
        String text = getParagraph(paragraphIndex).getText().trim();
        if (this.listParagraphs.get(paragraphIndex))
            text = this.paragraphsNumbering.get(paragraphIndex) + text;
        return text;
    }
}
