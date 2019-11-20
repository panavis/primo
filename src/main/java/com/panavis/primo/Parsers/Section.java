package com.panavis.primo.Parsers;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.Constants.Headings;
import com.panavis.primo.ResultTypes.TextParagraphIndex;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.*;
import com.panavis.primo.Wrappers.JsonArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.panavis.primo.Constants.Headings.SUBJECT_MATTER_HEADINGS;

public abstract class Section  {

    CaseParagraph caseParagraph;
    private int startParagraph;
    private String inlineParagraph;
    private int lastParagraph;
    private String subsectionBody;
    private String nextHeading;

    public Section(CaseParagraph caseParagraph) {
        this.caseParagraph = caseParagraph;
        this.inlineParagraph = "";
        this.subsectionBody = "";
        this.nextHeading = "";
    }

    abstract boolean isInNextSubsection(int paragraphIndex);
    abstract boolean isHeadingTooLong(int paragraphIndex);
    abstract String getNextNumbering();

    void parse() {
        if (hasInlineParagraphText())
            inlineParagraph += caseParagraph.getBlankLinesAfterParagraph(startParagraph);
        TextParagraphIndex remainingAndIndex = getRemainingSubsectionBody(startParagraph);
        subsectionBody = inlineParagraph.concat(remainingAndIndex.getSubsectionParagraphs()).trim();
        lastParagraph = remainingAndIndex.getParagraphIndex();
    }

    private boolean hasInlineParagraphText() {
        return inlineParagraph.length() != 0;
    }

    private TextParagraphIndex getRemainingSubsectionBody(int startParagraph) {
        StringBuilder sectionContent = new StringBuilder();
        int paragraphIndex = startParagraph + 1;

        this.resetNextHeading();
        while(caseParagraph.paragraphExists(paragraphIndex) &&
                nextHeading.isEmpty() &&
                !isInNextSubsection(paragraphIndex))
        {
            sectionContent = getUpdatedSectionContent(sectionContent, paragraphIndex);
            paragraphIndex++;
        }
        return new TextParagraphIndex(sectionContent.toString(), paragraphIndex);
    }

    private StringBuilder getUpdatedSectionContent(StringBuilder sectionContent, int paragraphIndex) {
        boolean hasNestedHeading = isNextHeadingNestedInParagraph(paragraphIndex);

        if (!hasNestedHeading) {
            sectionContent = addParagraphToSubsection(sectionContent, paragraphIndex);
            this.resetNextHeading();
        }
        else {
            String nestedHeading = getNestedHeading(paragraphIndex);
            this.nextHeading = nestedHeading;
            String actualContent = getContentBeforeNestedHeading(paragraphIndex, nestedHeading);
            sectionContent.append(actualContent);
        }
        return sectionContent;
    }

    private void resetNextHeading() {
        this.nextHeading = StringFormatting.EMPTY_STRING;
    }

    private String getContentBeforeNestedHeading(int paragraphIndex, String nestedHeading) {
        String paragraphText = caseParagraph.getParagraphText(paragraphIndex);
        int nestedPartSize = nestedHeading.length() + getNextNumbering().length();
        int paragraphSize = paragraphText.length();
        int endIndex = paragraphSize - nestedPartSize + 1;
        endIndex = endIndex < 0 ? paragraphSize : endIndex;
        return paragraphText.substring(0, endIndex).trim();
    }

    private String getNestedHeading(int paragraphIndex) {
        String nestedHeadingRaw = getTextPartAfterCurrentNumbering(paragraphIndex);
        return getNextNumbering().concat(nestedHeadingRaw).trim();
    }

    private boolean isNextHeadingNestedInParagraph(int paragraphIndex) {
        boolean hasNestedHeading = false;

        if (!getNextNumbering().isEmpty()) {
            String paragraphText = caseParagraph.getParagraphText(paragraphIndex);
            String[] parts = paragraphText.split(getNextNumbering());
            String lastPart = parts[parts.length - 1];
            String firstPart = parts[0];
            int firstPartMinLength = 10;

            if (lastPart.startsWith(".") &&
                   firstPart.length() > firstPartMinLength &&
                    StringFormatting.isTextCapitalized(lastPart))
            {
                hasNestedHeading = true;
            }
        }
        return hasNestedHeading;
    }

    private String getTextPartAfterCurrentNumbering(int paragraphIndex) {
        String paragraphText = caseParagraph.getParagraphText(paragraphIndex);
        String[] parts = paragraphText.split(getNextNumbering());
        return parts[parts.length - 1];
    }

