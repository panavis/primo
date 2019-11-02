package com.panavis.primo.Parsers;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SectionBodyOldFormat extends SectionCaseBody {

    private CaseBodyFormat caseBodyFormat;
    private String lastIndicator;
    private static final List<String> SECTION_INDICATORS = new ArrayList<>(Arrays.asList(
            Keywords.RUMAZE, Keywords.RUSANZE
    ));

    public SectionBodyOldFormat(CaseParagraph caseParagraph, CaseBodyFormat caseBodyFormat) {
        super(caseParagraph);
        this.caseBodyFormat = caseBodyFormat;
        this.lastIndicator = "";
    }

    @Override
    boolean isInNextSubsection(int paragraphIndex) {
        String firstWord = caseParagraph.getParagraphFirstWord(paragraphIndex).toLowerCase();

        if (isSectionIndicator(paragraphIndex) && !isSameAsLastSectionIndicator(paragraphIndex)) {
            lastIndicator = firstWord;
            return true;
        }
        if (nextParagraphHasLastSectionIndicator(paragraphIndex)) {
            return false;
        }

        return (caseParagraph.isSectionHeading(paragraphIndex) && !isHeadingTooLong(paragraphIndex))
                || caseBodyFormat.isCaseClosing(paragraphIndex);
    }

    private boolean nextParagraphHasLastSectionIndicator(int paragraphIndex) {
        return caseParagraph.paragraphExists(paragraphIndex + 1) &&
            isSectionIndicator(paragraphIndex + 1) &&
            isSameAsLastSectionIndicator(paragraphIndex + 1);
    }

    private boolean isSectionIndicator(int paragraphIndex) {
        String firstWord = caseParagraph.getParagraphFirstWord(paragraphIndex).toLowerCase();
        return SECTION_INDICATORS.stream()
                .anyMatch(firstWord::startsWith);
    }

    private boolean isSameAsLastSectionIndicator(int paragraphIndex) {
        String newSectionIndicator = caseParagraph.getParagraphFirstWord(paragraphIndex).toLowerCase();
        if (lastIndicator.isEmpty() && !newSectionIndicator.isEmpty()) return false;

        return lastIndicator.startsWith(newSectionIndicator) || newSectionIndicator.startsWith(lastIndicator);
    }

    @Override
    boolean isHeadingTooLong(int paragraphIndex) {
        int MAX_HEADING_LENGTH = 75; // ~ 55 seen in case data
        String text = caseParagraph.getParagraphText(paragraphIndex);
        return text.length() >= MAX_HEADING_LENGTH;
    }

    @Override
    String getNextNumbering() {
        return StringFormatting.EMPTY_STRING;
    }
}