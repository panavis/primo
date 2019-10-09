package com.panavis.primo;

import com.panavis.primo.Parsers.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.WordParagraph;
import com.panavis.primo.Validation.ParsingValidator;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class PrimoRunner {

    public boolean validTitle;
    public boolean validParties;
    public boolean validSubjectMatter;
    public boolean validCaseBody;
    public boolean validPanel;
    public boolean skippedParagraphs;

    public boolean run(String wordInputPath, String jsonOutputPath) {
        boolean successfulParsing = false;
        XWPFDocument wordDoc = createWordDocumentObject(wordInputPath);
        if (wordDoc != null) {
            ParsedCase parsedCase = getParsedCaseFromWordDoc(wordDoc);
            ParsingValidator validator = new ParsingValidator(parsedCase);
            if (validator.isParsedCaseValid()) {
                Map<String, SectionResult> parsedCaseAsMap = parsedCase.getParsedCaseAsMap();
                JSONObject gson = JsonObject.toParsedGson(parsedCaseAsMap);
                createFile(jsonOutputPath, gson.toJSONString());
                successfulParsing = true;
            }
            updateValidParsingStats(validator);
        }
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


    XWPFDocument createWordDocumentObject(String wordPath) {
        XWPFDocument wordDoc = null;
        try {
            wordDoc = new XWPFDocument(OPCPackage.open(wordPath));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return wordDoc;
    }

    private ParsedCase getParsedCaseFromWordDoc(XWPFDocument wordDoc) {
        Primo parser = getParser(wordDoc);
        parser.parseCaseSections();
        try {
            wordDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser.getParsedCase();
    }

    private Primo getParser(XWPFDocument wordDoc) {
        WordParagraph wordParagraph = new WordParagraph(wordDoc);
        ICaseSectionParser titleParser = new CaseTitleParser(wordParagraph);
        ICaseSectionParser partiesParser = new CasePartiesParser(wordParagraph);
        ICaseSectionParser subjectMatterParser = new CaseSubjectMatterParser(wordParagraph);
        ICaseSectionParser caseBodyParser = new CaseBodyParser(wordParagraph);
        ICaseSectionParser caseClosingParser = new CaseClosingParser(wordParagraph);
        ICaseSectionParser casePanelParser = new CasePanelParser(wordParagraph);
        return new Primo(
                        titleParser,
                        partiesParser,
                        subjectMatterParser,
                        caseBodyParser,
                        caseClosingParser,
                        casePanelParser
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
