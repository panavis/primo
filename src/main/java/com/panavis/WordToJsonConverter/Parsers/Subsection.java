package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.ResultTypes.TextParagraphIndex;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;

class Subsection {

    private WordParagraph wordParagraph;
    private ISection section;
    private int startParagraph;
    private String inlineFirstParagraph;
    private int lastParagraph;
    private String subsectionBody;

    Subsection(ISection section, WordParagraph wordParagraph, int startParagraph, String inlineFirstParagraph) {
        this.section = section;
        this.wordParagraph = wordParagraph;
        this.startParagraph = startParagraph;
        this.inlineFirstParagraph = inlineFirstParagraph;
        this.subsectionBody = "";
    }


    void parse() {
        inlineFirstParagraph = inlineFirstParagraph.length() == 0 ?
                "" : inlineFirstParagraph + wordParagraph.getBlankLinesAfterParagraph(startParagraph);
        TextParagraphIndex remainingAndIndex = getRemainingSubsectionBody(startParagraph);
        subsectionBody = inlineFirstParagraph.concat(remainingAndIndex.getSubsectionParagraphs()).trim();
        lastParagraph = remainingAndIndex.getParagraphIndex();
    }

    String getBody() {
        return subsectionBody;
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

    static Subsection getSubsection(ISection section, WordParagraph word, int startParagraph) {
        String inlineParagraph = word.getInlineHeadingFirstParagraph(startParagraph);
        Subsection subsection = new Subsection(section, word, startParagraph, inlineParagraph);
        subsection.parse();
        return subsection;
    }
}