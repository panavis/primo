package com.panavis.primo;

import com.panavis.primo.Parsers.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Validation.ParsingValidator;
import com.panavis.primo.Wrappers.JsonObject;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main {

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

        ParsedCase parsedCase = getParsedCaseFromWordDoc(wordInputPath);
        ParsingValidator validator = new ParsingValidator(parsedCase);
        String wordDocPath = getNormalizedDocPath(wordInputPath);

        if (validator.isParsedCaseValid()) {
            Map<String, SectionResult> parsedCaseAsMap = parsedCase.getParsedCaseAsMap();
            SectionResult inputFilePath = getInputFilePathAsSectionResult(wordDocPath);
            parsedCaseAsMap.put("wordDocPath", inputFilePath);
            JSONObject gson = JsonObject.toParsedGson(parsedCaseAsMap);
            createFile(jsonOutputPath, gson.toJSONString());
            successfulParsing = true;
        }
        updateValidParsingStats(validator);

        return successfulParsing;
    }

    private static String getNormalizedDocPath(String wordInputPath) {
        String uniqueDocPath = wordInputPath.split("cases_Word/")[1];
        String withoutBackslash = uniqueDocPath.replaceAll("/", INTER_FOLDER_DELIMITER);
        // removes .docx
        return withoutBackslash.substring(0, withoutBackslash.length() - 5);
    }

    private SectionResult getInputFilePathAsSectionResult(String wordFileInputPath) {
        JsonObject filePath = new JsonObject();
        filePath.addNameValuePair("path", wordFileInputPath);
        return new SectionResult(filePath, -1);
    }

    private void updateValidParsingStats(ParsingValidator validator) {
        validTitle = validator.getTitleParsingStatus();
        validParties = validator.getPartiesParsingStatus();
        validSubjectMatter = validator.getSubjectMatterParsingStatus();
        validCaseBody = validator.getCaseBodyParsingStatus();
        validPanel = validator.getPanelParsingStatus();
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
