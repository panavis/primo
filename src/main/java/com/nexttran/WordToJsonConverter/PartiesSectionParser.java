package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Format;
import com.nexttran.WordToJsonConverter.Constants.Headings;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import com.nexttran.WordToJsonConverter.ResultTypes.HeadingParagraphIndex;
import com.nexttran.WordToJsonConverter.ResultTypes.TextParagraphIndex;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import static com.nexttran.WordToJsonConverter.JsonCreator.*;
import static com.nexttran.WordToJsonConverter.StringFormatting.removeStartingOrTrailingColons;

class PartiesSectionParser {

    private WordParagraph wordParagraph;
    private JsonArray partiesSubsections;

    PartiesSectionParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.partiesSubsections = new JsonArray();
    }

    SectionResult parseCaseParties(int beginningParagraph) {
        HeadingParagraphIndex partiesSectionHeading = findPartiesSectionHeading(beginningParagraph);
        int paragraphIndex = partiesSectionHeading.getParagraphIndex();
        paragraphIndex = getPartiesSubsections(paragraphIndex);
        JsonObject parties = new JsonObject();
        parties.addNameValuePair(partiesSectionHeading.getHeadingName(), this.partiesSubsections);
        return new SectionResult(parties, paragraphIndex);
    }

    private HeadingParagraphIndex findPartiesSectionHeading(int beginningParagraph) {
        String potentialSectionHeading = "";
        int paragraphIndex;
        paragraphIndex = beginningParagraph;
        while (paragraphIndex < this.wordParagraph.numberOfParagraphs()) {
            if (this.wordParagraph.isSectionHeading(paragraphIndex)) {
                potentialSectionHeading = getHeadingFromParagraph(paragraphIndex);
            }
            potentialSectionHeading = Headings.PARTIES_HEADINGS.contains(potentialSectionHeading) ?
                    potentialSectionHeading : this.wordParagraph.getCaseSensitiveRunText(paragraphIndex);

            if (!potentialSectionHeading.isEmpty())
                break;
            paragraphIndex++;
        }
        String partiesHeading = Headings.PARTIES_HEADINGS.contains(potentialSectionHeading) ?
                potentialSectionHeading : Headings.HABURANA;
        return new HeadingParagraphIndex(partiesHeading, paragraphIndex);
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

    private String getHeadingFromParagraph(int paragraphIndex) {
        String currentParagraph = this.wordParagraph.getParagraph(paragraphIndex).getText();
        String sectionHeading = currentParagraph;

        if (this.wordParagraph.hasColonAndContentOnSameLine(paragraphIndex))
            sectionHeading = currentParagraph.split(Format.COLON)[0];
        return removeStartingOrTrailingColons(sectionHeading);
    }
    private int getPartiesSubsections(int paragraphIndex) {
        for (paragraphIndex++; paragraphIndex < this.wordParagraph.numberOfParagraphs(); paragraphIndex++) {
            if (this.wordParagraph.startsSubjectMatterSection(paragraphIndex))
                break;
            paragraphIndex = addPartiesSubsection(paragraphIndex);
        }
        return paragraphIndex;
    }

    private int addPartiesSubsection(int paragraphIndex) {
        if (this.wordParagraph.isSectionHeading(paragraphIndex)) {
            if (this.wordParagraph.contentIsOnNextLine(paragraphIndex))
                paragraphIndex = AddSubsectionOnNextLine(paragraphIndex);

            else if (this.wordParagraph.isContentOnSameLine(paragraphIndex))
                paragraphIndex = addPartiesSameLineSubsection(paragraphIndex);
        }
        return paragraphIndex;
    }

    private int addPartiesSameLineSubsection(int paragraphIndex) {
        XWPFParagraph currentParagraph = this.wordParagraph.getParagraph(paragraphIndex);
        String partyHeading = getHeadingFromParagraph(paragraphIndex);
        String firstParagraph = currentParagraph.getText().substring(partyHeading.length());
        TextParagraphIndex textParagraphIndex = this.wordParagraph.getMoreParagraphsIfAny(
                                                firstParagraph, paragraphIndex);
        String subsectionParagraphs = textParagraphIndex.getSubsectionParagraphs();
        addSubsectionContent(partyHeading, subsectionParagraphs);
        return textParagraphIndex.getParagraphIndex() - 1;
    }

    private int AddSubsectionOnNextLine(int paragraphIndex) {
        String subsectionName = getHeadingFromParagraph(paragraphIndex);
        paragraphIndex++;
        String firstParagraph = this.wordParagraph.getParagraph(paragraphIndex).getText();
        TextParagraphIndex textParagraphIndex = this.wordParagraph.getMoreParagraphsIfAny(
                                            firstParagraph, paragraphIndex);
        paragraphIndex = textParagraphIndex.getParagraphIndex();
        String subsectionParagraphs = textParagraphIndex.getSubsectionParagraphs();
        addSubsectionContent(subsectionName, subsectionParagraphs);
        return paragraphIndex - 1;
    }
}
