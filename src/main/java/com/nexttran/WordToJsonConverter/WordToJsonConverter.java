package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Format;
import com.nexttran.WordToJsonConverter.Constants.Headings;
import com.nexttran.WordToJsonConverter.ResultTypes.CaseSectionResult;
import com.nexttran.WordToJsonConverter.ResultTypes.HeadingParagraphIndex;
import com.nexttran.WordToJsonConverter.ResultTypes.TextParagraphIndex;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


import java.util.*;
import java.util.stream.Collectors;

public class WordToJsonConverter {

    private final List<XWPFParagraph> paragraphs;
    private final Map<Integer, Integer> postParagraphBlanks;
    private final Map<Integer, Boolean> listParagraphs;
    private JsonArray partiesSubsections;
    private final Map<Integer, String> docNumbering;

    WordToJsonConverter(XWPFDocument wordDocument) {
        this.partiesSubsections = new JsonArray();
        this.paragraphs = wordDocument.getParagraphs().stream()
                                                            .filter(para -> !para.getText().trim().isEmpty())
                                                            .collect(Collectors.toList());
        this.postParagraphBlanks = ConverterInitializer.getPostParagraphBlanks(wordDocument);
        this.listParagraphs = ConverterInitializer.getListParagraphs(this.paragraphs);
        this.docNumbering = DocNumbering.getDocNumbering(this.paragraphs);
    }

    CaseSectionResult getCaseTitle() {
        String caseTitle = "";

        int paragraphIndex;
        for (paragraphIndex = 0; paragraphIndex < this.paragraphs.size(); paragraphIndex++) {

            String firstWord = getParagraphFirstWord(paragraphIndex);
            if (firstWord.equals(Headings.URUKIKO)) {
                XWPFParagraph currentParagraph = paragraphs.get(paragraphIndex);
                caseTitle = currentParagraph.getText();
                break;
            }
        }
        return new CaseSectionResult(getJsonObject("title", caseTitle), paragraphIndex + 1);
    }

    CaseSectionResult getCaseParties(int beginningParagraph) {

        HeadingParagraphIndex partiesSectionHeading = findPartiesSectionHeading(beginningParagraph);
        int paragraphIndex = partiesSectionHeading.getParagraphIndex();
        paragraphIndex = getPartiesSubsections(paragraphIndex);
        JsonObject parties = new JsonObject();
        parties.addNameValuePair(partiesSectionHeading.getHeadingName(), this.partiesSubsections);

        return new CaseSectionResult(parties, paragraphIndex);
    }

    private HeadingParagraphIndex findPartiesSectionHeading(int beginningParagraph) {
        String potentialSectionHeading = "";
        int paragraphIndex;
        for (paragraphIndex = beginningParagraph; paragraphIndex < this.paragraphs.size(); paragraphIndex++) {
            potentialSectionHeading = getPartiesHeading(paragraphIndex, beginningParagraph);
            if (!potentialSectionHeading.isEmpty())
                break;
        }
        String partiesSectionHeading = potentialSectionHeading;
        return new HeadingParagraphIndex(partiesSectionHeading, paragraphIndex);
    }

    private String getPartiesHeading(int paragraphIndex, int beginningParagraph) {
        String potentialSectionHeading = "";
        String paragraphText = this.paragraphs.get(paragraphIndex).getText().trim();

        if (isSectionHeading(paragraphIndex))
            potentialSectionHeading = getHeadingFromParagraph(paragraphIndex);

        else if ((paragraphIndex == beginningParagraph) && (Headings.ALL_PARTIES_HEADINGS.contains(paragraphText)))
            potentialSectionHeading = getHeadingFromParagraph(paragraphIndex);

        return Headings.ALL_PARTIES_HEADINGS.contains(potentialSectionHeading)?
                potentialSectionHeading : getCaseSensitiveRunText(paragraphIndex) ;
    }

    private String getHeadingFromParagraph(int paragraphIndex) {
        String currentParagraph = this.paragraphs.get(paragraphIndex).getText();
        String sectionHeading = currentParagraph;

        if (hasColonAndContentOnSameLine(paragraphIndex))
            sectionHeading = currentParagraph.split(Format.COLON)[0];

        return removeStartingOrTrailingColons(sectionHeading);
    }
    private int getPartiesSubsections(int paragraphIndex) {

        for (paragraphIndex++; paragraphIndex < this.paragraphs.size(); paragraphIndex++) {

            XWPFParagraph currentParagraph = this.paragraphs.get(paragraphIndex);
            if (startsSubjectMatterSection(currentParagraph))
                break;
            paragraphIndex = addPartiesSubsection(paragraphIndex);
        }
        return paragraphIndex;
    }

