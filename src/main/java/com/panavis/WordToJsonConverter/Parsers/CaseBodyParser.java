package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class CaseBodyParser implements ICaseBodyParser {

    private WordParagraph wordParagraph;

    public CaseBodyParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    public SectionResult parse(int startParagraph) {
        JsonObject sectionContent = new JsonObject();
        if (wordParagraph.isSectionHeading(startParagraph)) {
            String heading = wordParagraph.getParagraphText(startParagraph);
            sectionContent.addNameValuePair(heading, "");
        }
        return new SectionResult(sectionContent, 0);
    }
}
