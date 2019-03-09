package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.ResultTypes.HeadingParagraphIndex;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

import static com.panavis.WordToJsonConverter.Utils.JsonCreator.*;

public class CasePartiesParser implements ICaseParties {

    private WordParagraph wordParagraph;
    private ISection section;
    private JsonArray partiesSubsections;
    private boolean reachedSubjectMatterSection;
    private int subsectionStart;

    public CasePartiesParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.section = new SectionParties(wordParagraph);
        this.partiesSubsections = new JsonArray();
        this.reachedSubjectMatterSection = false;
    }

    public SectionResult parse(int beginningParagraph) {
        HeadingParagraphIndex partiesSectionHeading = findPartiesSectionHeading(beginningParagraph);
        int paragraphIndex = partiesSectionHeading.getParagraphIndex();
        paragraphIndex = getPartiesSubsections(paragraphIndex + 1);
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

    private int getPartiesSubsections(int startParagraph) {
        subsectionStart = startParagraph;
        while (!reachedSubjectMatterSection) {
            if (this.wordParagraph.startsSubjectMatterSection(subsectionStart)) {
                reachedSubjectMatterSection = true;
                break;
            }
            addPartiesSubsection(subsectionStart);
        }
        return subsectionStart;
    }

    private void addPartiesSubsection(int paragraphIndex) {
        String paragraphText = this.wordParagraph.getParagraphText(paragraphIndex);
         if (this.wordParagraph.isSectionHeading(paragraphIndex)) {
             parseAndAddNormalSubsection(paragraphIndex);
         } else if (startsProsecutorSubsection(paragraphIndex)) {
            parseAndAddCriminalCaseProsecutorSubsection(paragraphIndex, paragraphText);
        }
    }

    private void parseAndAddNormalSubsection(int startParagraph) {
        String subsectionName = wordParagraph.getHeadingFromParagraph(startParagraph);
        Subsection subsection = Subsection.getSubsection(section, wordParagraph, startParagraph);
        addSubsectionContent(subsectionName, subsection.getBody());
        updateSubsectionStart(subsection.getLastParagraph());
    }

    private void addSubsectionContent(String subsectionName, String subsectionContent) {
        String[] subsectionItems = subsectionContent.split(Format.DOUBLE_BLANK);
        JsonArray jsonArray = getJsonArrayFromStringArray(subsectionItems);
        this.partiesSubsections.putValue(getJsonObject(subsectionName, jsonArray));
    }

    private void parseAndAddCriminalCaseProsecutorSubsection(int startParagraph, String inlineFirstParagraph) {
        Subsection subsection = new Subsection(section, wordParagraph, startParagraph, inlineFirstParagraph);
        subsection.parse();
        addSubsectionContent(Keywords.UBUSHINJACYAHA, subsection.getBody());
        updateSubsectionStart(subsection.getLastParagraph());
    }

    private void updateSubsectionStart(int paragraphIndex) {
        subsectionStart = paragraphIndex;
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
