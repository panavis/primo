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
        String paragraphText = this.wordParagraph.getParagraphText(paragraphIndex);
         if (this.wordParagraph.isSectionHeading(paragraphIndex)) {
             paragraphIndex = parseAndAddNormalSubsection(paragraphIndex);
         } else if (startsProsecutorSubsection(paragraphIndex)) {
            paragraphIndex = addCriminalCaseProsecutor(paragraphIndex, paragraphText);
        }
        return paragraphIndex;
    }

    private int parseAndAddNormalSubsection(int startParagraph) {
        String subsectionName = wordParagraph.getHeadingFromParagraph(startParagraph);
        String inlineBody = wordParagraph.getInlineHeadingFirstParagraph(startParagraph);
        inlineBody = inlineBody.length() == 0 ?
                "" : inlineBody + wordParagraph.getBlankLinesAfterParagraph(startParagraph);

        TextParagraphIndex remainingAndIndex = getRemainingSubsectionBody(startParagraph);
        addSubsectionContent(subsectionName, inlineBody.concat(remainingAndIndex.getSubsectionParagraphs()).trim());
        return remainingAndIndex.getParagraphIndex() - 1;
    }

    private TextParagraphIndex getRemainingSubsectionBody(int startParagraph) {
        StringBuilder remainingBody = new StringBuilder();
        int paragraphIndex = startParagraph + 1;
        while(isInTheCurrentSubsection(paragraphIndex)) {
            remainingBody.append(wordParagraph.getParagraphText(paragraphIndex))
                    .append(wordParagraph.getBlankLinesAfterParagraph(paragraphIndex));
            paragraphIndex++;
        }
        return new TextParagraphIndex(remainingBody.toString(), paragraphIndex);
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

    private int addCriminalCaseProsecutor(int startParagraph, String firstParagraph) {
        firstParagraph = firstParagraph.length() == 0 ?
                "" : firstParagraph + wordParagraph.getBlankLinesAfterParagraph(startParagraph);

        TextParagraphIndex remainingAndIndex = getRemainingSubsectionBody(startParagraph);
        addSubsectionContent(Keywords.UBUSHINJACYAHA, firstParagraph.concat(remainingAndIndex.getSubsectionParagraphs()).trim());

        return remainingAndIndex.getParagraphIndex() - 1;
    }

    private boolean startsProsecutorSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex);
        return text.toLowerCase().contains(Keywords.UBUSHINJACYAHA) &&
                previousParagraphIsNotSubsectionHeading(paragraphIndex);
    }

    private boolean previousParagraphIsNotSubsectionHeading(int paragraphIndex) {
        if (paragraphIndex - 1 < 0) return true;
        String pP = wordParagraph.getHeadingFromParagraph(paragraphIndex - 1).toUpperCase();
        return !wordParagraph.isSectionHeading(paragraphIndex - 1) ||
                Headings.PARTIES_HEADINGS.contains(pP);
    }
}
