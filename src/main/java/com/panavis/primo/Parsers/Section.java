package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Headings.*;
import static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.ResultTypes.Result;
import com.panavis.primo.ResultTypes.TextParagraphIndex;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.*;
import com.panavis.primo.Wrappers.JsonArray;

abstract class Section  {

    CaseParagraph caseParagraph;
    CaseClosingLogic closingLogic;
    private int startParagraph;
    private String inlineParagraph;
    private int lastParagraph;
    private String subsectionBody;

    Section(CaseParagraph caseParagraph) {
        this.caseParagraph = caseParagraph;
        this.closingLogic = new CaseClosingLogic(caseParagraph);
        this.inlineParagraph = "";
        this.subsectionBody = "";
    }

    abstract boolean isInNextSubsection(int paragraphIndex);

    void parse() {
        if (inlineParagraphHasText())
            inlineParagraph += caseParagraph.getBlankLinesAfterParagraph(startParagraph);
        TextParagraphIndex remainingAndIndex = getRemainingSubsectionBody(startParagraph);
        subsectionBody = inlineParagraph.concat(remainingAndIndex.getSubsectionParagraphs()).trim();
        lastParagraph = remainingAndIndex.getParagraphIndex();
    }

    private boolean inlineParagraphHasText() {
        return inlineParagraph.length() != 0;
    }

    private TextParagraphIndex getRemainingSubsectionBody(int startParagraph) {
        StringBuilder remainingBody = new StringBuilder();
        int paragraphIndex = startParagraph + 1;

        while(caseParagraph.paragraphExists(paragraphIndex) &&
                !isInNextSubsection(paragraphIndex))
        {
            addParagraphToSubsection(remainingBody, paragraphIndex);
            paragraphIndex++;
        }
        return new TextParagraphIndex(remainingBody.toString(), paragraphIndex);
    }

    private void addParagraphToSubsection(StringBuilder remainingBody, int paragraphIndex) {
        String paragraphText = caseParagraph.getParagraphText(paragraphIndex);

        if (StringFormatting.isCaseSensitive(paragraphText) || StringFormatting.includesNumbers(paragraphText)) {
            String blankLinesAfterParagraph = caseParagraph.getBlankLinesAfterParagraph(paragraphIndex);

            if ((caseParagraph.isBoldOrUnderlined(paragraphIndex) ||
                    hasHeadingNumbering(paragraphIndex)) &&
                    !isRegularBodyParagraph(paragraphText)) {
                paragraphText = "<bold/>" + paragraphText;
                blankLinesAfterParagraph = StringFormatting.duplicateLineSeparator(2);
                if (!remainingBody.toString().endsWith(blankLinesAfterParagraph)) {
                    remainingBody.append(StringFormatting.duplicateLineSeparator(1));
                }
            }
            remainingBody.append(paragraphText)
                    .append(blankLinesAfterParagraph);
        }
    }

    private boolean hasHeadingNumbering(int paragraphIndex) {
        String text = caseParagraph.getParagraphText(paragraphIndex);
        String withoutNumbering = caseParagraph.getParagraphTextWithoutNumbering(paragraphIndex);
        boolean hasNumbering = !text.equals(withoutNumbering);
        String firstChar = text.substring(0,1);
        String secondChar = text.length() >= 2 ? text.substring(1,2) : "";
        boolean isUnformattedHeading = firstChar.equals("(") || secondChar.equals(".") || secondChar.equals(")");
        return (hasNumbering && StringFormatting.isCaseSensitive(firstChar))||
                isUnformattedHeading;
    }

    private boolean isRegularBodyParagraph(String text) {
        String firstChar = text.substring(0, 1);
        return  !StringFormatting.isCaseSensitive(firstChar) && !firstChar.equals("-");
    }

    Result hasNewCaseBodyFormat(int paragraphIndex) {
        if (isParagraphNewCaseBodyStart(paragraphIndex)) {
            return new Result(true, paragraphIndex);
        }

        if (caseParagraph.paragraphExists(paragraphIndex + 1) &&
                caseParagraph.isSectionHeading(paragraphIndex) &&
                isParagraphNewCaseBodyStart(paragraphIndex + 1)) {
            return new Result(true, paragraphIndex + 1);
        }
        return new Result(false);
    }

    private boolean isParagraphNewCaseBodyStart(int paragraphIndex) {
        String text = caseParagraph.getParagraphText(paragraphIndex).toLowerCase();
        return text.contains(IMITERERE) && text.contains("y") &&
                text.contains(URUBANZA);
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

    boolean hasOldCaseBodyFormat(int startParagraph) {
        if (!caseParagraph.paragraphExists(startParagraph + 1)) return false;
        String heading = caseParagraph.getParagraphTextWithoutNumbering(startParagraph);
        String firstBodyParagraph = caseParagraph.getParagraphTextWithoutNumbering(startParagraph + 1);
        return heading.toUpperCase().startsWith(URUKIKO) && firstBodyParagraph.toLowerCase().contains(RUSHINGIYE);
    }
}