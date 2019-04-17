package com.panavis.primo.Style;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.Style.Numbering.UnitNumbering;
import com.panavis.primo.Utils.StringFormatting;
import org.apache.poi.xwpf.usermodel.*;
import java.util.*;

import static com.panavis.primo.Utils.StringFormatting.trimColons;

public class WordParagraph {

    private final List<XWPFParagraph> paragraphs;
    private final Map<Integer, Integer> postParagraphBlanks;
    private final Map<Integer, Boolean> numberedParagraphs;
    private final Map<Integer, UnitNumbering> unitNumberings;

    public WordParagraph(XWPFDocument wordDocument) {
        this.paragraphs = WordPreprocessor.getNonEmptyParagraphs(wordDocument);
        this.postParagraphBlanks = WordPreprocessor.getPostParagraphBlanks(wordDocument);
        this.numberedParagraphs = WordPreprocessor.getNumberedParagraphs(this.paragraphs);
        this.unitNumberings = WordPreprocessor.getUnitNumberings(wordDocument.getNumbering(), this.paragraphs);
    }

    private XWPFParagraph getParagraph(int paragraphIndex) {
        return paragraphs.get(paragraphIndex);
    }

    public boolean paragraphExists(int index) {
        return index >= 0 && index < numberOfParagraphs();
    }

    public String getParagraphText(int paragraphIndex) {
        String text = getParagraph(paragraphIndex).getText().trim();
        if (numberedParagraphs.get(paragraphIndex))
            text = unitNumberings.get(paragraphIndex).current + text;
        return text;
    }

    public UnitNumbering getUnitNumbering(int paragraphIndex) {
        return unitNumberings.get(paragraphIndex);
    }

    public int numberOfParagraphs() {
        return paragraphs.size();
    }

    public boolean isSectionHeading(int paragraphIndex) {
        XWPFParagraph paragraph = getParagraph(paragraphIndex);
        String text = getParagraphText(paragraphIndex);
        if (!StringFormatting.isCaseSensitive(text)) return false;

        return (ParagraphRun.isFirstOrSecondRunUnderlined(paragraph) ||
                ParagraphRun.isFirstRunBoldAndSecondRunStartsWithColon(paragraph) ||
                ParagraphRun.isFirstRunCapitalizedAndEndsWithColon(paragraph) ||
                ParagraphRun.isFirstRunBoldAndEndsWithColon(paragraph) ||
                hasLeftIndentationAndIsCapitalized(paragraphIndex) ||
                isUpperCaseAndHasNumberedHeading(paragraphIndex) ||
                hasColonAndPreColonPartHasStyle(paragraphIndex) ||
                isOneWordAndIsUpperCase(paragraphIndex)
        );
    }

    private boolean hasLeftIndentationAndIsCapitalized(int paragraphIndex) {
        XWPFRun firstRun = ParagraphRun.getRun(getParagraph(paragraphIndex), 0);
        return hasSignificantLeftIndentation(paragraphIndex) &&
                StringFormatting.isTextCapitalized(firstRun.text());
    }

    public boolean hasSignificantLeftIndentation(int paragraphIndex) {
        String firstWord = getParagraphText(paragraphIndex).split(" ")[0];
        XWPFParagraph paragraph = getParagraph(paragraphIndex);
        int MIN_HEADING_LENGTH = 3;
        return firstWord.length() > MIN_HEADING_LENGTH &&
                ParagraphRun.isFirstRunHighlyIndented(paragraph);
    }

    private boolean isUpperCaseAndHasNumberedHeading(int paragraphIndex) {
        XWPFParagraph paragraph = getParagraph(paragraphIndex);
        XWPFRun firstRun = ParagraphRun.getRun(paragraph, 0);
        UnitNumbering unitNumbering = unitNumberings.get(paragraphIndex);
        return StringFormatting.isTextCapitalized(firstRun.text()) &&
                (numberedParagraphs.get(paragraphIndex) &&
                        unitNumbering.style.startsWith(Keywords.HEADING));
    }

    private boolean hasColonAndPreColonPartHasStyle(int paragraphIndex) {
        String paragraphText = getParagraphText(paragraphIndex);
        String[] textParts = paragraphText.split(StringFormatting.COLON);
        int numberOfParts = textParts.length;
        String firstPart = textParts[0];

        boolean colonAndCapitalized = false;
        if (preColonPartHasStyle(paragraphIndex, numberOfParts, firstPart))
            colonAndCapitalized = true;
        return colonAndCapitalized ;
    }

    private boolean preColonPartHasStyle(int paragraphIndex, int numberOfParts, String firstPart) {
        return numberOfParts == 2 && firstPart.length() > 3 &&
                (StringFormatting.isTextCapitalized(firstPart) ||
                        ParagraphRun.isFirstRunBold(getParagraph(paragraphIndex)));
    }

    private boolean isOneWordAndIsUpperCase(int paragraphIndex) {
        String text = getParagraphText(paragraphIndex);
        return text.equals(text.toUpperCase()) && text.split(" ").length == 1 &&
                text.matches("^[a-zA-Z]");
    }

    public String getBlankLinesAfterParagraph(int paragraphIndex) {
        int blanks = getNumberOfAfterBlanks(paragraphIndex);
        return StringFormatting.duplicateLineSeparator(blanks);
    }

    public int getNumberOfAfterBlanks(int paragraphIndex) {
        return postParagraphBlanks.get(paragraphIndex);
    }

    public String getCaseSensitiveRunText(int paragraphIndex) {
        String runText = "";
        List<XWPFRun> runs = getParagraph(paragraphIndex).getRuns();
        for (XWPFRun run : runs) {
            if (StringFormatting.isCaseSensitive(run.text()))
                runText = run.text();
        }
        return StringFormatting.trimColons(runText).trim();
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

        if (hasColonAndPreColonPartHasStyle(paragraphIndex))
            sectionHeading = currentParagraph.split(StringFormatting.COLON)[0];
        return trimColons(sectionHeading);
    }

    public boolean isBeginningUnderlined(int paragraphIndex) {
        XWPFParagraph paragraph = getParagraph(paragraphIndex);
        return ParagraphRun.isFirstOrSecondRunUnderlined(paragraph);
    }

    public boolean isFirstRunBold(int paragraphIndex) {
        return ParagraphRun.isFirstRunBold(getParagraph(paragraphIndex));
    }
}
