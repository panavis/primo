package com.panavis.primo;

import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.Parsers.*;
import com.panavis.primo.ResultTypes.SectionResult;

public class Primo {

    private ParsedCase parsedCase;
    private int nextParagraph;
    private ICaseSectionParser titleParser;
    private ICaseSectionParser partiesParser;
    private ICaseSectionParser subjectMatterParser;
    private ICaseSectionParser caseBodyParser;
    private ICaseSectionParser caseClosingParser;
    private ICaseSectionParser casePanelParser;

    Primo(ICaseSectionParser titleParser, ICaseSectionParser partiesParser,
          ICaseSectionParser subjectMatterParser, ICaseSectionParser caseBodyParser,
          ICaseSectionParser caseClosingParser, ICaseSectionParser casePanelParser) {
        this.parsedCase = new ParsedCase();
        this.titleParser = titleParser;
        this.partiesParser = partiesParser;
        this.subjectMatterParser = subjectMatterParser;
        this.caseBodyParser = caseBodyParser;
        this.caseClosingParser = caseClosingParser;
        this.casePanelParser = casePanelParser;
    }

    public void parseCaseSections() {
        parseTitleAndUpdateNextParagraph();
        parsePartiesAndUpdateNextParagraph();
        checkIfPartiesParserSkippedParagraphs();
        parseSubjectMatterAndUpdateNextParagraph();
        parseCaseBodyAndUpdateNextParagraph();
        parseCaseClosingAndUpdateNextParagraph();
        parseCasePanel();
    }

    private void parseTitleAndUpdateNextParagraph() {
        SectionResult caseTitle = this.titleParser.parse(0);
        this.parsedCase.set(TITLE, caseTitle);
        nextParagraph = caseTitle.getNextParagraph();
    }

    private void parsePartiesAndUpdateNextParagraph() {
        SectionResult caseParties = this.partiesParser.parse(nextParagraph);
        this.parsedCase.set(PARTIES, caseParties);
        nextParagraph = caseParties.getNextParagraph();
    }

    private void checkIfPartiesParserSkippedParagraphs() {
        this.parsedCase.setSkippedParagraphs(partiesParser.skippedParagraphs());
    }

    private void parseSubjectMatterAndUpdateNextParagraph() {
        SectionResult caseSubjectMatter = this.subjectMatterParser.parse(nextParagraph);
        this.parsedCase.set(SUBJECT_MATTER, caseSubjectMatter);
        nextParagraph = caseSubjectMatter.getNextParagraph();
    }

    private void parseCaseBodyAndUpdateNextParagraph() {
        SectionResult caseBody = this.caseBodyParser.parse(nextParagraph);
        this.parsedCase.set(CASE_BODY, caseBody);
        nextParagraph = caseBody.getNextParagraph();
    }

    private void parseCaseClosingAndUpdateNextParagraph() {
        SectionResult caseClosing = this.caseClosingParser.parse(nextParagraph);
        this.parsedCase.set(CASE_CLOSING, caseClosing);
        nextParagraph = caseClosing.getNextParagraph();
    }

    private void parseCasePanel() {
        SectionResult casePanel = this.casePanelParser.parse(nextParagraph);
        this.parsedCase.set(INTEKO, casePanel);
    }

    public SectionResult getParsedCaseSection(String section) {
        return this.parsedCase.get(section);
    }

    ParsedCase getParsedCase() {
        return this.parsedCase;
    }
}