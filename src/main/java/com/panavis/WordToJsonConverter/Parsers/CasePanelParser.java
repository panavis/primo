package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CasePanelParser implements ICaseSectionParser {

    private WordParagraph wordParagraph;

    public CasePanelParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    @Override
    public SectionResult parse(int startParagraph) {
        int nextParagraph = startParagraph + 1;
        List panelistsTitles = Collections.emptyList();
        List panelistsNames = Collections.emptyList();
        if (wordParagraph.paragraphExists(nextParagraph)) {
            String[] titles = wordParagraph.getParagraphText(nextParagraph).split("\t");
            panelistsTitles = Arrays.asList(titles);
        }
        nextParagraph++;
        if (wordParagraph.paragraphExists(nextParagraph)) {
            String[] names = wordParagraph.getParagraphText(nextParagraph).split("\t");
            panelistsNames = Arrays.asList(names);
        }
        String firstTitle = panelistsTitles.get(0).toString();
        String secondTitle = panelistsTitles.get(1).toString();
        String firstName = panelistsNames.get(0).toString();
        String secondName = panelistsNames.get(1).toString();
        JsonObject firstPanelist = new JsonObject();
        firstPanelist.addNameValuePair(firstTitle, firstName);
        JsonObject secondPanelist = new JsonObject();
        secondPanelist.addNameValuePair(secondTitle, secondName);
        JsonArray panelArray = new JsonArray();
        panelArray.putValue(firstPanelist);
        panelArray.putValue(secondPanelist);
        JsonObject casePanel = new JsonObject();
        casePanel.addNameValuePair(Keywords.INTEKO, panelArray);
        return new SectionResult(casePanel, 0);
    }
}
