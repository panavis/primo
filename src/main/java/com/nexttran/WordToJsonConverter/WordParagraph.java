package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Format;
import com.nexttran.WordToJsonConverter.Constants.Headings;
import com.nexttran.WordToJsonConverter.ResultTypes.TextParagraphIndex;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.util.List;
import java.util.Map;

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
                ParagraphRun.isFirstRunBoldAndEndsWithColon(firstRun) ||
                hasColonAndContentOnSameLine(paragraphIndex)
        );
    }

    boolean startsSubjectMatterSection(int paragraphIndex) {
        XWPFParagraph currentParagraph = getParagraph(paragraphIndex);
        boolean subjectMatterStart = false;
        for (String heading : Headings.ALL_SUBJECT_MATTER_HEADINGS) {
            if (currentParagraph.getText().toUpperCase().startsWith(heading))
                subjectMatterStart = true;
        }
        return subjectMatterStart;
    }

    TextParagraphIndex getMoreParagraphsIfAny(String firstParagraph, int paragraphIndex) {
        StringBuilder subsectionParagraphs= new StringBuilder();
        firstParagraph = addNumberingIfAny(paragraphIndex, firstParagraph.trim());
        subsectionParagraphs.append(firstParagraph);
        paragraphIndex++;
        while (nextParagraphIsNotHeading(paragraphIndex)) {
            String emptyLines = getBlankLines(paragraphIndex-1);
            String paragraph = getParagraph(paragraphIndex).getText().trim();
            String numbered = addNumberingIfAny(paragraphIndex, paragraph);
            String paragraphText = emptyLines + numbered;
            subsectionParagraphs.append(paragraphText);
            paragraphIndex++;
        }
        return new TextParagraphIndex(subsectionParagraphs.toString(), paragraphIndex);
    }

    private String getBlankLines(int paragraphIndex) {
        int blanks = this.postParagraphBlanks.get(paragraphIndex);
        if (isListParagraphAndHasNumbering(paragraphIndex)) blanks = 2;
        return StringFormatting.duplicateLineSeparator(blanks);
    }

    private Boolean isListParagraphAndHasNumbering(int paragraphIndex) {
        return this.listParagraphs.get(paragraphIndex) &&
                this.paragraphsNumbering.get(paragraphIndex).length() != 0;
    }

    private String addNumberingIfAny(int paragraphIndex, String paragraph) {
        String newParagraph = paragraph;
        if (this.isListParagraphAndHasNumbering(paragraphIndex)) {
            newParagraph = this.paragraphsNumbering.get(paragraphIndex) + paragraph;
        }
        return newParagraph;
    }

    private boolean nextParagraphIsNotHeading(int paragraphIndex) {
        return !(isSectionHeading(paragraphIndex));
    }

    boolean isContentOnSameLine(int paragraphIndex) {
        String paragraphText = getParagraph(paragraphIndex).getText();
        String[] textParts = paragraphText.split(Format.COLON);
        boolean onSameLine = false;
        if (textParts.length == 2 && !textParts[1].isEmpty())
            onSameLine = true;
        return onSameLine;
    }

    boolean hasColonAndContentOnSameLine(int paragraphIndex) {
        String paragraphText = getParagraph(paragraphIndex).getText();
        String[] textParts = paragraphText.split(Format.COLON);
        int numberOfParts = textParts.length;
        String firstPart = textParts[0];

        boolean colonAndContent = false;
        if (numberOfParts == 2 && firstPart.length() > 3 && StringFormatting.isTextCapitalized(firstPart))
            colonAndContent = true;
        return colonAndContent;
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

    boolean contentIsOnNextLine(int paragraphIndex) {
        return getParagraph(paragraphIndex).getText().endsWith(Format.COLON);
    }
}
