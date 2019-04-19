package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.Numbering.UnitNumbering;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Style.*;
import com.panavis.primo.Wrappers.*;

public class CaseBodyParser implements ICaseSectionParser {

    private WordParagraph wordParagraph;
    private JsonArray bodySubsections;
    private SectionBody section;

    public CaseBodyParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.section = new SectionBody(wordParagraph);
        this.bodySubsections = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        int nextParagraph = startParagraph;
        if (wordParagraph.paragraphExists(nextParagraph) &&
                section.startsCaseBodySection(startParagraph)) {
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
        return new SectionResult(caseBody, nextParagraph);
    }

    @Override
    public boolean skippedParagraphs() {
        return false;
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
