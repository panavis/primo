package com.panavis.WordToJsonConverter;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.Parsers.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;

import java.util.*;

class Converter {

    private Map<String, SectionResult> parsedCase;
    private int nextParagraph;
    private ICaseSectionParser titleParser;
    private ICaseSectionParser partiesParser;
    private ICaseSectionParser subjectMatterParser;
    private ICaseSectionParser caseBodyParser;
    private ICaseSectionParser caseClosingParser;

    Converter(ICaseSectionParser titleParser, ICaseSectionParser partiesParser,
              ICaseSectionParser subjectMatterParser, ICaseSectionParser caseBodyParser,
              ICaseSectionParser caseClosingParser) {
        this.parsedCase = new HashMap<>();
        this.titleParser = titleParser;
        this.partiesParser = partiesParser;
        this.subjectMatterParser = subjectMatterParser;
        this.caseBodyParser = caseBodyParser;
        this.caseClosingParser = caseClosingParser;
    }

    void parseCaseSections() {
        parseTitleAndUpdateNextParagraph();
        parsePartiesAndUpdateNextParagraph();
        parseSubjectMatterAndUpdateNextParagraph();
        parseCaseBodyAndUpdateNextParagraph();
        SectionResult caseClosing = this.caseClosingParser.parse(nextParagraph);
        this.parsedCase.put(CASE_CLOSING, caseClosing);
    }

    private void parseTitleAndUpdateNextParagraph() {
        SectionResult caseTitle = this.titleParser.parse(0);
        this.parsedCase.put(TITLE, caseTitle);
        nextParagraph = caseTitle.getNextParagraph();
    }

    private void parsePartiesAndUpdateNextParagraph() {
        SectionResult caseParties = this.partiesParser.parse(nextParagraph);
        this.parsedCase.put(PARTIES, caseParties);
        nextParagraph = caseParties.getNextParagraph();
    }

    private void parseSubjectMatterAndUpdateNextParagraph() {
        SectionResult caseSubjectMatter = this.subjectMatterParser.parse(nextParagraph);
        this.parsedCase.put(SUBJECT_MATTER, caseSubjectMatter);
        nextParagraph = caseSubjectMatter.getNextParagraph();
    }

    private void parseCaseBodyAndUpdateNextParagraph() {
        SectionResult caseBody = this.caseBodyParser.parse(nextParagraph);
        this.parsedCase.put(CASE_BODY, caseBody);
        nextParagraph = caseBody.getNextParagraph();
    }

    SectionResult getParsedCaseSection(String section) {
        return this.parsedCase.get(section);
    }

    Map<String, SectionResult> getParsedCase() {
        return this.parsedCase;
    }
}