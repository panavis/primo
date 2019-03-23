package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.Style.UnitNumbering;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;

import java.util.regex.Pattern;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;

class SectionBody extends Section {

    private UnitNumbering currentNumbering;

    SectionBody(WordParagraph wordParagraph) {
        super(wordParagraph);
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        if (isCaseClosing(paragraphIndex)) return false;
        String text = wordParagraph.getParagraphText(paragraphIndex);
        UnitNumbering paragraphNumbering = wordParagraph.getUnitNumbering(paragraphIndex);
        if (!currentNumbering.current.equals(Format.EMPTY_STRING) &&
                paragraphHasNumbering(paragraphNumbering.current)) {
            return !matchesNextNumbering(text, currentNumbering.next);
        }
        return !hasSameBodyHeadingFormat(paragraphIndex, text);
    }

    private boolean paragraphHasNumbering(String currentNumbering) {
        return !currentNumbering.trim().isEmpty();
    }

    private boolean matchesNextNumbering(String text, String nextNumbering) {
        return text.startsWith(nextNumbering);
    }

    private boolean hasSameBodyHeadingFormat(int paragraphIndex, String text) {
        String currentStyle = wordParagraph.getUnitNumbering(paragraphIndex).style;
        boolean hasSameStyle = isTextCapitalizedAndHasSameStyle(text, currentStyle);
        boolean nonDynamicNumbering = hasNonDynamicNumbering(paragraphIndex, text, hasSameStyle);
        boolean noNumbering = hasSameStyle &&
                wordParagraph.isBeginningUnderlined(paragraphIndex);
        return nonDynamicNumbering || noNumbering;
    }

    private boolean hasNonDynamicNumbering(int paragraphIndex, String text, boolean hasSameStyle) {
        boolean hasNextHeadingStart = false;
        if (!currentNumbering.next.equals(Format.EMPTY_STRING))
            hasNextHeadingStart = text.startsWith(currentNumbering.next);
        String firstWord = text.split(" ")[0];
        boolean hasNextHeadingInBold = firstWord.endsWith(".") &&
                hasSameStyle &&
                wordParagraph.isFirstRunBold(paragraphIndex);
        return hasNextHeadingStart || hasNextHeadingInBold;
    }

    private boolean isTextCapitalizedAndHasSameStyle(String text, String currentStyle) {
        return StringFormatting.isTextCapitalized(text) &&
                currentStyle.equals(currentNumbering.style);
    }

    boolean isCaseClosing(int nextParagraph) {
        String paragraphText = wordParagraph.getParagraphText(nextParagraph).toLowerCase();
        return isClosingSentence(nextParagraph, paragraphText) ||
                isClosingHeading(nextParagraph, paragraphText);
    }

    private boolean isClosingHeading(int nextParagraph, String paragraphText) {
        return wordParagraph.isIndentedAndCapitalized(nextParagraph) &&
                paragraphText.contains(INTEKO);
    }

    private boolean isClosingSentence(int paragraphIndex, String text) {
        String firstWord = text.split(" ")[0];
        String style = wordParagraph.getUnitNumbering(paragraphIndex).style;
        return hasClosingKeywords(text) && sentenceHasDate(text) &&
                StringFormatting.isCaseSensitive(firstWord) &&
                !(style.equals(LIST_PARAGRAPH));
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

    Section setCurrentNumbering(UnitNumbering unitNumbering) {
        this.currentNumbering = unitNumbering;
        return this;
    }
}
