package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;


import java.util.*;


public class WordToJsonConverter {

    List<SectionResult> parsedCase;
    private TitleSectionParser titleSectionParser;
    private PartiesSectionParser partiesSectionParser;

    WordToJsonConverter(TitleSectionParser titleSectionParser, PartiesSectionParser partiesSectionParser) {
        this.parsedCase = new ArrayList<>();
        this.titleSectionParser = titleSectionParser;
        this.partiesSectionParser = partiesSectionParser;
    }

    void parseCaseSections() {
        int nextParagraph;
        this.parsedCase.add(this.titleSectionParser.parseCaseTitle());
        nextParagraph = this.parsedCase.get(0).getNextParagraph();
        this.parsedCase.add(this.partiesSectionParser.parseCaseParties(nextParagraph));
    }
}
