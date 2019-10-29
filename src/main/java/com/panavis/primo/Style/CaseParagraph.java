package com.panavis.primo.Style;

import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.core.ParagraphWrapper;
import com.panavis.primo.core.RunWrapper;
import com.panavis.primo.core.WordParagraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.panavis.primo.Utils.StringFormatting.trimColonsAndSemicolons;

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
                                isFirstRunBold(paragraphIndex) &&
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
                                isFirstRunBold(paragraphIndex) &&
                                        WordParagraph.hasOneRun(paragraph) &&
                                        isFirstRunCapitalized(paragraph))
                        ||
                        (
                                isFirstRunBold(paragraphIndex) &&
                                        WordParagraph.hasOneRun(paragraph) &&
                                        StringFormatting.isFirstLetterCapitalized(text)
                        )
                        ||
                        (
                                hasLeftIndentationAndIsCapitalized(paragraphIndex) &&
                                        !hasManyLowerCaseWords(text)

                        )
                        ||
                        isUpperCaseAndHasNumberedHeading(paragraphIndex) ||
                        hasColonAndPreColonPartHasStyle(paragraphIndex) ||
                        isOneWordAndIsUpperCase(paragraphIndex)
        );
    }

    public boolean isFirstRunBold(int paragraphIndex) {
        return isRunBold(getParagraph(paragraphIndex), 0);
    }

    private boolean hasManyLowerCaseWords(String text) {
        List<String> tokens = new ArrayList<>(Arrays.asList(text.split(" ")))
                                    .stream().filter(t -> !t.isEmpty() && StringFormatting.isTextLowercase(t))
                                    .collect(Collectors.toList());
        int maxLowercaseTokensInHeading = 3;
        return tokens.size() >= maxLowercaseTokensInHeading;
    }

    public boolean isBoldOrUnderlined(int paragraphIndex) {
        ParagraphWrapper paragraph = this.getParagraph(paragraphIndex);
        String text = getParagraphText(paragraphIndex);
        if (!StringFormatting.isCaseSensitive(text)) return false;
        return isFirstRunBold(paragraphIndex) || isFirstOrSecondRunUnderlined(paragraph);
    }

    public String getHeadingFromParagraph(int paragraphIndex) {
        String currentParagraph = getParagraphText(paragraphIndex);
        String sectionHeading = currentParagraph;

        if (hasColonAndPreColonPartHasStyle(paragraphIndex)) {
            sectionHeading = currentParagraph.split(StringFormatting.COLON)[0];
        }

        if (isFirstRunBold(paragraphIndex) && !hasNumbering(paragraphIndex)) {
            String boldText = getStartingBoldedText(paragraphIndex);
            sectionHeading = shouldBoldedTextPrevail(sectionHeading, boldText) ? boldText : sectionHeading;
        }
        String withoutColons = trimColonsAndSemicolons(sectionHeading);
        return getCaseSensitiveText(withoutColons);
    }

    private boolean shouldBoldedTextPrevail(String currentHeading, String boldText) {
        int currentTokens = currentHeading.split(" ").length;
        int boldedTokens = boldText.split(" ").length;
        boolean boldedHeadingIsShorter = currentHeading.length() > boldText.length();
        boolean bolderTextHasFewerWords = currentTokens > boldedTokens + 1;
        return boldedHeadingIsShorter && bolderTextHasFewerWords &&
                meetsMinLength(boldText);
    }

    private String getStartingBoldedText(int paragraphIndex) {
        ParagraphWrapper paragraphWrapper = getParagraph(paragraphIndex);
        StringBuilder boldTextBuilder = new StringBuilder(getUnitNumbering(paragraphIndex).current);
        for (int i=0; i < paragraphWrapper.getRuns().size(); i++) {
            if (isRunBold(paragraphWrapper, i)) {
                boldTextBuilder.append(getNthRunText(paragraphWrapper, i));
            }
            else {
                break;
            }
        }
        return boldTextBuilder.toString().trim();
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
        return StringFormatting.trimColonsAndSemicolons(newText);
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
        String shortestHeading = "UREGA";
        return StringFormatting.hasAtLeastNCharacters(potentialHeading, shortestHeading.length());
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

        boolean colonAndCapitalizedOrBold = false;
        if (preColonPartHasStyle(paragraphIndex, numberOfParts, firstPart))
            colonAndCapitalizedOrBold = true;
        return colonAndCapitalizedOrBold ;
    }

    private boolean preColonPartHasStyle(int paragraphIndex, int numberOfParts, String firstPart) {
        return numberOfParts >= 2 && firstPart.length() > 3 &&
                (StringFormatting.isTextCapitalized(firstPart) ||
                        isFirstRunBold(paragraphIndex));
    }

    private boolean isOneWordAndIsUpperCase(int paragraphIndex) {
        String text = getParagraphText(paragraphIndex);
        return text.equals(text.toUpperCase()) && text.split(" ").length == 1 &&
                text.matches("^[a-zA-Z]");
    }

    public String getInlineHeadingFirstParagraph(int paragraphIndex) {
        String paragraphText = getParagraphText(paragraphIndex);
        String[] bodyArray = paragraphText.split(":");
        String[] bodyNoHeading = Arrays.copyOfRange(bodyArray, 1, bodyArray.length);
        String inlineText = String.join(" ", bodyNoHeading).trim();

        if (isFirstRunBold(paragraphIndex)) {
           String boldText = getStartingBoldedText(paragraphIndex);
           String afterBoldText = paragraphText.substring(boldText.length()).trim();
           inlineText = inlineText.length() > afterBoldText.length() ? inlineText : afterBoldText;
        }
        return StringFormatting.trimColons(inlineText);
    }

    public boolean isBeginningUnderlined(int paragraphIndex) {
        ParagraphWrapper paragraph = getParagraph(paragraphIndex);
        return CaseParagraph.isFirstOrSecondRunUnderlined(paragraph);
    }
}
