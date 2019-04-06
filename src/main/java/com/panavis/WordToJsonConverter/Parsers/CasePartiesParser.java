package com.panavis.WordToJsonConverter.Parsers;

import static com.panavis.WordToJsonConverter.Constants.Headings.*;
import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.ResultTypes.*;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.JsonCreator;
import com.panavis.WordToJsonConverter.Wrappers.*;

public class CasePartiesParser implements ICaseParties {

    private WordParagraph wordParagraph;
    private SectionParties section;
    private JsonArray partiesSubsections;
    private boolean reachedSubjectMatterSection;
    private boolean lacksHeadingAfterProsecutorSection;
    private int subsectionStart;

    public CasePartiesParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.section = new SectionParties(wordParagraph);
        this.partiesSubsections = new JsonArray();
        this.reachedSubjectMatterSection = false;
        this.lacksHeadingAfterProsecutorSection = false;
    }

    public SectionResult parse(int startParagraph) {
        HeadingParagraphIndex partiesSectionHeading = findPartiesSectionHeading(startParagraph);
        int paragraphIndex = partiesSectionHeading.getParagraphIndex();
        paragraphIndex = getPartiesSubsections(paragraphIndex + 1);
        JsonObject parties = new JsonObject();
        parties.addNameValuePair(partiesSectionHeading.getHeadingName(), this.partiesSubsections);
        return new SectionResult(parties, paragraphIndex);
    }

    private HeadingParagraphIndex findPartiesSectionHeading(int startParagraph) {
        HeadingParagraphIndex headingAndIndex = findFirstHeading(startParagraph);
        String partiesHeading = headingAndIndex.getHeadingName();
        int paragraphIndex = headingAndIndex.getParagraphIndex();
        if (firstHeadingIsSubsection(partiesHeading)) {
            partiesHeading = HABURANA;
            paragraphIndex--;
        }
        return new HeadingParagraphIndex(partiesHeading, paragraphIndex);
    }

    private HeadingParagraphIndex findFirstHeading(int startParagraph) {
        String firstHeading = "";
        int paragraphIndex;
        paragraphIndex = startParagraph;
        while (paragraphIndex < this.wordParagraph.numberOfParagraphs()) {
            firstHeading = getFirstHeading(firstHeading, paragraphIndex);
            if (!firstHeading.isEmpty())
                break;
            paragraphIndex++;
        }
        return new HeadingParagraphIndex(firstHeading, paragraphIndex);
    }

    private String getFirstHeading(String firstHeading, int paragraphIndex) {
        if (this.wordParagraph.isSectionHeading(paragraphIndex))
            firstHeading = this.wordParagraph.getHeadingFromParagraph(paragraphIndex);

        firstHeading = PARTIES_HEADINGS.contains(firstHeading) ?
                firstHeading : this.wordParagraph.getCaseSensitiveRunText(paragraphIndex);
        return firstHeading;
    }

    private boolean firstHeadingIsSubsection(String partiesHeading) {
        return !PARTIES_HEADINGS.contains(partiesHeading);
    }

    private int getPartiesSubsections(int startParagraph) {
        subsectionStart = startParagraph;
        while (!reachedSubjectMatterSection && startParagraph < wordParagraph.numberOfParagraphs()) {
            if (section.startsSubjectMatterSection(subsectionStart)) {
                reachedSubjectMatterSection = true;
                break;
            }
            addPartiesSubsection(subsectionStart);
        }
        return subsectionStart;
    }

    private void addPartiesSubsection(int paragraphIndex) {
        String paragraphText = wordParagraph.getParagraphText(paragraphIndex);
         if (this.wordParagraph.isSectionHeading(paragraphIndex)) {
             parseAndAddNormalSubsection(paragraphIndex);
         } else if (startsProsecutorSubsection(paragraphIndex)) {
            parseAndAddCriminalCaseProsecutorSubsection(paragraphIndex, paragraphText);
        } else if (lacksHeadingAfterProsecutorSection) {
             parseAndAddPostProsecutorSubsectionWithoutHeading(paragraphIndex);
         }
         else {
             // TODO Unable To Parse Case
             updateSubsectionStart(paragraphIndex + 1);
         }
    }

    private void parseAndAddNormalSubsection(int startParagraph) {
        String inlineParagraph = wordParagraph.getInlineHeadingFirstParagraph(startParagraph);
        section.setStartingParagraph(startParagraph)
                .setInlineParagraph(inlineParagraph)
                .parse();
        String subsectionName = wordParagraph.getHeadingFromParagraph(startParagraph);
        addSubsectionContent(subsectionName, section.getBody());
        updateSubsectionStart(section.getLastParagraph());
    }

    private void addSubsectionContent(String subsectionName, JsonArray subsectionBody) {
        this.partiesSubsections.putValue(JsonCreator.getJsonObject(subsectionName, subsectionBody));
    }

    private void parseAndAddCriminalCaseProsecutorSubsection(int startParagraph, String inlineParagraph) {
        String nextParagraphText = getNextParagraphText(startParagraph);
        if (nextParagraphText.toLowerCase().equals("na")) {
            JsonArray sectionBody = new JsonArray();
            sectionBody.putValue(wordParagraph.getParagraphText(startParagraph));
            addSubsectionContent(UBUSHINJACYAHA, sectionBody);
            updateSubsectionStart(startParagraph + 2);
            updateAvailabilityOfHeadingAfterProsecutorSection(subsectionStart);
        } else {
            section.setStartingParagraph(startParagraph)
                    .setInlineParagraph(inlineParagraph)
                    .parse();
            addSubsectionContent(UBUSHINJACYAHA, section.getBody());
            updateSubsectionStart(section.getLastParagraph());
        }
    }

    private String getNextParagraphText(int paragraphIndex) {
        String text = "";
        if (wordParagraph.paragraphExists(paragraphIndex + 1))
            text = wordParagraph.getParagraphText(paragraphIndex + 1);
        return text;
    }

    private void updateAvailabilityOfHeadingAfterProsecutorSection(int startParagraph) {
        lacksHeadingAfterProsecutorSection = wordParagraph.paragraphExists(startParagraph) &&
                                            !wordParagraph.isSectionHeading(startParagraph);
    }

    private void parseAndAddPostProsecutorSubsectionWithoutHeading(int paragraphIndex) {
        int logicalHeadingIndex = paragraphIndex - 1;
        section.setStartingParagraph(logicalHeadingIndex)
                .parse();
        addSubsectionContent(USHINJWA.toLowerCase(), section.getBody());
        updateSubsectionStart(section.getLastParagraph());
    }

    private void updateSubsectionStart(int paragraphIndex) {
        subsectionStart = paragraphIndex;
    }

    private boolean startsProsecutorSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex);
        return text.toLowerCase().contains(UBUSHINJACYAHA) &&
                previousParagraphIsNotSubsectionHeading(paragraphIndex);
    }

    private boolean previousParagraphIsNotSubsectionHeading(int paragraphIndex) {
        if (paragraphIndex - 1 < 0) return true;
        String pP = wordParagraph.getHeadingFromParagraph(paragraphIndex - 1).toUpperCase();
        return !wordParagraph.isSectionHeading(paragraphIndex - 1) ||
                PARTIES_HEADINGS.contains(pP);
    }
}
