package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.ResultTypes.TextParagraphIndex;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.JsonCreator;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;

abstract class Subsection {

    WordParagraph wordParagraph;
    private int startParagraph;
    private String inlineParagraph;
    private int lastParagraph;
    private String subsectionBody;

    Subsection(WordParagraph wordParagraph, int startParagraph) {
        this.wordParagraph = wordParagraph;
        this.startParagraph = startParagraph;
        this.inlineParagraph = "";
        this.subsectionBody = "";
    }

    abstract boolean isStillInOneSubsection(int paragraphIndex);

    Subsection parse() {
        if (inlineParagraphHasText())
            inlineParagraph = inlineParagraph + wordParagraph.getBlankLinesAfterParagraph(startParagraph);
        TextParagraphIndex remainingAndIndex = getRemainingSubsectionBody(startParagraph);
        subsectionBody = inlineParagraph.concat(remainingAndIndex.getSubsectionParagraphs()).trim();
        lastParagraph = remainingAndIndex.getParagraphIndex();
        return this;
    }

    private boolean inlineParagraphHasText() {
        return inlineParagraph.length() != 0;
    }

    private TextParagraphIndex getRemainingSubsectionBody(int startParagraph) {
        StringBuilder remainingBody = new StringBuilder();
        int paragraphIndex = startParagraph + 1;
        while(isStillInOneSubsection(paragraphIndex)) {
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

    Subsection setInlineParagraph(String inlineParagraph) {
        this.inlineParagraph = inlineParagraph;
        return this;
    }

    JsonArray getBody() {
        String[] contentArray = subsectionBody.split(Format.DOUBLE_BLANK);
        return JsonCreator.getJsonArrayFromStringArray(contentArray);
    }

    int getLastParagraph() {
        return lastParagraph;
    }
}