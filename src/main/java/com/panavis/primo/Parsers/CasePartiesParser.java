package com.panavis.primo.Parsers;

import com.panavis.primo.ResultTypes.HeadingParagraphIndex;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.JsonCreator;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import static com.panavis.primo.Constants.Headings.*;
import static com.panavis.primo.Constants.Keywords.DEFAULT_PARTY_SUBHEADING;
import static com.panavis.primo.Constants.Keywords.UBUSHINJACYAHA;

public class CasePartiesParser implements ICaseSectionParser {

    private CaseParagraph caseParagraph;
    private SectionParties section;
    private JsonArray partiesSubsections;
    private boolean reachedSubjectMatterSection;
    private int subsectionStart;
    private static final String AND_CONJUNCTION = "na";

    public CasePartiesParser(CaseParagraph caseParagraph) {
        this.caseParagraph = caseParagraph;
        this.section = new SectionParties(caseParagraph);
        this.partiesSubsections = new JsonArray();
        this.reachedSubjectMatterSection = false;
    }

    public SectionResult parse(int startParagraph) {
        HeadingParagraphIndex partiesSectionHeading = findPartiesSectionHeading(startParagraph);
        int paragraphIndex = partiesSectionHeading.getParagraphIndex();
        paragraphIndex = parsePartiesSubsections(paragraphIndex + 1);
        sanitizePartiesSubsections();
        JsonObject parties = new JsonObject();
        String sectionHeading = partiesSectionHeading.getHeadingName().toUpperCase();
        parties.addNameValuePair(sectionHeading, this.partiesSubsections);
        return new SectionResult(parties, paragraphIndex);
    }

    private void sanitizePartiesSubsections() {
        for (int i = 0; i < this.partiesSubsections.getSize(); i++) {
            JsonObject subsection = this.partiesSubsections.getJsonByIndex(i);
            String subsectionHeading = (String) subsection.getKeys().toArray()[0];
            JsonArray subsectionContent = subsection.getArrayByKey(subsectionHeading);
            String firstParagraph = subsectionContent.getSize() > 0 ? subsectionContent.getStringByIndex(0) : "";

            boolean isProsecutor = hasProsecutor(subsectionHeading);
            boolean hasEmptyContent = firstParagraph.equals(StringFormatting.EMPTY_STRING);

            if (isProsecutor && hasEmptyContent) {
                JsonArray defaultContent = new JsonArray();
                defaultContent.putValue(UBUSHINJACYAHA.toUpperCase());
                JsonObject newSubsection = new JsonObject();
                newSubsection.addNameValuePair(DEFAULT_PARTY_SUBHEADING, defaultContent);
                this.partiesSubsections.putValueAtIndex(i, newSubsection);
            }
        }
    }

    private HeadingParagraphIndex findPartiesSectionHeading(int startParagraph) {
        HeadingParagraphIndex headingAndIndex = findFirstHeading(startParagraph);
        String partiesHeading = headingAndIndex.getHeadingName();
        int paragraphIndex = headingAndIndex.getParagraphIndex();
        if (!isPartySectionHeading(partiesHeading)) {
            partiesHeading = HABURANA;
            paragraphIndex--;
        }
        return new HeadingParagraphIndex(partiesHeading, paragraphIndex);
    }

    private HeadingParagraphIndex findFirstHeading(int startParagraph) {
        String firstHeading = "";
        int paragraphIndex;
        paragraphIndex = startParagraph;
        while (caseParagraph.paragraphExists(paragraphIndex)) {
            firstHeading = getFirstHeading(firstHeading, paragraphIndex);
            if (!firstHeading.isEmpty() && !exceedsPartyHeadingLength(firstHeading))
                break;
            paragraphIndex++;
        }
        return new HeadingParagraphIndex(firstHeading, paragraphIndex);
    }

    private boolean exceedsPartyHeadingLength(String heading) {
        int MAX_PARTIES_HEADING_LENGTH = 3;
        return heading.split(" ").length > MAX_PARTIES_HEADING_LENGTH;
    }

    private String getFirstHeading(String firstHeading, int paragraphIndex) {
        if (this.caseParagraph.isSectionHeading(paragraphIndex)) {
            firstHeading = this.caseParagraph.getHeadingFromParagraph(paragraphIndex);
        }
        return firstHeading;
    }

    private boolean isPartySectionHeading(String partiesHeading) {
        for (String heading : PARTIES_HEADINGS) {
            if (partiesHeading.toUpperCase().contains(heading)) {
                return true;
            }
        }
        return false;
    }

