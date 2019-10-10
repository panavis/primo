package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;

import java.util.regex.Pattern;

import static com.panavis.primo.Constants.Keywords.*;
import static com.panavis.primo.Constants.Keywords.RUSOMEWE;

class CaseClosingLogic {

    private CaseParagraph caseParagraph;

    CaseClosingLogic(CaseParagraph caseParagraph) {
        this.caseParagraph = caseParagraph;
    }

    boolean isCaseClosing(int nextParagraph) {
        return isClosingSentence(nextParagraph) ||
                isClosingHeading(nextParagraph);
    }

    boolean isClosingHeading(int paragraphIndex) {
        String text = caseParagraph.getParagraphText(paragraphIndex);
        return text.toLowerCase().contains(INTEKO) &&
                (
                        (caseParagraph.hasSignificantLeftIndentation(paragraphIndex) ||
                                StringFormatting.isTextCapitalized(text))
                                ||
                                caseParagraph.isBeginningUnderlined(paragraphIndex)
                );
    }

    boolean isClosingSentence(int paragraphIndex) {
        String text = caseParagraph.getParagraphText(paragraphIndex);
        String firstWord = text.split(" ")[0];
        text = text.toLowerCase();
        return hasClosingKeywords(text) && sentenceHasDate(text) &&
                StringFormatting.isCaseSensitive(firstWord) &&
                !caseParagraph.isListedParagraph(paragraphIndex);
    }

    private boolean hasClosingKeywords(String text) {
        return text.contains(RUKIJIJWE) || text.contains(RUSOMEWE);
    }

    private boolean sentenceHasDate(String text) {
        Pattern dateAllDigits = Pattern.compile("\\b\\d{1,2}\\/\\d{1,2}\\/\\d{4}");
        Pattern dateMonthSpelled = Pattern.compile("\\b\\d{1,2}\\b.+\\b\\d{4}");
        return dateAllDigits.matcher(text).find() ||
                dateMonthSpelled.matcher(text).find();
    }
}
