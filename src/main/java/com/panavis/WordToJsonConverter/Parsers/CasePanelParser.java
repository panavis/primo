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

public class CasePanelParser implements ICaseSectionParser {

    private WordParagraph wordParagraph;
    private SectionClosing sectionClosing;

    public CasePanelParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.sectionClosing = new SectionClosing(wordParagraph);
    }

    @Override
    public SectionResult parse(int startParagraph) {
        List panelistsTitles = Collections.emptyList();
        List panelistsNames = Collections.emptyList();
        int nextParagraph = this.sectionClosing.isClosingHeading(startParagraph) ?
                            startParagraph + 1 : startParagraph;
        if (isSignatureLine(nextParagraph)) nextParagraph++;
        if (wordParagraph.paragraphExists(nextParagraph)) {
            String[] titles = wordParagraph.getParagraphText(nextParagraph).split("\t");
            panelistsTitles = Arrays.asList(titles);
        }
        nextParagraph++;
        if (isSignatureLine(nextParagraph)) nextParagraph++;
        if (wordParagraph.paragraphExists(nextParagraph)) {
            String[] names = wordParagraph.getParagraphText(nextParagraph).split("\t");
            panelistsNames = Arrays.asList(names);
        }

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


        String firstTitle = panelistsTitles.get(0).toString();
        String secondTitle = panelistsTitles.get(1).toString();
        String firstName = panelistsNames.get(0).toString();
        int secondNameIndex = 1;
        if ((firstName.split(" ").length == 1) && panelistsNames.size() > 2) {
            firstName = firstName + " " + panelistsNames.get(1);
            secondNameIndex = 2;
        }
        String secondName = panelistsNames.get(secondNameIndex).toString();
        JsonObject firstPanelist = new JsonObject();
        firstPanelist.addNameValuePair(firstTitle, firstName);
        JsonObject secondPanelist = new JsonObject();
        secondPanelist.addNameValuePair(secondTitle, secondName);
        JsonArray panelArray = new JsonArray();
        panelArray.putValue(firstPanelist);
        panelArray.putValue(secondPanelist);
        JsonObject casePanel = new JsonObject();
        casePanel.addNameValuePair(INTEKO, panelArray);
        return new SectionResult(casePanel, 0);
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