    private int parsePartiesSubsections(int startParagraph) {
        subsectionStart = startParagraph;
        while (caseParagraph.paragraphExists(subsectionStart) && !reachedSubjectMatterSection &&
                startParagraph < caseParagraph.getNumberOfParagraphs()) {
            if (section.startsSubjectMatterSection(subsectionStart)) {
                reachedSubjectMatterSection = true;
                break;
            }
            addPartiesSubsection(subsectionStart);
        }
        return subsectionStart;
    }

    private void addPartiesSubsection(int paragraphIndex) {
        String paragraphText = caseParagraph.getParagraphText(paragraphIndex);
        boolean isProsecutor = startsProsecutorSubsection(paragraphIndex);
        boolean isHeading = this.caseParagraph.isSectionHeading(paragraphIndex);
        String heading = caseParagraph.getHeadingFromParagraph(paragraphIndex);

        if (isHeading && !hasProsecutor(heading)) {
            parseAndAddNormalSubsection(paragraphIndex);
        }
        else if (isProsecutor) {
            parseAndAddCriminalCaseProsecutorSubsection(paragraphIndex, paragraphText);
        }
        else {
            parseAndAddPartyWithoutExplicitHeading(paragraphIndex);
        }
    }

    private void parseAndAddNormalSubsection(int startParagraph) {
        String inlineParagraph = caseParagraph.getInlineHeadingFirstParagraph(startParagraph);
        section.setStartingParagraph(startParagraph)
                .setInlineParagraph(inlineParagraph)
                .parse();
        String subsectionName = caseParagraph.getHeadingFromParagraph(startParagraph);
        addSubsectionContent(subsectionName, section.getBody());
        updateSubsectionStart(section.getLastParagraph());
    }

    private void addSubsectionContent(String subsectionName, JsonArray subsectionBody) {
        this.partiesSubsections.putValue(JsonCreator.getJsonObject(subsectionName, subsectionBody));
    }

    private void updateSubsectionStart(int paragraphIndex) {
        subsectionStart = paragraphIndex;
    }

    private boolean startsProsecutorSubsection(int paragraphIndex) {
        String text = caseParagraph.getParagraphText(paragraphIndex);
        return hasProsecutor(text) &&
                previousParagraphIsNotSubsectionHeading(paragraphIndex);
    }

    private boolean hasProsecutor(String text) {
        return text.toLowerCase().contains(UBUSHINJACYAHA);
    }

    private void parseAndAddCriminalCaseProsecutorSubsection(int startParagraph, String paragraphText) {
        boolean conjunctionOnNextLine = hasConjunctionOnNextLine(startParagraph);
        boolean conjunctionOnSameLine = paragraphText.toLowerCase().endsWith(" " + AND_CONJUNCTION);

        if (conjunctionOnNextLine || conjunctionOnSameLine) {
            JsonArray sectionBody = new JsonArray();
            sectionBody.putValue(paragraphText);
            addSubsectionContent(DEFAULT_PARTY_SUBHEADING, sectionBody);
            int nextSubsectionStart = conjunctionOnSameLine ? startParagraph + 1 : startParagraph + 2;
            updateSubsectionStart(nextSubsectionStart);
        }
        else {
            section.setStartingParagraph(startParagraph)
                    .setInlineParagraph(paragraphText)
                    .parse();
            addSubsectionContent(UBUSHINJACYAHA, section.getBody());
            updateSubsectionStart(section.getLastParagraph());
        }
    }

    private boolean hasConjunctionOnNextLine(int paragraphIndex) {
        String text = "";
        if (caseParagraph.paragraphExists(paragraphIndex + 1))
            text = caseParagraph.getParagraphText(paragraphIndex + 1);
        return text.toLowerCase().equals(AND_CONJUNCTION) ;
    }

    private boolean previousParagraphIsNotSubsectionHeading(int paragraphIndex) {
        if (paragraphIndex - 1 < 0) return true;
        String pP = caseParagraph.getHeadingFromParagraph(paragraphIndex - 1).toUpperCase();
        return !caseParagraph.isSectionHeading(paragraphIndex - 1) ||
                PARTIES_HEADINGS.contains(pP);
    }

    private void parseAndAddPartyWithoutExplicitHeading(int paragraphIndex) {
        String textContent = caseParagraph.getParagraphText(paragraphIndex);
        if (!textContent.toLowerCase().equals(AND_CONJUNCTION)) {
            JsonArray partyContent = new JsonArray();
            partyContent.putValue(textContent);
            addSubsectionContent(DEFAULT_PARTY_SUBHEADING, partyContent);
        }
        updateSubsectionStart(paragraphIndex + 1);
    }

    public boolean skippedParagraphs() {
        return false;
    }
}