    private int addPartiesSubsection(int paragraphIndex) {
        if (isSectionHeading(paragraphIndex)) {
            if (contentIsOnNextLine(paragraphIndex))
                paragraphIndex = AddSubsectionOnNextLine(paragraphIndex);

            else if (isContentOnSameLine(paragraphIndex))
                paragraphIndex = addPartiesSameLineSubsection(paragraphIndex);
        }
        return paragraphIndex;
    }

    private int addPartiesSameLineSubsection(int paragraphIndex) {
        XWPFParagraph currentParagraph = this.paragraphs.get(paragraphIndex);
        String partyHeading = getHeadingFromParagraph(paragraphIndex);
        String partyContent = currentParagraph.getText().substring(partyHeading.length());
        TextParagraphIndex textParagraphIndex = getMoreParagraphsIfAny(partyContent, paragraphIndex);
        partyContent = textParagraphIndex.getParagraphText();
        addSubsectionContent(partyHeading, partyContent);

        return textParagraphIndex.getParagraphIndex() - 1;
    }

    private int AddSubsectionOnNextLine(int paragraphIndex) {
        String subsectionName = getHeadingFromParagraph(paragraphIndex);
        paragraphIndex++;

        String paragraphText = this.paragraphs.get(paragraphIndex).getText();
        TextParagraphIndex textParagraphIndex = getMoreParagraphsIfAny(
                                                    paragraphText, paragraphIndex);
        paragraphIndex = textParagraphIndex.getParagraphIndex();

        paragraphText = textParagraphIndex.getParagraphText();
        addSubsectionContent(subsectionName, paragraphText);
        return paragraphIndex - 1;
    }

    private void addSubsectionContent(String subsectionName, String subsectionContent) {
        String[] subsectionItems = subsectionContent.split(Format.DOUBLE_BLANK);

        if (subsectionItems.length == 1)
            this.partiesSubsections.addValue(getJsonObject(subsectionName, getJsonArrayWithString(subsectionContent)));
         else {
            JsonArray jsonArray = getJsonArrayFromStringArray(subsectionItems);
            this.partiesSubsections.addValue(getJsonObject(subsectionName, jsonArray));
        }
    }

    private String getParagraphFirstWord(int paragraphIndex) {
        String paragraphText = this.paragraphs.get(paragraphIndex).getText().trim();
        return paragraphText.split(" ")[0];
    }

    private boolean isSectionHeading(int paragraphIndex) {
        XWPFParagraph paragraph = this.paragraphs.get(paragraphIndex);
        List<XWPFRun> paragraphRuns = paragraph.getRuns();
        XWPFRun firstRun = paragraphRuns.get(0);

        return (isFirstRunUnderlined(firstRun) ||
                isFirstRunBoldAndSecondRunsStartsWithColon(paragraphRuns) ||
                isFirstRunCapitalizedAndEndsWithColon(paragraph, firstRun) ||
                isFirstRunHighlyIndentedAndCapitalized(paragraph, firstRun) ||
                isFirstRunBoldAndEndsWithColon(firstRun) ||
                hasColonAndContentOnSameLine(paragraphIndex)
        );
    }

    private TextParagraphIndex getMoreParagraphsIfAny(String paragraphText, int paragraphIndex) {
        paragraphIndex++;
        while (nextParagraphIsNotHeading(paragraphIndex)) {
            String emptyLines = getBlankLines(paragraphIndex-1);
            paragraphText += emptyLines + this.paragraphs.get(paragraphIndex).getText();
            paragraphIndex++;
        }
        return new TextParagraphIndex(paragraphText, paragraphIndex);
    }
    private boolean nextParagraphIsNotHeading(int paragraphIndex) {
        return !(isSectionHeading(paragraphIndex));
    }

    private String getBlankLines(int paragraphIndex) {
        int blanks = this.postParagraphBlanks.get(paragraphIndex);
        if (blanks < 2 && this.listParagraphs.get(paragraphIndex))
            blanks = 2;
        return duplicateLineSeparator(blanks);
    }

    private String duplicateLineSeparator(int numberOfLines) {
        return new String(new char[numberOfLines]).replace("\0", Format.LINE_SEPARATOR);
    }

