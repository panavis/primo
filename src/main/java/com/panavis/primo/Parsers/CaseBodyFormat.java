package com.panavis.primo.Parsers;

import com.panavis.primo.ResultTypes.Result;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.panavis.primo.Constants.Headings.URUKIKO;
import static com.panavis.primo.Constants.Keywords.*;

public class CaseBodyFormat {

    private CaseParagraph caseParagraph;
    private static final Pattern dateAllDigits = Pattern.compile("\\b\\d{1,2}\\/\\d{1,2}\\/\\d{4}");
    private static final Pattern dateMonthSpelled = Pattern.compile("\\b\\d{1,2}\\b.+\\b\\d{4}");

    public CaseBodyFormat(CaseParagraph caseParagraph) {
        this.caseParagraph = caseParagraph;
    }

    public Result isOldFormatCaseBody(int startParagraph) {
        if (!caseParagraph.paragraphExists(startParagraph + 1)) return new Result(false);
        String heading = caseParagraph.getParagraphTextWithoutNumbering(startParagraph);
        String firstBodyParagraph = caseParagraph.getParagraphTextWithoutNumbering(startParagraph + 1);
        boolean isOldFormat = heading.toUpperCase().startsWith(URUKIKO) && firstBodyParagraph.toLowerCase().contains(RUSHINGIYE);
        return new Result(isOldFormat, startParagraph);
    }

    public Result isNewFormatCaseBody(int paragraphIndex) {
        if (isParagraphNewCaseBodyStart(paragraphIndex)) {
            return new Result(true, paragraphIndex);
        }

        if (caseParagraph.paragraphExists(paragraphIndex + 1) &&
                caseParagraph.isSectionHeading(paragraphIndex) &&
                isParagraphNewCaseBodyStart(paragraphIndex + 1)) {
            return new Result(true, paragraphIndex + 1);
        }
        return new Result(false);
    }

    private boolean isParagraphNewCaseBodyStart(int paragraphIndex) {
        String text = caseParagraph.getParagraphText(paragraphIndex).toLowerCase();
        List<String> tokens = new ArrayList<>(Arrays.asList(text.split(" ")))
                .stream().filter(t -> !t.isEmpty()).collect(Collectors.toList());

        return text.contains(IMITERERE) && text.contains("y") &&
                text.contains(URUBANZA) && tokens.size() <= 5;
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
        return dateAllDigits.matcher(text).find() ||
                dateMonthSpelled.matcher(text).find();
    }

}
