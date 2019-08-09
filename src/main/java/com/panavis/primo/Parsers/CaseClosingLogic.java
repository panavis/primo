package com.panavis.primo.Parsers;

import com.panavis.primo.Style.WordParagraph;
import com.panavis.primo.Utils.StringFormatting;

import java.util.regex.Pattern;

import static com.panavis.primo.Constants.Keywords.*;
import static com.panavis.primo.Constants.Keywords.RUSOMEWE;

class CaseClosingLogic {

    private WordParagraph wordParagraph;

    CaseClosingLogic(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    boolean isCaseClosing(int nextParagraph) {
        return isClosingSentence(nextParagraph) ||
                isClosingHeading(nextParagraph);
    }

    boolean isClosingHeading(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex);
        return text.toLowerCase().trim().contains(INTEKO) &&
                (
                        (wordParagraph.hasSignificantLeftIndentation(paragraphIndex) ||
                                StringFormatting.isTextCapitalized(text))
                                ||
                                wordParagraph.isBeginningUnderlined(paragraphIndex)
                );
    }

    boolean isClosingSentence(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex);
        String firstWord = text.split(" ")[0];
        String style = wordParagraph.getUnitNumbering(paragraphIndex).style;
        text = text.toLowerCase();
        return hasClosingKeywords(text) && sentenceHasDate(text) &&
                StringFormatting.isCaseSensitive(firstWord) &&
                !style.equals(LIST_PARAGRAPH);
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
