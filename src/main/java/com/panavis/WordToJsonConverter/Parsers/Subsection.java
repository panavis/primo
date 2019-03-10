package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.ResultTypes.TextParagraphIndex;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.JsonCreator;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;

import java.util.Arrays;

class Subsection {

    private WordParagraph wordParagraph;
    private ISection section;
    private int startParagraph;
    private String inlineFirstParagraph;
    private int lastParagraph;
    private String subsectionBody;

    Subsection(ISection section, WordParagraph wordParagraph, int startParagraph) {
        this.section = section;
        this.wordParagraph = wordParagraph;
        this.startParagraph = startParagraph;
        this.inlineFirstParagraph = "";
        this.subsectionBody = "";
    }

    Subsection setInlineParagraph(String inlineParagraph) {
        inlineFirstParagraph = inlineParagraph;
        return this;
    }

    Subsection parse() {
        setInlineParagraph();
        TextParagraphIndex remainingAndIndex = getRemainingSubsectionBody(startParagraph);
        subsectionBody = inlineFirstParagraph.concat(remainingAndIndex.getSubsectionParagraphs()).trim();
        lastParagraph = remainingAndIndex.getParagraphIndex();
        return this;
    }

    private void setInlineParagraph() {
        inlineFirstParagraph = inlineFirstParagraph.length() == 0 ?
                "" : inlineFirstParagraph + wordParagraph.getBlankLinesAfterParagraph(startParagraph);
    }

    JsonArray getBody() {
        String[] contentArray = subsectionBody.split(Format.DOUBLE_BLANK);
        return JsonCreator.getJsonArrayFromStringArray(contentArray);
    }

    int getLastParagraph() {
        return lastParagraph;
    }

    private TextParagraphIndex getRemainingSubsectionBody(int startParagraph) {
        StringBuilder remainingBody = new StringBuilder();
        int paragraphIndex = startParagraph + 1;
        while(section.isStillInOneSubsection(paragraphIndex)) {
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
}