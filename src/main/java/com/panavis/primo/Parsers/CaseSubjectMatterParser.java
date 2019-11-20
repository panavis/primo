package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.JsonCreator;
import com.panavis.primo.Wrappers.*;

public class CaseSubjectMatterParser implements ICaseSectionParser {

    private CaseParagraph caseParagraph;
    private Section section;
    private int numberOfSubsections;
    private int subsectionStart;
    private JsonObject sectionContent;
    private JsonArray sectionArray;

    public CaseSubjectMatterParser(CaseParagraph caseParagraph, Section sectionSubjectMatter) {
        this.caseParagraph = caseParagraph;
        this.section = sectionSubjectMatter;
        this.numberOfSubsections = 1;
        this.sectionContent = new JsonObject();
        this.sectionArray = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        subsectionStart = startParagraph;
        int nextParagraph = startParagraph;
        while(caseParagraph.paragraphExists(startParagraph) && numberOfSubsections > 0) {
            String inlineParagraph = caseParagraph.getInlineHeadingFirstParagraph(subsectionStart);
            section.setStartingParagraph(subsectionStart)
                    .setInlineParagraph(inlineParagraph)
                    .parse();
            addSubsectionContent(section.getBody());
            nextParagraph = section.getLastParagraph();
            updateSubsectionStartAndNumber(section, nextParagraph);
            numberOfSubsections--;
        }
        sectionArray = JsonArray.escapeSubsections(sectionArray);
        sectionContent.addNameValuePair(SUBJECT_MATTER, sectionArray);
        return new SectionResult(sectionContent, nextParagraph);
    }

    @Override
    public boolean skippedParagraphs() {
        return false;
    }

    private String getSubjectMatterHeading(int startParagraph) {
        return caseParagraph.getHeadingFromParagraph(startParagraph);
    }

    private void updateSubsectionStartAndNumber(Section section, int paragraphIndex) {
        if (caseParagraph.paragraphExists(paragraphIndex) && section.subjectMatterHasAnotherSubsection(paragraphIndex)) {
            numberOfSubsections++;
            subsectionStart = paragraphIndex;
        }
    }

    private void addSubsectionContent(JsonArray subsectionBody) {
        String heading = getSubjectMatterHeading(subsectionStart);
        sectionArray.putValue(JsonCreator.getJsonObject(heading, subsectionBody));
    }
}
