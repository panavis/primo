package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.Parsers.ICaseBodyParser;
import com.panavis.WordToJsonConverter.Parsers.ICaseParties;
import com.panavis.WordToJsonConverter.Parsers.ICaseSubjectMatter;
import com.panavis.WordToJsonConverter.Parsers.ICaseTitle;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;

import java.util.*;

class Converter {

    private Map<String, SectionResult> parsedCase;
    private int nextParagraph;
    private ICaseTitle titleParser;
    private ICaseParties partiesParser;
    private ICaseSubjectMatter subjectMatterParser;
    private ICaseBodyParser caseBodyParser;

    Converter(ICaseTitle titleParser, ICaseParties partiesParser,
              ICaseSubjectMatter subjectMatterParser, ICaseBodyParser caseBodyParser) {
        this.parsedCase = new HashMap<>();
        this.titleParser = titleParser;
        this.partiesParser = partiesParser;
        this.subjectMatterParser = subjectMatterParser;
        this.caseBodyParser = caseBodyParser;
    }

    void parseCaseSections() {
        parseTitleAndUpdateNextParagraph();
        parsePartiesAndUpdateNextParagraph();
        parseSubjectMatterAndUpdateNextParagraph();
        SectionResult caseBody = this.caseBodyParser.parse(nextParagraph);
        this.parsedCase.put(Keywords.CASE_BODY, caseBody);
    }

    private void parseTitleAndUpdateNextParagraph() {
        SectionResult caseTitle = this.titleParser.parse();
        this.parsedCase.put(Keywords.TITLE, caseTitle);
        nextParagraph = caseTitle.getNextParagraph();
    }

    private void parsePartiesAndUpdateNextParagraph() {
        SectionResult caseParties = this.partiesParser.parse(nextParagraph);
        this.parsedCase.put(Keywords.PARTIES, caseParties);
        nextParagraph = caseParties.getNextParagraph();
    }

    private void parseSubjectMatterAndUpdateNextParagraph() {
        SectionResult caseSubjectMatter = this.subjectMatterParser.parse(nextParagraph);
        this.parsedCase.put(Keywords.SUBJECT_MATTER, caseSubjectMatter);
        nextParagraph = caseSubjectMatter.getNextParagraph();
    }

    SectionResult getParsedCaseSection(String section) {
        return this.parsedCase.get(section);
    }
}