package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;

import java.util.regex.Pattern;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import static com.panavis.WordToJsonConverter.Constants.Keywords.RUSOMEWE;

class SectionClosing extends Section {

    private boolean endingReached;

    SectionClosing(WordParagraph wordParagraph) {
        super(wordParagraph);
        this.endingReached = false;
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        if (endingReached) return false;
        if (wordParagraph.getNumberOfAfterBlanks(paragraphIndex) > 1)
            endingReached = true;
        return !isClosingHeading(paragraphIndex);
    }

    boolean isClosingHeading(int nextParagraph) {
        String text = wordParagraph.getParagraphText(nextParagraph);
        return text.toLowerCase().trim().contains(INTEKO) &&
                (
                        (wordParagraph.hasSignificantLeftIndentation(nextParagraph) ||
                        StringFormatting.isTextCapitalized(text))
                ||
                                wordParagraph.isBeginningUnderlined(nextParagraph)
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
