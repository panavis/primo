package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.WordParagraph;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import java.util.*;
import java.util.regex.Pattern;

class PanelSectionLine {

    List<String> panelLine;
    int index;

    PanelSectionLine(List<String> panelLine, int index) {
        this.panelLine = panelLine;
        this.index = index;
    }
}

class NamesAndTitles {

    List<String> names;
    List<String> titles;

    NamesAndTitles(List<String> names, List<String> titles) {
        this.names = names;
        this.titles = titles;
    }
}

public class CasePanelParser implements ICaseSectionParser {

    private WordParagraph wordParagraph;
    private SectionClosing sectionClosing;
    private JsonArray panelArray;
    private int nextParagraph;

    public CasePanelParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.sectionClosing = new SectionClosing(wordParagraph);
        this.panelArray = new JsonArray();
        this.nextParagraph = wordParagraph.numberOfParagraphs();
    }

    public SectionResult parse(int startParagraph) {
        nextParagraph = getFirstParagraphOfPanelSection(startParagraph);
        boolean hasReachedEnding = false;
        while (wordParagraph.paragraphExists(nextParagraph) && !hasReachedEnding) {
            NamesAndTitles namesAndTitles = getSubsequentNamesAndTitles();
            addTitlesAndNamesToPanelArray(namesAndTitles.titles, namesAndTitles.names);
            hasReachedEnding = hasReachedCaseWriter(namesAndTitles.titles) && !hasNonWriterTitlesBelow(nextParagraph);
        }
        JsonObject casePanel = new JsonObject();
        casePanel.addNameValuePair(INTEKO, panelArray);
        return new SectionResult(casePanel, 0);
    }

    @Override
    public boolean skippedParagraphs() {
        return false;
    }

    private int getFirstParagraphOfPanelSection(int startParagraph) {
        int panelStart = wordParagraph.numberOfParagraphs();
        if (wordParagraph.paragraphExists(startParagraph))
            panelStart = this.sectionClosing.isClosingHeading(startParagraph) ?
                    startParagraph + 1 : startParagraph;
        return panelStart;
    }

    private NamesAndTitles getSubsequentNamesAndTitles() {
        PanelSectionLine firstPanelLine = getPanelSectionLine(nextParagraph);
        List<String> panelistsTitles = firstPanelLine.panelLine;
        nextParagraph = firstPanelLine.index + 1;
        NamesAndTitles namesAndTitles = new NamesAndTitles(new ArrayList<>(), panelistsTitles);
        if (isNameAndTitleOnTheSameLine(panelistsTitles)) {
            splitTheLineAndSetNewNamesAndTitles(panelistsTitles, namesAndTitles);
        } else {
            PanelSectionLine secondPanelLine = getPanelSectionLine(nextParagraph);
            namesAndTitles.names = secondPanelLine.panelLine;
            nextParagraph = secondPanelLine.index + 1;
        }
        updateOrderIfReversed(namesAndTitles);
        return namesAndTitles;
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
        boolean signatureLine = false;
        if (wordParagraph.paragraphExists(nextParagraph)) {
            signatureLine = firstWordMatchesSignaturePattern(nextParagraph);

        }
        return signatureLine;
    }

    private boolean firstWordMatchesSignaturePattern(int nextParagraph) {
        boolean isMatch = false;
        String paragraphText = wordParagraph.getParagraphText(nextParagraph);
        String[] words = paragraphText.split("\t");
        String firstWord = words[0].toLowerCase();
        String firstLetter = firstWord.length() != 0 ?
                            firstWord.substring(0,1) : "CASE_SENSITIVE_PLACEHOLDER";
        if (!StringFormatting.isCaseSensitive(firstLetter)) {
            isMatch = true;
        }
        Pattern signature = Pattern.compile("^s[ée]/?$");
        if (signature.matcher(firstWord).find()) {
            isMatch = true;

        }
        return isMatch;
    }

    private boolean isNameAndTitleOnTheSameLine(List<String> panelistsTitles) {
        return listHasTitleKeyword(panelistsTitles) && !isFirstWordTitleKeyword(panelistsTitles);
    }

    private String[] getWordsInFirstListElement(List<String> panelistsTitles) {
        return panelistsTitles.get(0).split(" ");
    }

    private boolean isFirstWordTitleKeyword(List<String> panelistsTitles) {
        String[] firstListElement = getWordsInFirstListElement(panelistsTitles);
        String firstWord = firstListElement[0];
        return hasPanelistTitleKeyword(firstWord);
    }

    private boolean isJudge(String word) {
        String w = word.toLowerCase();
        return w.contains("camanza") || w.contains("perezida");
    }

    private boolean isCaseWriter(String word) {
        String w = word.toLowerCase();
        return w.contains("anditsi");
    }

    private boolean listHasTitleKeyword(List<String> panelistsNames) {
        boolean orderIsReversed = false;
        for (String word : panelistsNames) {
            if (hasPanelistTitleKeyword(word)) {
                orderIsReversed = true;
            }
        }
        return orderIsReversed;
    }

    private boolean hasPanelistTitleKeyword(String word) {
        return isJudge(word) || isCaseWriter(word);
    }

    private void splitTheLineAndSetNewNamesAndTitles(List<String> panelistsTitles, NamesAndTitles namesAndTitles) {
        String[] firstListElement = getWordsInFirstListElement(panelistsTitles);
        int titleIndex = getPanelistTitleIndex(firstListElement);
        panelistsTitles.set(0, getFirstElementWithoutTitle(firstListElement, titleIndex));
        namesAndTitles.names = new ArrayList<>(panelistsTitles);
        panelistsTitles = getPanelistTitles(firstListElement, titleIndex);
        namesAndTitles.titles = panelistsTitles;
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

    private String getFirstElementWithoutTitle(String[] firstListElement, int titleIndex) {
        return String.join(" ", Arrays.copyOfRange(firstListElement, 0, titleIndex));
    }

    private List<String> getPanelistTitles(String[] firstListElement, int titleIndex) {
        String[] titleArray = getTitlePortionFromFirstElement(firstListElement, titleIndex);
        String title = String.join(" ", titleArray);
        List<String> panelistsTitles = new ArrayList<>();
        panelistsTitles.add(title);
        return panelistsTitles;
    }

    private String[] getTitlePortionFromFirstElement(String[] firstListElement, int titleIndex) {
        return Arrays.copyOfRange(firstListElement, titleIndex, firstListElement.length);
    }


    private void updateOrderIfReversed(NamesAndTitles namesAndTitles) {
        boolean isNameTitleOrderReversed = listHasTitleKeyword(namesAndTitles.names);
        List panelistsNamesCopy = Arrays.asList(namesAndTitles.names.toArray());
        namesAndTitles.names = isNameTitleOrderReversed ? namesAndTitles.titles : namesAndTitles.names;
        namesAndTitles.titles = isNameTitleOrderReversed ? panelistsNamesCopy : namesAndTitles.titles;
    }

    private void addTitlesAndNamesToPanelArray(List<String> panelistsTitles, List<String> panelistsNames) {
        ListIterator<String> namesIterator = panelistsNames.listIterator();
        ListIterator<String> titlesIterator = panelistsTitles.listIterator();
        while (namesIterator.hasNext() && titlesIterator.hasNext()) {
            String title = titlesIterator.next();
            String name = getNameFromNamesIterator(panelistsNames, namesIterator);
            if (nameAndTitleAreAvailable(title, name)) {
                JsonObject panelist = new JsonObject();
                panelist.addNameValuePair(title, name);
                panelArray.putValue(panelist);
            }
        }
    }

    private String getNameFromNamesIterator(List<String> panelistsNames,
                                            ListIterator<String> namesIterator) {
        String name = namesIterator.next();
        if (hasTabInOneFullName(panelistsNames, namesIterator, name))
            name = name + " " + namesIterator.next();
        name = removeTrailingSignature(name);
        return name;
    }

    private String removeTrailingSignature(String name) {
        int signatureLength = "Sé ".length();
        if (name.startsWith("Sé ") || name.startsWith("Se "))
            name = name.substring(signatureLength);
        if (name.endsWith(" Sé") || name.endsWith(" Se"))
            name = name.substring(0, name.length() - signatureLength);
        return name;
    }

    private boolean nameAndTitleAreAvailable(String title, String name) {
        return !name.isEmpty() && !title.isEmpty();
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
}
