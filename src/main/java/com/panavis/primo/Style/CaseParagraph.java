package com.panavis.primo.Style;

import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.core.ParagraphWrapper;
import com.panavis.primo.core.RunWrapper;
import com.panavis.primo.core.WordParagraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.panavis.primo.Utils.StringFormatting.trimColonsOrSemicolons;

public class CaseParagraph extends WordParagraph {

    public CaseParagraph(String wordFilePath) {
        super(wordFilePath);
    }

    public boolean isSectionHeading(int paragraphIndex) {
        ParagraphWrapper paragraph = this.getParagraph(paragraphIndex);
        String text = getParagraphText(paragraphIndex);
        if (!StringFormatting.isCaseSensitive(text)) return false;

        String potentialHeading = getHeadingFromParagraph(paragraphIndex);

        return (
                (isFirstOrSecondRunUnderlined(paragraph) &&
                        StringFormatting.isFirstLetterCapitalized(text) &&
                        !StringFormatting.hasComma(text)
                )
                        ||
                        (
                                WordParagraph.isFirstRunBold(paragraph) &&
                                        isFirstRunFollowedByColon(paragraph)
                        )
                        ||
                        (
                                isFirstRunCapitalized(paragraph) &&
                                        isFirstRunFollowedByColon(paragraph)) &&
                                meetsMinLength(potentialHeading)
                        ||
                        (
                                isFirstRunCapitalized(paragraph) &&
                                        StringFormatting.isFirstLetterCaseSensitive(text) &&
                                        WordParagraph.hasOneRun(paragraph) &&
                                        meetsMinLength(potentialHeading)
                        )
                        ||
                        (
                                WordParagraph.isFirstRunBold(paragraph) &&
                                        WordParagraph.hasOneRun(paragraph) &&
                                        isFirstRunCapitalized(paragraph))
                        ||
                        (
                                WordParagraph.isFirstRunBold(paragraph) &&
                                        WordParagraph.hasOneRun(paragraph) &&
                                        StringFormatting.isFirstLetterCapitalized(text)
                        )
                        ||
                        hasLeftIndentationAndIsCapitalized(paragraphIndex) ||
                        isUpperCaseAndHasNumberedHeading(paragraphIndex) ||
                        hasColonAndPreColonPartHasStyle(paragraphIndex) ||
                        isOneWordAndIsUpperCase(paragraphIndex)
        );
    }

    public String getHeadingFromParagraph(int paragraphIndex) {
        String currentParagraph = getParagraphText(paragraphIndex);
        String sectionHeading = currentParagraph;

        if (hasColonAndPreColonPartHasStyle(paragraphIndex))
            sectionHeading = currentParagraph.split(StringFormatting.COLON)[0];
        String withoutColons = trimColonsOrSemicolons(sectionHeading);
        return getCaseSensitiveText(withoutColons);
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

    private static boolean isFirstRunFollowedByColon(ParagraphWrapper paragraphWrapper) {
        String firstRunText = getNthRunText(paragraphWrapper, 0);
        String secondRunText = getNthRunText(paragraphWrapper, 1);
        return firstRunText.endsWith(StringFormatting.COLON) ||
                firstRunText.contains(StringFormatting.COLON) ||
                secondRunText.startsWith(StringFormatting.COLON);
    }

    private static boolean isFirstRunHighlyIndented(ParagraphWrapper paragraphWrapper) {
        int HEADING_INDENT = -1;
        int TWO_TABS_ESTIMATE = 2000;
        int leftIndent = paragraphWrapper.getIndentationLeft();
        return (leftIndent == HEADING_INDENT || leftIndent > TWO_TABS_ESTIMATE);
    }

    private static boolean isFirstRunCapitalized(ParagraphWrapper paragraphWrapper) {
        String firstRunText = getNthRunText(paragraphWrapper, 0);
        return StringFormatting.isTextCapitalized(firstRunText);
    }

    private static boolean isFirstOrSecondRunUnderlined(ParagraphWrapper paragraphWrapper) {
        RunWrapper firstRun = getNthRun(paragraphWrapper, 0);
        if (isRunUnderlined(firstRun)) return true;
        if (paragraphWrapper.getRuns().size() > 1) {
            RunWrapper secondRun = getNthRun(paragraphWrapper, 1);
            return isRunUnderlined(secondRun);
        }
        return false;
    }

    private boolean meetsMinLength(String potentialHeading) {
        return StringFormatting.hasAtLeastNCharacters(potentialHeading, 3);
    }

    private boolean hasLeftIndentationAndIsCapitalized(int paragraphIndex) {
        RunWrapper firstRun = WordParagraph.getNthRun(getParagraph(paragraphIndex), 0);
        return hasSignificantLeftIndentation(paragraphIndex) &&
                StringFormatting.isTextCapitalized(firstRun.getText());
    }

    public boolean hasSignificantLeftIndentation(int paragraphIndex) {
        String firstWord = getParagraphText(paragraphIndex).split(" ")[0];
        ParagraphWrapper paragraphWrapper = getParagraph(paragraphIndex);
        int MIN_HEADING_LENGTH = 3;
        return firstWord.length() > MIN_HEADING_LENGTH &&
                isFirstRunHighlyIndented(paragraphWrapper);
    }

    private boolean isUpperCaseAndHasNumberedHeading(int paragraphIndex) {
        ParagraphWrapper paragraphWrapper = getParagraph(paragraphIndex);
        RunWrapper firstRun = WordParagraph.getNthRun(paragraphWrapper, 0);
        return StringFormatting.isTextCapitalized(firstRun.getText()) &&
                hasHeadingWithNumbering(paragraphIndex);
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
                        WordParagraph.isFirstRunBold(getParagraph(paragraphIndex)));
    }

    private boolean isOneWordAndIsUpperCase(int paragraphIndex) {
        String text = getParagraphText(paragraphIndex);
        return text.equals(text.toUpperCase()) && text.split(" ").length == 1 &&
                text.matches("^[a-zA-Z]");
    }

    public String getInlineHeadingFirstParagraph(int paragraphIndex) {
        String[] bodyArray = getParagraphText(paragraphIndex).split(":");
        String[] bodyNoHeading = Arrays.copyOfRange(bodyArray, 1, bodyArray.length);
        return String.join(" ", bodyNoHeading).trim();
    }

    public boolean isBeginningUnderlined(int paragraphIndex) {
        ParagraphWrapper paragraph = getParagraph(paragraphIndex);
        return CaseParagraph.isFirstOrSecondRunUnderlined(paragraph);
    }
}
