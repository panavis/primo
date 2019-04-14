package com.panavis.WordToJsonConverter.Parsers;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

import java.util.*;

class PanelSectionLine {

    List<String> panelLine;
    int index;

    PanelSectionLine(List<String> panelLine, int index) {
        this.panelLine = panelLine;
        this.index = index;
    }
}

public class CasePanelParser implements ICaseSectionParser {

    private WordParagraph wordParagraph;
    private SectionClosing sectionClosing;

    public CasePanelParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.sectionClosing = new SectionClosing(wordParagraph);
    }

    @Override
    public SectionResult parse(int startParagraph) {
        int nextParagraph = this.sectionClosing.isClosingHeading(startParagraph) ?
                            startParagraph + 1 : startParagraph;
        JsonArray panelArray = new JsonArray();
        boolean hasReachedEnding = false;
        while (wordParagraph.paragraphExists(nextParagraph) && !hasReachedEnding) {
            PanelSectionLine firstPanelLine = getPanelSectionLine(nextParagraph);
            List<String> panelistsTitles = firstPanelLine.panelLine;
            nextParagraph = firstPanelLine.index + 1;

            List<String> panelistsNames;
            String[] firstListElement = panelistsTitles.get(0).split(" ");
            String firstWord = firstListElement[0];
            if (hasTitleKeywordInList(panelistsTitles) && !isJudge(firstWord) && !isCaseWriter(firstWord)) {
                int titleIndex = getPanelistTitleIndex(firstListElement);
                panelistsTitles.set(0, String.join(" ", Arrays.copyOfRange(firstListElement, 0, titleIndex)));
                panelistsNames = new ArrayList<>(panelistsTitles);
                String[] titleArray = Arrays.copyOfRange(firstListElement, titleIndex, firstListElement.length);
                String title = String.join(" ", titleArray);
                panelistsTitles = new ArrayList<>();
                panelistsTitles.add(title);
            } else {
                PanelSectionLine secondPanelLine = getPanelSectionLine(nextParagraph);
                panelistsNames = secondPanelLine.panelLine;
                nextParagraph = secondPanelLine.index + 1;
            }

            boolean orderIsReversed = hasTitleKeywordInList(panelistsNames);
            List panelistsNamesCopy = Arrays.asList(panelistsNames.toArray());
            if (orderIsReversed) {
                panelistsNames = panelistsTitles;
                panelistsTitles = panelistsNamesCopy;
            }

            ListIterator<String> namesIterator = panelistsNames.listIterator();
            ListIterator<String> titlesIterator = panelistsTitles.listIterator();
            while (namesIterator.hasNext() && titlesIterator.hasNext()) {
                String title = titlesIterator.next();
                String name = namesIterator.next();
                if (hasTabInOneFullName(panelistsNames, namesIterator, name))
                    name = name + " " + namesIterator.next();
                JsonObject panelist = new JsonObject();
                panelist.addNameValuePair(title, name);
                panelArray.putValue(panelist);
            }
            hasReachedEnding = hasReachedCaseWriter(panelistsTitles) && !hasNonWriterTitlesBelow(nextParagraph);
        }

        JsonObject casePanel = new JsonObject();
        casePanel.addNameValuePair(INTEKO, panelArray);
        return new SectionResult(casePanel, 0);
    }

    private int getPanelistTitleIndex(String[] firstListElement) {
        int titleIndex = 0;
        for (int i = 0; i < firstListElement.length; i++) {
            String word = firstListElement[i];
            if (isCaseWriter(word) || isJudge(word)) {
                titleIndex = i;
                break;
            }
        }
        return titleIndex;
    }

    private boolean hasTitleKeywordInList(List<String> panelistsNames) {
        boolean orderIsReversed = false;
        for (String word : panelistsNames) {
            if (hasPanelistTitleKeyword(word)) {
                orderIsReversed = true;
            }
        }
        return orderIsReversed;
    }

    private PanelSectionLine getPanelSectionLine(int paragraphIndex) {
        List<String> panelLine = Collections.emptyList();
        if (isSignatureLine(paragraphIndex)) paragraphIndex++;
        if (wordParagraph.paragraphExists(paragraphIndex)) {
            String[] words = wordParagraph.getParagraphText(paragraphIndex).split("\t");
            panelLine = Arrays.asList(words);
        }
        return new PanelSectionLine(panelLine, paragraphIndex);
    }

    private boolean isSignatureLine(int nextParagraph) {
        boolean signatureLine = true;
        if (wordParagraph.paragraphExists(nextParagraph)) {
            String paragraphText = wordParagraph.getParagraphText(nextParagraph);
            String[] words = paragraphText.split("\t");
            for (String word : words) {
                String firstLetter = word.substring(0,1);
                if (!StringFormatting.isCaseSensitive(firstLetter)) {
                    break;
                }
                if (!word.toLowerCase().startsWith("s") || word.length() > 3) {
                    signatureLine = false;
                    break;
                }
            }
        }
        return signatureLine;
    }

    private boolean hasTabInOneFullName(List<String> panelistsNames,
                                        ListIterator<String> namesIterator, String name) {
        return name.split(" ").length == 1 &&
                panelistsNames.size() > 2 &&
                namesIterator.hasNext();
    }

    private boolean hasReachedCaseWriter(List<String> panelistsTitles) {
        return panelistsTitles.stream()
                .anyMatch(this::isCaseWriter);
    }

    private boolean isCaseWriter(String word) {
        String w = word.toLowerCase();
        return w.contains("anditsi");
    }

    private boolean hasNonWriterTitlesBelow(int paragraphIndex) {
        boolean hasOtherTitles = false;
        while (wordParagraph.paragraphExists(paragraphIndex)) {
            String text = wordParagraph.getParagraphText(paragraphIndex).toLowerCase();
            if (isJudge(text))
                hasOtherTitles = true;
            paragraphIndex++;
        }
        return hasOtherTitles;
    }

    private boolean hasPanelistTitleKeyword(String word) {
        return isJudge(word) || isCaseWriter(word);
    }

    private boolean isJudge(String word) {
        String w = word.toLowerCase();
        return w.contains("camanza") || w.contains("perezida");
    }
}
