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
        for (paragraphIndex = beginningParagraph; paragraphIndex < this.wordParagraph.paragraphs.size(); paragraphIndex++) {
            potentialSectionHeading = getPartiesHeading(paragraphIndex, beginningParagraph);
            if (!potentialSectionHeading.isEmpty())
                break;
        }
        String partiesSectionHeading = potentialSectionHeading;
        return new HeadingParagraphIndex(partiesSectionHeading, paragraphIndex);
    }

    private String getPartiesHeading(int paragraphIndex, int beginningParagraph) {
        String potentialSectionHeading = "";
        String paragraphText = this.wordParagraph.paragraphs.get(paragraphIndex).getText().trim();

        if (this.wordParagraph.isSectionHeading(paragraphIndex))
            potentialSectionHeading = getHeadingFromParagraph(paragraphIndex);

        else if ((paragraphIndex == beginningParagraph) && (Headings.ALL_PARTIES_HEADINGS.contains(paragraphText)))
            potentialSectionHeading = getHeadingFromParagraph(paragraphIndex);

        return Headings.ALL_PARTIES_HEADINGS.contains(potentialSectionHeading)?
                potentialSectionHeading : this.wordParagraph.getCaseSensitiveRunText(paragraphIndex) ;
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

    private String getHeadingFromParagraph(int paragraphIndex) {
        String currentParagraph = this.wordParagraph.paragraphs.get(paragraphIndex).getText();
        String sectionHeading = currentParagraph;

        if (this.wordParagraph.hasColonAndContentOnSameLine(paragraphIndex))
            sectionHeading = currentParagraph.split(Format.COLON)[0];

        return removeStartingOrTrailingColons(sectionHeading);
    }
    private int getPartiesSubsections(int paragraphIndex) {

        for (paragraphIndex++; paragraphIndex < this.wordParagraph.paragraphs.size(); paragraphIndex++) {
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
        XWPFParagraph currentParagraph = this.wordParagraph.paragraphs.get(paragraphIndex);
        String partyHeading = getHeadingFromParagraph(paragraphIndex);
        String partyContent = currentParagraph.getText().substring(partyHeading.length());
        TextParagraphIndex textParagraphIndex = this.wordParagraph.getMoreParagraphsIfAny(partyContent, paragraphIndex);
        partyContent = textParagraphIndex.getParagraphText();
        addSubsectionContent(partyHeading, partyContent);

        return textParagraphIndex.getParagraphIndex() - 1;
    }

    private int AddSubsectionOnNextLine(int paragraphIndex) {
        String subsectionName = getHeadingFromParagraph(paragraphIndex);
        paragraphIndex++;

        String paragraphText = this.wordParagraph.paragraphs.get(paragraphIndex).getText();
        TextParagraphIndex textParagraphIndex = this.wordParagraph.getMoreParagraphsIfAny(
                paragraphText, paragraphIndex);
        paragraphIndex = textParagraphIndex.getParagraphIndex();

        paragraphText = textParagraphIndex.getParagraphText();
        addSubsectionContent(subsectionName, paragraphText);
        return paragraphIndex - 1;
    }
}