    private JsonObject getJsonObject(String name, String value){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addNameValuePair(name, removeStartingOrTrailingColons(value));
        return jsonObject;
    }

    private JsonObject getJsonObject(String name, JsonArray value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addNameValuePair(name, value);
        return jsonObject;
    }

    private JsonArray getJsonArrayWithString(String value) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.addValue(removeStartingOrTrailingColons(value).trim());
        return jsonArray;
    }

    private JsonArray getJsonArrayFromStringArray(String[] content) {
        JsonArray jsonArray = new JsonArray();
        for (String subContent : content)
            jsonArray.addValue(removeStartingOrTrailingColons(subContent).trim());
        return jsonArray;
    }

    private boolean contentIsOnNextLine(int paragraphIndex) {
        return this.paragraphs.get(paragraphIndex).getText().endsWith(Format.COLON);
    }

    private boolean isContentOnSameLine(int paragraphIndex) {
        String paragraphText = this.paragraphs.get(paragraphIndex).getText();
        String[] textParts = paragraphText.split(Format.COLON);
        boolean onSameLine = false;
        if (textParts.length == 2 && !textParts[1].isEmpty())
            onSameLine = true;
        return onSameLine;
    }

    private boolean startsSubjectMatterSection(XWPFParagraph currentParagraph) {
        boolean subjectMatterStart = false;
        for (String heading : Headings.ALL_SUBJECT_MATTER_HEADINGS) {
            if (currentParagraph.getText().toUpperCase().startsWith(heading))
                subjectMatterStart = true;
        }
        return subjectMatterStart;
    }

    private boolean isFirstRunBoldAndSecondRunsStartsWithColon(List<XWPFRun> paragraphRuns) {
        int numberOfRuns = paragraphRuns.size();
        String secondRunText = "";
        if (numberOfRuns > 1)
            secondRunText = paragraphRuns.get(1).text().trim();
        XWPFRun firstRun = paragraphRuns.get(0);
        return firstRun.isBold() && secondRunText.startsWith(Format.COLON);
    }

    private boolean isFirstRunHighlyIndentedAndCapitalized(XWPFParagraph paragraph, XWPFRun firstRun) {
        return (paragraph.getIndentationLeft() == -1) && isTextCapitalized(firstRun.text());
    }

    private boolean isFirstRunCapitalizedAndEndsWithColon(XWPFParagraph paragraph, XWPFRun firstRun) {
        return isTextCapitalized(firstRun.text()) && paragraph.getText().endsWith(Format.COLON);
    }

    private boolean isFirstRunBoldAndEndsWithColon(XWPFRun firstRun) {
        return firstRun.isBold() && firstRun.text().endsWith(Format.COLON);
    }

    private boolean isFirstRunUnderlined(XWPFRun firstRun) {
        int underlinePattern = firstRun.getUnderline().getValue();
        return underlinePattern == 1 || underlinePattern == 4;
    }

    private boolean hasColonAndContentOnSameLine(int paragraphIndex) {
        String paragraphText = this.paragraphs.get(paragraphIndex).getText();
        String[] textParts = paragraphText.split(Format.COLON);
        int numberOfParts = textParts.length;
        String firstPart = textParts[0];

        boolean colonAndContent = false;
        if (numberOfParts == 2 && firstPart.length() > 3 && isTextCapitalized(firstPart))
            colonAndContent = true;
        return colonAndContent;
    }

    private boolean isTextCapitalized(String text) {
        return text.equals(text.toUpperCase());
    }

    private String getCaseSensitiveRunText(int paragraphIndex) {
        String runText = "";
        List<XWPFRun> runs = this.paragraphs.get(paragraphIndex).getRuns();
        for (XWPFRun run : runs) {
            if (isCaseSensitive(run.text()))
                runText = run.text();
        }
        return removeStartingOrTrailingColons(runText).trim();
    }
    private boolean isCaseSensitive(String text) {
        return !(text.toUpperCase().equals(text.toLowerCase()));
    }

    private String removeStartingOrTrailingColons(String text) {
        text = text.trim();
        String modifiedText = text;
        if (text.endsWith(Format.COLON))
            modifiedText = text.substring(0, text.length() - 1);

        if (modifiedText.startsWith(Format.COLON))
            modifiedText = modifiedText.substring(1).trim();

        return modifiedText.trim();
    }

}
