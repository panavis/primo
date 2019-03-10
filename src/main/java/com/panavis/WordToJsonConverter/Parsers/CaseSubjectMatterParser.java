package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

import java.util.Arrays;

public class CaseSubjectMatterParser implements ICaseSubjectMatter {

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
            String heading = getSubjectMatterHeading(subsectionStart);
            String inlineParagraph = wordParagraph.getInlineHeadingFirstParagraph(subsectionStart);
            Subsection subsection = new Subsection(section, wordParagraph, subsectionStart)
                                        .setInlineParagraph(inlineParagraph)
                                        .parse();
            addSubsectionContent(heading, subsection.getBody());
            nextParagraph = subsection.getLastParagraph();
            updateSubsectionStartAndNumber(nextParagraph);
            numberOfSubsections--;
        }
        return new SectionResult(sectionContent, nextParagraph);
    }

    private String getSubjectMatterHeading(int startParagraph) {
        return wordParagraph.getHeadingFromParagraph(startParagraph);
    }

    private void updateSubsectionStartAndNumber(int paragraphIndex) {
        if (section.hasAnotherSubjectMatterSubsection(paragraphIndex)) {
            numberOfSubsections++;
            subsectionStart = paragraphIndex;
        }
    }

    private void addSubsectionContent(String heading, String body) {
        JsonObject nestedJson = new JsonObject();
        JsonArray sectionNestedArray = new JsonArray();
        for (String paragraph : body.split(Format.DOUBLE_BLANK)) {
            sectionNestedArray.putValue(paragraph);
        }
        nestedJson.addNameValuePair(heading, sectionNestedArray);
        sectionArray.putValue(nestedJson);
        sectionContent.addNameValuePair(Keywords.SUBJECT_MATTER, sectionArray);
    }
}
