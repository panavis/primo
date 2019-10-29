package com.panavis.primo;

import com.panavis.primo.Parsers.ICaseSectionParser;
import com.panavis.primo.Parsers.ParsedCase;
import com.panavis.primo.ResultTypes.SectionResult;

import static com.panavis.primo.Constants.Keywords.*;

public class Primo {

    private ParsedCase parsedCase;
    private int nextParagraph;
    private ICaseSectionParser titleParser;
    private ICaseSectionParser partiesParser;
    private ICaseSectionParser subjectMatterParser;
    private ICaseSectionParser preCaseBodyParser;
    private ICaseSectionParser caseBodyParser;
    private ICaseSectionParser caseClosingParser;
    private ICaseSectionParser casePanelParser;

    Primo(ICaseSectionParser titleParser, ICaseSectionParser partiesParser,
          ICaseSectionParser subjectMatterParser, ICaseSectionParser preCaseBodyParser,
          ICaseSectionParser caseBodyParser, ICaseSectionParser caseClosingParser, ICaseSectionParser casePanelParser) {
        this.parsedCase = new ParsedCase();
        this.titleParser = titleParser;
        this.partiesParser = partiesParser;
        this.subjectMatterParser = subjectMatterParser;
        this.preCaseBodyParser = preCaseBodyParser;
        this.caseBodyParser = caseBodyParser;
        this.caseClosingParser = caseClosingParser;
        this.casePanelParser = casePanelParser;
    }

    public void parseCaseSections() {
        parseCaseTitle();
        parseCaseParties();
        parseCaseSubjectMatter();
        parsePreCaseBody();
        parseCaseBody();
        parseCaseClosing();
        parseCasePanel();
    }

    private void parseCaseTitle() {
        SectionResult caseTitle = titleParser.parse(0);
        parsedCase.set(TITLE, caseTitle);
        nextParagraph = caseTitle.getNextParagraph();
    }

    private void parseCaseParties() {
        SectionResult caseParties = partiesParser.parse(nextParagraph);
        parsedCase.set(PARTIES, caseParties);
        nextParagraph = caseParties.getNextParagraph();
    }

    private void parseCaseSubjectMatter() {
        SectionResult caseSubjectMatter = subjectMatterParser.parse(nextParagraph);
        parsedCase.set(SUBJECT_MATTER, caseSubjectMatter);
        nextParagraph = caseSubjectMatter.getNextParagraph();
    }

    private void parsePreCaseBody() {
        SectionResult preCaseBody = preCaseBodyParser.parse(nextParagraph);
        parsedCase.set(PRE_CASE_BODY, preCaseBody);
        nextParagraph = preCaseBody.getNextParagraph();
    }

    private void parseCaseBody() {
        SectionResult caseBody = caseBodyParser.parse(nextParagraph);
        parsedCase.set(CASE_BODY, caseBody);
        nextParagraph = caseBody.getNextParagraph();
    }

    private void parseCaseClosing() {
        SectionResult caseClosing = caseClosingParser.parse(nextParagraph);
        parsedCase.set(CASE_CLOSING, caseClosing);
        nextParagraph = caseClosing.getNextParagraph();
    }

    private void parseCasePanel() {
        SectionResult casePanel = casePanelParser.parse(nextParagraph);
        parsedCase.set(INTEKO, casePanel);
        parsedCase.setSkippedParagraphs(casePanelParser.skippedParagraphs());
    }

    public SectionResult getParsedCaseSection(String section) {
        return parsedCase.get(section);
    }

    public ParsedCase getParsedCase() {
        return parsedCase;
    }
}