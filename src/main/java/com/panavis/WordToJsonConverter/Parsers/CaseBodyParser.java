package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
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
            heading = StringFormatting.trimColons(heading);
            Subsection subsection = new Subsection(section, wordParagraph, startParagraph)
                                        .parse();
            sectionContent.addNameValuePair(heading, subsection.getBody());
        }
        return new SectionResult(sectionContent, 0);
    }
}
