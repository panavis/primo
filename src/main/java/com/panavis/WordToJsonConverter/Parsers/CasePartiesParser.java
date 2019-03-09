package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.ResultTypes.HeadingParagraphIndex;
import com.panavis.WordToJsonConverter.ResultTypes.TextParagraphIndex;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.Arrays;

import static com.panavis.WordToJsonConverter.Utils.JsonCreator.*;

public class CasePartiesParser implements ICaseParties {

    private WordParagraph wordParagraph;
    private JsonArray partiesSubsections;

    public CasePartiesParser(WordParagraph wordParagraph) {
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
        // TODO Refactor this function for everybody's sake
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
        // TODO Convert to subsectionStart and numberOfSections
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
         } else if (startsProsecutorSubsection(paragraphIndex)) {
            paragraphIndex = addCriminalCaseProsecutor(paragraphIndex, paragraphText);
        }
        return paragraphIndex;
    }

//    private int parseAndAddNormalSubsection(int paragraphIndex) {
//        if (this.wordParagraph.isContentOnNextLine(paragraphIndex))
//            paragraphIndex = AddSubsectionOnNextLine(paragraphIndex);
//
//        else if (this.wordParagraph.isContentOnSameLine(paragraphIndex))
//            paragraphIndex = addSubsectionOnSameLine(paragraphIndex);
//        return paragraphIndex;
//    }

    private int parseAndAddNormalSubsection(int startParagraph) {
        String partyHeading = wordParagraph.getHeadingFromParagraph(startParagraph);
        String firstParagraph = getFirstParagraphOfBody(startParagraph);
        StringBuilder body = new StringBuilder(firstParagraph);
        body.append(body.length() == 0 ? "" : wordParagraph.getBlankLinesAfterParagraph(startParagraph));
        int paragraphIndex = startParagraph + 1;
        while(isInTheCurrentSubsection(paragraphIndex)) {
            body.append(wordParagraph.getParagraphWithNumbering(paragraphIndex))
                    .append(wordParagraph.getBlankLinesAfterParagraph(paragraphIndex));
            paragraphIndex++;
        }
        addSubsectionContent(partyHeading, body.toString().trim());
        return paragraphIndex - 1;
    }

    private String getFirstParagraphOfBody(int startParagraph) {
        String[] bodyArray = wordParagraph.getParagraph(startParagraph).getText().split(":");
        String[] bodyNoHeading = Arrays.copyOfRange(bodyArray, 1, bodyArray.length);
        return String.join(" ", bodyNoHeading).trim();
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
        return !(wordParagraph.isSectionHeading(paragraphIndex)) &&
                !startsProsecutorSubsection(paragraphIndex);
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

    private boolean startsProsecutorSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraph(paragraphIndex).getText();
        return text.toLowerCase().contains(Keywords.UBUSHINJACYAHA) &&
                previousParagraphIsNotSubsectionHeading(paragraphIndex);
    }

    private boolean previousParagraphIsNotSubsectionHeading(int paragraphIndex) {
        if (paragraphIndex - 1 < 0) return true;
        String previousParagraph = wordParagraph.getHeadingFromParagraph(paragraphIndex - 1).toUpperCase();
        return !wordParagraph.isSectionHeading(paragraphIndex - 1) ||
                Headings.PARTIES_HEADINGS.contains(previousParagraph);
    }
}
