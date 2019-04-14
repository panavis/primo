package com.panavis.WordToJsonConverter.Parsers;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

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

        PanelSectionLine panelLineFirstLine = getPanelSectionLine(nextParagraph);
        List<String> panelistsTitles = panelLineFirstLine.panelLine;
        nextParagraph = panelLineFirstLine.index + 1;
        PanelSectionLine panelLineSecondLine = getPanelSectionLine(nextParagraph);
        List<String> panelistsNames = panelLineSecondLine.panelLine;

        boolean isPanelistList = false;
        for (Object word: panelistsNames) {
            String w = word.toString().toLowerCase();
            if (w.contains("camanza") || w.contains("anditsi") || w.contains("perezida")) {
                isPanelistList = true;
            }
        }
        List panelistsNamesCopy = Arrays.asList(panelistsNames.toArray());
        if (isPanelistList) {
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

        JsonObject casePanel = new JsonObject();
        casePanel.addNameValuePair(INTEKO, panelArray);
        return new SectionResult(casePanel, 0);
    }

    private boolean hasTabInOneFullName(List<String> panelistsNames, ListIterator<String> namesIterator, String name) {
        return name.split(" ").length == 1 &&
                panelistsNames.size() > 2 &&
                namesIterator.hasNext();
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
}
