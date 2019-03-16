package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.JsonCreator;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class CaseSubjectMatterParser implements ICaseSubjectMatter {

    private WordParagraph wordParagraph;
    private int numberOfSubsections;
    private int subsectionStart;
    private JsonObject sectionContent;
    private JsonArray sectionArray;

    public CaseSubjectMatterParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.numberOfSubsections = 1;
        this.sectionContent = new JsonObject();
        this.sectionArray = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        subsectionStart = startParagraph;
        int nextParagraph = startParagraph;
        while(numberOfSubsections > 0) {
            String heading = getSubjectMatterHeading(subsectionStart);
            String inlineParagraph = wordParagraph.getInlineHeadingFirstParagraph(subsectionStart);
            SectionSubjectMatter subsection = (SectionSubjectMatter) new SectionSubjectMatter(wordParagraph, subsectionStart)
                                        .setInlineParagraph(inlineParagraph)
                                        .parse();
            addSubsectionContent(heading, subsection.getBody());
            nextParagraph = subsection.getLastParagraph();
            updateSubsectionStartAndNumber(subsection, nextParagraph);
            numberOfSubsections--;
        }
        return new SectionResult(sectionContent, nextParagraph);
    }

    private String getSubjectMatterHeading(int startParagraph) {
        return wordParagraph.getHeadingFromParagraph(startParagraph);
    }

    private void updateSubsectionStartAndNumber(SectionSubjectMatter subsection, int paragraphIndex) {
        if (subsection.hasAnotherSubsection(paragraphIndex)) {
            numberOfSubsections++;
            subsectionStart = paragraphIndex;
        }
    }

    private void addSubsectionContent(String heading, JsonArray subsectionBody) {
        sectionArray.putValue(JsonCreator.getJsonObject(heading, subsectionBody));
        sectionContent.addNameValuePair(Keywords.SUBJECT_MATTER, sectionArray);
    }
}
