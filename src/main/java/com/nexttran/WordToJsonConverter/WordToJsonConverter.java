package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import java.util.*;

public class WordToJsonConverter {

    private Map<String, SectionResult> parsedCase;
    private TitleSectionParser titleSectionParser;
    private PartiesSectionParser partiesSectionParser;

    WordToJsonConverter(TitleSectionParser titleSectionParser, PartiesSectionParser partiesSectionParser) {
        this.parsedCase = new HashMap<>();
        this.titleSectionParser = titleSectionParser;
        this.partiesSectionParser = partiesSectionParser;
    }

    void parseCaseSections() {
        int nextParagraph;
        this.parsedCase.put(Keywords.TITLE, this.titleSectionParser.parseCaseTitle());
        nextParagraph = this.parsedCase.get(Keywords.TITLE).getNextParagraph();
        this.parsedCase.put(Keywords.PARTIES, this.partiesSectionParser.parseCaseParties(nextParagraph));
    }

    SectionResult getParsedCaseSection(String section) {
        return this.parsedCase.get(section);
    }
}
