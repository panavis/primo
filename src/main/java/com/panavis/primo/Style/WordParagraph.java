package com.panavis.primo.Style;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.Style.Numbering.UnitNumbering;
import com.panavis.primo.Utils.StringFormatting;
import org.apache.poi.xwpf.usermodel.*;
import java.util.*;

import static com.panavis.primo.Utils.StringFormatting.trimColonsOrSemicolons;

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
        return index >= 0 && index < getNumberOfParagraphs();
    }

    public String getParagraphText(int paragraphIndex) {
        String text = getParagraphTextWithoutNumbering(paragraphIndex);
        if (numberedParagraphs.get(paragraphIndex))
            text = unitNumberings.get(paragraphIndex).current + text;
        return text;
    }

    public String getParagraphTextWithoutNumbering(int paragraphIndex) {
        return getParagraph(paragraphIndex).getText().trim();
    }

    public UnitNumbering getUnitNumbering(int paragraphIndex) {
        return unitNumberings.get(paragraphIndex);
    }

    public int getNumberOfParagraphs() {
        return paragraphs.size();
    }

    public boolean isSectionHeading(int paragraphIndex) {
        XWPFParagraph paragraph = getParagraph(paragraphIndex);
        String text = getParagraphText(paragraphIndex);
        if (!StringFormatting.isCaseSensitive(text)) return false;

        String potentialHeading = getHeadingFromParagraph(paragraphIndex);

        return (
                        (ParagraphRun.isFirstOrSecondRunUnderlined(paragraph) &&
                        StringFormatting.isFirstLetterCapitalized(text) &&
                        !StringFormatting.hasComma(text)
                )
                ||
                (
                        ParagraphRun.isFirstRunBold(paragraph) &&
                        ParagraphRun.isFirstRunFollowedByColon(paragraph)
                )
                ||
                (
                        ParagraphRun.isFirstRunCapitalized(paragraph) &&
                        ParagraphRun.isFirstRunFollowedByColon(paragraph)) &&
                        meetsMinLength(potentialHeading)
                ||
                (
                        ParagraphRun.isFirstRunCapitalized(paragraph) &&
                        StringFormatting.isFirstLetterCaseSensitive(text) &&
                        ParagraphRun.hasOneRun(paragraph) &&
                        meetsMinLength(potentialHeading)
                )
                ||
                (
                        ParagraphRun.isFirstRunBold(paragraph) &&
                        ParagraphRun.hasOneRun(paragraph) &&
                        ParagraphRun.isFirstRunCapitalized(paragraph))
                ||
                (
                        ParagraphRun.isFirstRunBold(paragraph) &&
                        ParagraphRun.hasOneRun(paragraph) &&
                        StringFormatting.isFirstLetterCapitalized(text)
                )
                ||
                hasLeftIndentationAndIsCapitalized(paragraphIndex) ||
                isUpperCaseAndHasNumberedHeading(paragraphIndex) ||
                hasColonAndPreColonPartHasStyle(paragraphIndex) ||
                isOneWordAndIsUpperCase(paragraphIndex)
        );
    }

    private boolean meetsMinLength(String potentialHeading) {
        return StringFormatting.hasAtLeastNCharacters(potentialHeading, 3);
    }

    private boolean hasLeftIndentationAndIsCapitalized(int paragraphIndex) {
        XWPFRun firstRun = ParagraphRun.getNthRun(getParagraph(paragraphIndex), 0);
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
        XWPFRun firstRun = ParagraphRun.getNthRun(paragraph, 0);
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
        int blanks = getNumberOfPostParagraphBlanks(paragraphIndex);
        return StringFormatting.duplicateLineSeparator(blanks);
    }

    public int getNumberOfPostParagraphBlanks(int paragraphIndex) {
        return postParagraphBlanks.get(paragraphIndex);
    }

    private String getCaseSensitiveText(String text) {
        List<String> caseTokens = new ArrayList<>();
        String[] tokens = text.split(" ");
        for (String token : tokens) {
            if ((StringFormatting.isCaseSensitive(token)) ||
                    token.equals(".")) {
                caseTokens.add(token);
            }
        }

        String newText = String.join(" ", caseTokens);
        return StringFormatting.trimColonsOrSemicolons(newText);
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
        String withoutColons = trimColonsOrSemicolons(sectionHeading);
        return getCaseSensitiveText(withoutColons);
    }

    public boolean isBeginningUnderlined(int paragraphIndex) {
        XWPFParagraph paragraph = getParagraph(paragraphIndex);
        return ParagraphRun.isFirstOrSecondRunUnderlined(paragraph);
    }

    public boolean isFirstRunBold(int paragraphIndex) {
        return ParagraphRun.isFirstRunBold(getParagraph(paragraphIndex));
    }
}
