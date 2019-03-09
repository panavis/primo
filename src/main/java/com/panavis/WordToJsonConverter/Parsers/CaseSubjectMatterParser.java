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
        while(numberOfSubsections > 0) {
            String heading = getSubjectMatterHeading(subsectionStart);
            Subsection subsection = Subsection.getSubsection(section, wordParagraph, subsectionStart);
            addSubsectionContent(heading, subsection.getBody());
            int nextParagraph = subsection.getLastParagraph();
            if (section.hasAnotherSubjectMatterSubsection(nextParagraph)) {
                updateSubsectionStartAndNumber(nextParagraph);
            }
            numberOfSubsections--;
        }
        return new SectionResult(sectionContent, startParagraph);
    }

    private String getSubjectMatterHeading(int startParagraph) {
        return wordParagraph.getHeadingFromParagraph(startParagraph);
    }

    private void updateSubsectionStartAndNumber(int paragraphIndex) {
        numberOfSubsections++;
        subsectionStart = paragraphIndex;
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