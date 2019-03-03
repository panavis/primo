package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import java.util.*;

class Converter {

    private Map<String, SectionResult> parsedCase;
    private ICaseTitle titleParser;
    private ICaseParties partiesParser;
    private ICaseSubjectMatter subjectMatterParser;

    Converter(ICaseTitle titleParser, ICaseParties partiesParser,
              ICaseSubjectMatter subjectMatterParser) {
        this.parsedCase = new HashMap<>();
        this.titleParser = titleParser;
        this.partiesParser = partiesParser;
        this.subjectMatterParser = subjectMatterParser;
    }

    void parseCaseSections() {
        int nextParagraph;
        SectionResult caseTitle = this.titleParser.parse();
        this.parsedCase.put(Keywords.TITLE, caseTitle);
        nextParagraph = this.parsedCase.get(Keywords.TITLE).getNextParagraph();
        SectionResult caseParties = this.partiesParser.parse(nextParagraph);
        this.parsedCase.put(Keywords.PARTIES, caseParties);
        nextParagraph = this.parsedCase.get(Keywords.PARTIES).getNextParagraph();
        SectionResult caseSubjectMatter = this.subjectMatterParser.parse(nextParagraph);
        this.parsedCase.put(Keywords.SUBJECT_MATTER, caseSubjectMatter);
    }

    SectionResult getParsedCaseSection(String section) {
        return this.parsedCase.get(section);
    }
}
