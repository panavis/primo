package com.panavis.WordToJsonConverter.Parsers;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.JsonCreator;
import com.panavis.WordToJsonConverter.Wrappers.*;

public class CaseSubjectMatterParser implements ICaseSectionParser {

    private WordParagraph wordParagraph;
    private SectionSubjectMatter section;
    private int numberOfSubsections;
    private int subsectionStart;
    private JsonObject sectionContent;
    private JsonArray sectionArray;

    public CaseSubjectMatterParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.section = new SectionSubjectMatter(wordParagraph);
        this.numberOfSubsections = 1;
        this.sectionContent = new JsonObject();
        this.sectionArray = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        subsectionStart = startParagraph;
        int nextParagraph = startParagraph;
        while(numberOfSubsections > 0) {
            String inlineParagraph = wordParagraph.getInlineHeadingFirstParagraph(subsectionStart);
            section.setStartingParagraph(subsectionStart)
                    .setInlineParagraph(inlineParagraph)
                    .parse();
            addSubsectionContent(section.getBody());
            nextParagraph = section.getLastParagraph();
            updateSubsectionStartAndNumber(section, nextParagraph);
            numberOfSubsections--;
        }
        sectionContent.addNameValuePair(SUBJECT_MATTER, sectionArray);
        return new SectionResult(sectionContent, nextParagraph);
    }

    private String getSubjectMatterHeading(int startParagraph) {
        return wordParagraph.getHeadingFromParagraph(startParagraph);
    }

    private void updateSubsectionStartAndNumber(SectionSubjectMatter section, int paragraphIndex) {
        if (section.hasAnotherSubsection(paragraphIndex)) {
            numberOfSubsections++;
            subsectionStart = paragraphIndex;
        }
    }

    private void addSubsectionContent(JsonArray subsectionBody) {
        String heading = getSubjectMatterHeading(subsectionStart);
        sectionArray.putValue(JsonCreator.getJsonObject(heading, subsectionBody));
    }
}
