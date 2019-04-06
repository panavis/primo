package com.panavis.WordToJsonConverter.Parsers;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.Numbering.UnitNumbering;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import com.panavis.WordToJsonConverter.Style.*;
import com.panavis.WordToJsonConverter.Wrappers.*;

public class CaseBodyParser implements ICaseBodyParser {

    private WordParagraph wordParagraph;
    private JsonArray bodySubsections;
    private SectionBody section;

    public CaseBodyParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.section = new SectionBody(wordParagraph);
        this.bodySubsections = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        if (section.startsCaseBodySection(startParagraph)) {
            int nextParagraph = startParagraph;
            while(wordParagraph.paragraphExists(nextParagraph) &&
                    !section.isCaseClosing(nextParagraph))
            {
                UnitNumbering numbering = wordParagraph.getUnitNumbering(nextParagraph);
                section.setCurrentNumbering(numbering)
                        .setStartingParagraph(nextParagraph)
                        .parse();
                addCaseBodySubsection(nextParagraph, section);
                nextParagraph = section.getLastParagraph();
            }
        }
        JsonObject caseBody = getCaseBody();
        return new SectionResult(caseBody, 0);
    }

    private void addCaseBodySubsection(int startParagraph, Section section) {
        String heading = wordParagraph.getParagraphText(startParagraph);
        heading = StringFormatting.trimColons(heading);
        JsonObject sectionContent = new JsonObject();
        sectionContent.addNameValuePair(heading, section.getBody());
        bodySubsections.putValue(sectionContent);
    }

    private JsonObject getCaseBody() {
        JsonObject caseBody = new JsonObject();
        caseBody.addNameValuePair(CASE_BODY, bodySubsections);
        return caseBody;
    }
}
