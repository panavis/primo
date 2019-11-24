package com.panavis.primo;

import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.Parsers.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Validation.ParsingValidator;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class PrimoSetup {

    public boolean validTitle;
    public boolean validParties;
    public boolean validSubjectMatter;
    public boolean validCaseBody;
    public boolean validPanel;
    public boolean skippedParagraphs;
    // 4 underscores
    private static final String INTER_FOLDER_DELIMITER = "_" + "_" + "_" + "_";

    public boolean run(String wordInputPath, String jsonOutputPath) {
        boolean successfulParsing = false;
        String wordDocPath = getNormalizedDocPath(wordInputPath);

        ParsedCase parsedCase = getParsedCaseFromWordDoc(wordInputPath);
        ParsingValidator validator = new ParsingValidator(parsedCase);
        updateValidParsingStats(validator);

        if (!validator.isParsedCaseValid()) {
            Map<String, SectionResult> parsedCaseAsMap = parsedCase.getParsedCaseAsMap();
            SectionResult inputFilePath = getInputFilePathAsSectionResult(wordDocPath);
            parsedCaseAsMap.put(WORD_DOC_PATH, inputFilePath);
            useEmptyStringForNonValidSections(parsedCaseAsMap);
            JSONObject gson = JsonObject.toParsedGson(parsedCaseAsMap);
            createFile(jsonOutputPath, gson.toJSONString());
//            successfulParsing = true;
        }

        return successfulParsing;
    }

    private void useEmptyStringForNonValidSections(Map<String, SectionResult> parsedCaseAsMap) {
        if (!this.validTitle) {
            JsonObject emptyTitle = new JsonObject();
            emptyTitle.addNameValuePair(CASE_TITLE, "");
            SectionResult newTitle = new SectionResult(emptyTitle, -1);
            parsedCaseAsMap.put(CASE_TITLE, newTitle);
        }
        if (!this.validParties) {
            parsedCaseAsMap.put(CASE_PARTIES, getEmptySectionResult(CASE_PARTIES));
        }
        if (!this.validSubjectMatter) {
            parsedCaseAsMap.put(SUBJECT_MATTER, getEmptySectionResult(SUBJECT_MATTER));
        }
        if (!this.validCaseBody) {
            parsedCaseAsMap.put(CASE_BODY, getEmptySectionResult(CASE_BODY));
        }
        if (!this.validPanel) {
            parsedCaseAsMap.put(CASE_PANEL, getEmptySectionResult(CASE_PANEL));
        }

        parsedCaseAsMap.put(PRE_CASE_BODY, getEmptySectionResult(PRE_CASE_BODY));
        parsedCaseAsMap.put(CASE_CLOSING, getEmptySectionResult(CASE_CLOSING));
    }

    private SectionResult getEmptySectionResult(String sectionName) {
        JsonObject section = new JsonObject();
        section.addNameValuePair(sectionName, new JsonArray());
        return new SectionResult(section, -1);
    }

    private static String getNormalizedDocPath(String wordInputPath) {
        String wordDocsParentFolder = "cases_Word";
        String wordDocPath = "";
        if (wordInputPath.contains(wordDocsParentFolder)) {
            String uniqueDocPath = wordInputPath.split("cases_Word/")[1];
            String withoutBackslash = uniqueDocPath.replaceAll("/", INTER_FOLDER_DELIMITER);
            // removes .docx
            wordDocPath = withoutBackslash.substring(0, withoutBackslash.length() - 5);
        }
        return wordDocPath;
    }

    private SectionResult getInputFilePathAsSectionResult(String wordFileInputPath) {
        JsonObject filePath = new JsonObject();
        filePath.addNameValuePair(WORD_DOC_PATH, wordFileInputPath);
        return new SectionResult(filePath, -1);
    }

    private void updateValidParsingStats(ParsingValidator validator) {
        validTitle = validator.isTitleValid();
        validParties = validator.arePartiesValid();
        validSubjectMatter = validator.isSubjectMatterValid();
        validCaseBody = validator.isCaseBodyValid();
        validPanel = validator.isCasePanelValid();
        skippedParagraphs = validator.getSkippedParagraphsStatus();
    }

    private ParsedCase getParsedCaseFromWordDoc(String wordFilePath) {
        Primo parser = getParser(wordFilePath);
        parser.parseCaseSections();
        return parser.getParsedCase();
    }

    private Primo getParser(String wordFilePath) {
        CaseParagraph caseParagraph = new CaseParagraph(wordFilePath);
        CaseBodyFormat caseBodyFormat = new CaseBodyFormat(caseParagraph);

        Section sectionParties = new SectionParties(caseParagraph);
        Section sectionSubjectMatter = new SectionSubjectMatter(caseParagraph, caseBodyFormat);
        SectionCaseBody sectionCaseBodyOldFormat = new SectionBodyOldFormat(caseParagraph, caseBodyFormat);
        SectionCaseBody sectionCaseBodyNewFormat = new SectionBodyNewFormat(caseParagraph, caseBodyFormat);
        Section sectionClosing = new SectionClosing(caseParagraph, caseBodyFormat);


        ICaseSectionParser titleParser = new CaseTitleParser(caseParagraph);
        ICaseSectionParser partiesParser = new CasePartiesParser(caseParagraph, sectionParties);
        ICaseSectionParser subjectMatterParser = new CaseSubjectMatterParser(caseParagraph, sectionSubjectMatter);
        ICaseSectionParser preCaseBodyParser = new PreCaseBodyParser(caseParagraph, caseBodyFormat);
        ICaseSectionParser caseBodyParserOldFormat = new CaseBodyParserOldFormat(caseParagraph, caseBodyFormat, sectionCaseBodyOldFormat);
        ICaseSectionParser caseBodyParser = new CaseBodyParserNewFormat(caseParagraph, caseBodyFormat,sectionCaseBodyNewFormat);
        ICaseSectionParser caseClosingParser = new CaseClosingParser(caseParagraph, caseBodyFormat, sectionClosing);
        ICaseSectionParser casePanelParser = new CasePanelParser(caseParagraph, caseBodyFormat);
        return new Primo(caseBodyFormat,
                        titleParser, partiesParser,
                        subjectMatterParser, preCaseBodyParser,
                        caseBodyParserOldFormat, caseBodyParser,
                        caseClosingParser, casePanelParser);
    }

    private void createFile(String jsonOutputPath, String jsonString){
        try (FileWriter file = new FileWriter(jsonOutputPath)) {
            file.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
