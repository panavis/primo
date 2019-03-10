package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.JsonCreator;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class CaseBodyParser implements ICaseBodyParser {

    private WordParagraph wordParagraph;
    private SectionBody section;

    public CaseBodyParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.section = new SectionBody(wordParagraph);
    }

    public SectionResult parse(int startParagraph) {
        JsonObject sectionContent = new JsonObject();
        if (wordParagraph.isSectionHeading(startParagraph)) {
            section.setCurrentNumbering(wordParagraph.getUnitNumbering(startParagraph));
            String heading = wordParagraph.getParagraphText(startParagraph);
            Subsection subsection = new Subsection(section, wordParagraph, startParagraph)
                                        .parse();
            String[] contentArray = subsection.getBody().split(Format.DOUBLE_BLANK);
            JsonArray subsectionBody = JsonCreator.getJsonArrayFromStringArray(contentArray);
            sectionContent.addNameValuePair(heading, subsectionBody);
        }
        return new SectionResult(sectionContent, 0);
    }
}