    private StringBuilder addParagraphToSubsection(StringBuilder previousContent, int paragraphIndex) {
        String paragraphText = caseParagraph.getParagraphText(paragraphIndex);
        StringBuilder updatedContent = new StringBuilder();

        if (isEligibleParagraph(paragraphText)) {
            String blankLinesAfterParagraph = caseParagraph.getBlankLinesAfterParagraph(paragraphIndex);

            if ((caseParagraph.isParagraphBoldOrUnderlined(paragraphIndex) ||
                    hasNumberedHeading(paragraphIndex)) &&
                    !isRegularBodyParagraph(paragraphText)) {
                paragraphText = Keywords.BOLD + paragraphText;
                blankLinesAfterParagraph = StringFormatting.duplicateLineSeparator(2);
                if (!previousContent.toString().endsWith(blankLinesAfterParagraph)) {
                    previousContent.append(StringFormatting.duplicateLineSeparator(1));
                }
                updatedContent
                        .append(previousContent.toString())
                        .append(paragraphText);
            }
            else if (!endsParagraph(previousContent) && isLowerCaseAndCaseSensitive(paragraphText)) {
                updatedContent.append(previousContent.toString().trim())
                            .append(" ")
                            .append(paragraphText);
            }
            else {
                updatedContent
                        .append(previousContent.toString())
                        .append(paragraphText);
            }

            updatedContent.append(blankLinesAfterParagraph);
        }
        else {
            updatedContent.append(previousContent.toString());
        }
        return updatedContent;
    }

    private boolean isEligibleParagraph(String paragraphText) {
        return (StringFormatting.isCaseSensitive(paragraphText) || StringFormatting.includesNumbers(paragraphText)) &&
                !isPageNumberParagraph(paragraphText);
    }

    private boolean isPageNumberParagraph(String paragraphText) {
        List<String> rawTokens = new ArrayList<>(Arrays.asList(paragraphText.split(" ")));
        List<String> tokens = rawTokens.stream()
                                .filter(t -> !t.isEmpty())
                                .collect(Collectors.toList());

        int minimumPhraseLength = "Urupapuro rwa <number>".split(" ").length;
        if (tokens.size() < minimumPhraseLength) {
            return false;
        }
        List<String> lastTokens = tokens.subList(tokens.size() - minimumPhraseLength, tokens.size());
        String lastPart = String.join(" ", lastTokens).toLowerCase().trim();
        return lastPart.contains("urupapuro rwa") && !lastPart.endsWith(".");
    }

    private boolean endsParagraph(StringBuilder previousContent) {
        String text = previousContent.toString().trim();
        return text.endsWith(".") || text.endsWith(":") || text.endsWith(";");
    }

    private boolean isLowerCaseAndCaseSensitive(String paragraphText) {
        String firstChar = paragraphText.substring(0, 1);
        return firstChar.equals(firstChar.toLowerCase()) && StringFormatting.isCaseSensitive(firstChar);

    }

    private boolean hasNumberedHeading(int paragraphIndex) {
        String text = caseParagraph.getParagraphText(paragraphIndex);
        String withoutNumbering = caseParagraph.getParagraphTextWithoutNumbering(paragraphIndex);
        boolean hasNumbering = !text.equals(withoutNumbering);
        String firstChar = text.substring(0,1);
        String secondChar = text.length() >= 2 ? text.substring(1,2) : "";
        String thirdCar = text.length() >= 3 ? text.substring(2,3) : "";

        boolean isUnformattedHeading = firstChar.equals("(") ||
                                        secondChar.equals(")") ||
                        (secondChar.equals(".") && thirdCar.equals(" "));

        return (hasNumbering && StringFormatting.isCaseSensitive(firstChar))||
                isUnformattedHeading;
    }

    private boolean isRegularBodyParagraph(String text) {
        String firstChar = text.substring(0, 1);
        return  !StringFormatting.isCaseSensitive(firstChar) && !firstChar.equals("-");
    }

    Section setStartingParagraph(int startParagraph) {
        this.startParagraph = startParagraph;
        return this;
    }

    Section setInlineParagraph(String inlineParagraph) {
        this.inlineParagraph = inlineParagraph;
        return this;
    }

    JsonArray getBody() {
        String[] contentArray = subsectionBody.split(StringFormatting.DOUBLE_BLANK);
        return JsonCreator.getJsonArrayFromStringArray(contentArray);
    }

    int getLastParagraph() {
        return lastParagraph;
    }

    String getNextHeading() { return nextHeading; }

    boolean subjectMatterHasAnotherSubsection(int paragraphIndex) {
        String text = caseParagraph.getParagraphTextWithoutNumbering(paragraphIndex).toLowerCase();
        for (String heading: SUBJECT_MATTER_HEADINGS) {
            if (text.startsWith(heading.toLowerCase())) return true;
        }
        return false;
    }

    boolean startsSubjectMatterSection(int paragraphIndex) {
        String paragraphText = caseParagraph.getParagraphTextWithoutNumbering(paragraphIndex);
        boolean subjectMatterStart = false;
        for (String heading : Headings.SUBJECT_MATTER_HEADINGS) {
            if (paragraphText.toUpperCase().startsWith(heading))
                subjectMatterStart = true;
        }
        return subjectMatterStart;
    }
}