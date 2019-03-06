package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Format;
import com.nexttran.WordToJsonConverter.Constants.Headings;
import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import com.nexttran.WordToJsonConverter.ResultTypes.HeadingParagraphIndex;
import com.nexttran.WordToJsonConverter.ResultTypes.TextParagraphIndex;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import static com.nexttran.WordToJsonConverter.JsonCreator.*;

class CasePartiesParser implements ICaseParties {

    private WordParagraph wordParagraph;
    private JsonArray partiesSubsections;

    CasePartiesParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.partiesSubsections = new JsonArray();
    }

    public SectionResult parse(int beginningParagraph) {
        HeadingParagraphIndex partiesSectionHeading = findPartiesSectionHeading(beginningParagraph);
        int paragraphIndex = partiesSectionHeading.getParagraphIndex();
        paragraphIndex = getPartiesSubsections(paragraphIndex);
        JsonObject parties = new JsonObject();
        parties.addNameValuePair(partiesSectionHeading.getHeadingName(), this.partiesSubsections);
        return new SectionResult(parties, paragraphIndex);
    }

    private HeadingParagraphIndex findPartiesSectionHeading(int beginningParagraph) {
        String firstHeading = "";
        int paragraphIndex;
        paragraphIndex = beginningParagraph;
        while (paragraphIndex < this.wordParagraph.numberOfParagraphs()) {
            firstHeading = getFirstHeading(firstHeading, paragraphIndex);
            if (!firstHeading.isEmpty())
                break;
            paragraphIndex++;
        }
        String partiesHeading = firstHeading;
        if (!Headings.PARTIES_HEADINGS.contains(firstHeading)) {
            partiesHeading = Headings.HABURANA;
            paragraphIndex--;
        }
        return new HeadingParagraphIndex(partiesHeading, paragraphIndex);
    }

    private String getFirstHeading(String firstHeading, int paragraphIndex) {
        if (this.wordParagraph.isSectionHeading(paragraphIndex))
            firstHeading = this.wordParagraph.getHeadingFromParagraph(paragraphIndex);

        firstHeading = Headings.PARTIES_HEADINGS.contains(firstHeading) ?
                firstHeading : this.wordParagraph.getCaseSensitiveRunText(paragraphIndex);
        return firstHeading;
    }

    private int getPartiesSubsections(int paragraphIndex) {
        for (paragraphIndex++;
             paragraphIndex < this.wordParagraph.numberOfParagraphs();
             paragraphIndex++) {
            if (this.wordParagraph.startsSubjectMatterSection(paragraphIndex))
                break;
            paragraphIndex = addPartiesSubsection(paragraphIndex);
        }
        return paragraphIndex;
    }

    private int addPartiesSubsection(int paragraphIndex) {
        String paragraphText = this.wordParagraph.getParagraph(paragraphIndex).getText();
         if (this.wordParagraph.isSectionHeading(paragraphIndex)) {
             paragraphIndex = parseAndAddNormalSubsection(paragraphIndex);
         } else if (isProsecutor(paragraphText)) {
            paragraphIndex = addCriminalCaseProsecutor(paragraphIndex, paragraphText);
        }
        return paragraphIndex;
    }

    private int parseAndAddNormalSubsection(int paragraphIndex) {
        if (this.wordParagraph.isContentOnNextLine(paragraphIndex))
            paragraphIndex = AddSubsectionOnNextLine(paragraphIndex);

        else if (this.wordParagraph.isContentOnSameLine(paragraphIndex))
            paragraphIndex = addPartiesSameLineSubsection(paragraphIndex);
        return paragraphIndex;
    }

    private int AddSubsectionOnNextLine(int paragraphIndex) {
        String subsectionName = wordParagraph.getHeadingFromParagraph(paragraphIndex);
        paragraphIndex++;
        String firstParagraph = wordParagraph.getParagraphWithNumbering(paragraphIndex);
        TextParagraphIndex textParagraphIndex = getMoreParagraphsIfAny(
                firstParagraph, paragraphIndex);
        String subsectionParagraphs = textParagraphIndex.getSubsectionParagraphs();
        addSubsectionContent(subsectionName, subsectionParagraphs);
        return textParagraphIndex.getParagraphIndex() - 1;
    }

    private int addPartiesSameLineSubsection(int paragraphIndex) {
        XWPFParagraph currentParagraph = wordParagraph.getParagraph(paragraphIndex);
        String partyHeading = wordParagraph.getHeadingFromParagraph(paragraphIndex);
        String firstParagraph = currentParagraph.getText().substring(partyHeading.length());
        TextParagraphIndex textParagraphIndex = getMoreParagraphsIfAny(
                                                firstParagraph, paragraphIndex);
        String subsectionParagraphs = textParagraphIndex.getSubsectionParagraphs();
        addSubsectionContent(partyHeading, subsectionParagraphs);
        return textParagraphIndex.getParagraphIndex() - 1;
    }

    private TextParagraphIndex getMoreParagraphsIfAny(String firstParagraph, int paragraphIndex) {
        StringBuilder subsectionParagraphs= new StringBuilder();
        subsectionParagraphs.append(firstParagraph);
        paragraphIndex++;
        while (isInTheCurrentSubsection(paragraphIndex)) {
            String emptyLines = wordParagraph.getBlankLinesAfterParagraph(paragraphIndex-1);
            String numbered = wordParagraph.getParagraphWithNumbering(paragraphIndex);
            String paragraphText = emptyLines + numbered;
            subsectionParagraphs.append(paragraphText);
            paragraphIndex++;
        }
        return new TextParagraphIndex(subsectionParagraphs.toString(), paragraphIndex);
    }

    private boolean isInTheCurrentSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraph(paragraphIndex).getText();
        return !(wordParagraph.isSectionHeading(paragraphIndex)) && !CasePartiesParser.isProsecutor(text);
    }

    private void addSubsectionContent(String subsectionName, String subsectionContent) {
        String[] subsectionItems = subsectionContent.split(Format.DOUBLE_BLANK);

        if (subsectionItems.length == 1)
            this.partiesSubsections.putValue(getJsonObject(subsectionName, getJsonArrayWithString(subsectionContent)));
        else {
            JsonArray jsonArray = getJsonArrayFromStringArray(subsectionItems);
            this.partiesSubsections.putValue(getJsonObject(subsectionName, jsonArray));
        }
    }

    private int addCriminalCaseProsecutor(int paragraphIndex, String firstParagraph) {
        TextParagraphIndex textParagraphIndex = getMoreParagraphsIfAny(
                firstParagraph, paragraphIndex);
        String sectionContent = textParagraphIndex.getSubsectionParagraphs();
        JsonArray prosecutorContent = new JsonArray();
        prosecutorContent.putValue(sectionContent);
        this.partiesSubsections.putValue(getJsonObject(Keywords.UBUSHINJACYAHA, prosecutorContent));
        return textParagraphIndex.getParagraphIndex() - 1;
    }

    private static boolean isProsecutor(String text) {
        return text.toLowerCase().contains(Keywords.UBUSHINJACYAHA);
    }
}