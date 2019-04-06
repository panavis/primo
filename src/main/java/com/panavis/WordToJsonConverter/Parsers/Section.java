package com.panavis.WordToJsonConverter.Parsers;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.ResultTypes.TextParagraphIndex;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.*;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;

abstract class Section {

    WordParagraph wordParagraph;
    private int startParagraph;
    private String inlineParagraph;
    private int lastParagraph;
    private String subsectionBody;

    Section(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
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
        if (StringFormatting.isCaseSensitive(paragraphText))
            remainingBody.append(paragraphText)
                    .append(wordParagraph.getBlankLinesAfterParagraph(paragraphIndex));
    }

    boolean startsCaseBodySection(int paragraphIndex) {
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
}