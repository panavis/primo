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

    public boolean run(String wordInputPath, String jsonOutputPath) {
        boolean successfulParsing = false;

        ParsedCase parsedCase = getParsedCaseFromWordDoc(wordInputPath);
        ParsingValidator validator = new ParsingValidator(parsedCase);
        if (validator.isParsedCaseValid()) {
            Map<String, SectionResult> parsedCaseAsMap = parsedCase.getParsedCaseAsMap();
            JSONObject gson = JsonObject.toParsedGson(parsedCaseAsMap);
            createFile(jsonOutputPath, gson.toJSONString());
            successfulParsing = true;
        }
        updateValidParsingStats(validator);

        return successfulParsing;
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
        ICaseSectionParser titleParser = new CaseTitleParser(caseParagraph);
        ICaseSectionParser partiesParser = new CasePartiesParser(caseParagraph);
        ICaseSectionParser subjectMatterParser = new CaseSubjectMatterParser(caseParagraph);
        ICaseSectionParser preCaseBodyParser = new PreCaseBodyParser(caseParagraph);
        ICaseSectionParser caseBodyParser = new CaseBodyParser(caseParagraph);
        ICaseSectionParser caseClosingParser = new CaseClosingParser(caseParagraph);
        ICaseSectionParser casePanelParser = new CasePanelParser(caseParagraph);
        return new Primo(
                        titleParser,
                        partiesParser,
                        subjectMatterParser, preCaseBodyParser,
                caseBodyParser, caseClosingParser, casePanelParser
        );
    }

    private void createFile(String jsonOutputPath, String jsonString){
        try (FileWriter file = new FileWriter(jsonOutputPath)) {
            file.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
