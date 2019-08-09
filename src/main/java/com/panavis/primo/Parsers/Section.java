package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Headings.*;
import static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.ResultTypes.Result;
import com.panavis.primo.ResultTypes.TextParagraphIndex;
import com.panavis.primo.Style.WordParagraph;
import com.panavis.primo.Utils.*;
import com.panavis.primo.Wrappers.JsonArray;

abstract class Section  {

    WordParagraph wordParagraph;
    CaseClosingLogic closingLogic;
    private int startParagraph;
    private String inlineParagraph;
    private int lastParagraph;
    private String subsectionBody;

    Section(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.closingLogic = new CaseClosingLogic(wordParagraph);
        this.inlineParagraph = "";
        this.subsectionBody = "";
    }

    abstract boolean isStillInOneSubsection(int paragraphIndex);

    void parse() {
        if (inlineParagraphHasText())
            inlineParagraph += wordParagraph.getBlankLinesAfterParagraph(startParagraph);
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
        while(wordParagraph.paragraphExists(paragraphIndex) &&
                isStillInOneSubsection(paragraphIndex))
        {
            addParagraphToSubsection(remainingBody, paragraphIndex);
            paragraphIndex++;
        }
        return new TextParagraphIndex(remainingBody.toString(), paragraphIndex);
    }

    private void addParagraphToSubsection(StringBuilder remainingBody, int paragraphIndex) {
        String paragraphText = wordParagraph.getParagraphText(paragraphIndex);
        if (StringFormatting.isCaseSensitive(paragraphText) || StringFormatting.includesNumbers(paragraphText))
            remainingBody.append(paragraphText)
                    .append(wordParagraph.getBlankLinesAfterParagraph(paragraphIndex));
    }

    Result hasNewCaseBodyFormat(int paragraphIndex) {
        if (isParagraphNewCaseBodyStart(paragraphIndex)) {
            return new Result(true, paragraphIndex);
        }

        if (wordParagraph.paragraphExists(paragraphIndex + 1) &&
                wordParagraph.isSectionHeading(paragraphIndex) &&
                isParagraphNewCaseBodyStart(paragraphIndex + 1)) {
            return new Result(true, paragraphIndex + 1);
        }
        return new Result(false);
    }

    private boolean isParagraphNewCaseBodyStart(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex).toLowerCase();
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
        if (!wordParagraph.paragraphExists(startParagraph + 1)) return false;
        String heading = wordParagraph.getParagraphTextWithoutNumbering(startParagraph);
        String firstBodyParagraph = wordParagraph.getParagraphTextWithoutNumbering(startParagraph + 1);
        return heading.toUpperCase().startsWith(URUKIKO) && firstBodyParagraph.toLowerCase().contains(RUSHINGIYE);
    }
}