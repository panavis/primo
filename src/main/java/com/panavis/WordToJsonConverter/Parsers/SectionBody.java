package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Style.*;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;

import java.util.regex.Pattern;

class SectionBody extends Section {

    private UnitNumbering currentNumbering;

    SectionBody(WordParagraph wordParagraph) {
        super(wordParagraph);
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        if (isCaseClosing(paragraphIndex)) return false;
        String text = wordParagraph.getParagraphText(paragraphIndex);
        String nextNumbering = currentNumbering.next;
        if (nextNumberingIsAvailable(nextNumbering)) {
            return !matchesNextNumbering(text, nextNumbering);
        }
        String currentStyle = wordParagraph.getUnitNumbering(paragraphIndex).style;
        return !isTextCapitalizedAndHasSameStyle(text, currentStyle);
    }

    boolean isCaseClosing(int nextParagraph) {
        String paragraphText = wordParagraph.getParagraphText(nextParagraph).toLowerCase();

        return isClosingSentence(nextParagraph, paragraphText) ||
                isClosingHeading(nextParagraph, paragraphText);
    }

    private boolean isClosingHeading(int nextParagraph, String paragraphText) {
        return wordParagraph.isIndentedAndCapitalized(nextParagraph) && paragraphText.contains("inteko");
    }

    private boolean isClosingSentence(int paragraphIndex, String text) {
        String firstWord = text.split(" ")[0];
        String style = wordParagraph.getUnitNumbering(paragraphIndex).style;
        return sentenceHasDate(text) &&
                text.contains("rukijijwe") &&
                StringFormatting.isCaseSensitive(firstWord) &&
                !(style.equals("ListParagraph"));
    }

    private boolean sentenceHasDate(String text) {
        Pattern dateAllDigits = Pattern.compile("\\b\\d{1,2}\\/\\d{1,2}\\/\\d{4}");
        Pattern dateMonthSpelled = Pattern.compile("\\b\\d{1,2}\\b.+\\b\\d{4}");
        return dateAllDigits.matcher(text).find() ||
                dateMonthSpelled.matcher(text).find();
    }

    private boolean nextNumberingIsAvailable(String nextNumbering) {
        return !nextNumbering.trim().isEmpty();
    }

    private boolean matchesNextNumbering(String text, String nextNumbering) {
        return text.startsWith(nextNumbering);
    }

    private boolean isTextCapitalizedAndHasSameStyle(String text, String currentStyle) {
        return StringFormatting.isTextCapitalized(text) &&
                currentStyle.equals(currentNumbering.style);
    }

    Section setCurrentNumbering(UnitNumbering unitNumbering) {
        this.currentNumbering = unitNumbering;
        return this;
    }
}
