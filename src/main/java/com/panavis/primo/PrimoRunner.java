package com.panavis.primo;

import com.panavis.primo.Parsers.*;
import com.panavis.primo.Style.WordParagraph;
import com.panavis.primo.Validation.ParsingValidator;
import com.panavis.primo.Wrappers.JsonObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

class PrimoRunner {

    public boolean run(String wordInputPath, String jsonOutputPath) {
        boolean successfulParsing = false;
        XWPFDocument wordDoc = createWordDocumentObject(wordInputPath);
        if (wordDoc != null) {
            Primo parser = getParser(wordDoc);
            parser.parseCaseSections();
            ParsedCase parsedCase = parser.getParsedCase();
            ParsingValidator validator = new ParsingValidator(parsedCase);
            if (validator.isParsedCaseValid()) {
                JSONObject gson = JsonObject.toParsedGson(parsedCase.getParsedCaseAsMap());
                createFile(jsonOutputPath, gson.toJSONString());
                successfulParsing = true;
            }
        }
        return successfulParsing;
    }

    static XWPFDocument createWordDocumentObject(String wordPath) {
        XWPFDocument wordDoc = null;
        try {
            wordDoc = new XWPFDocument(OPCPackage.open(wordPath));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return wordDoc;
    }

    private Primo getParser(XWPFDocument wordDoc) {
        WordParagraph wordParagraph = new WordParagraph(wordDoc);
        ICaseSectionParser titleParser = new CaseTitleParser(wordParagraph);
        ICaseSectionParser partiesParser = new CasePartiesParser(wordParagraph);
        ICaseSectionParser subjectMatterParser = new CaseSubjectMatterParser(wordParagraph);
        ICaseSectionParser caseBodyParser = new CaseBodyParser(wordParagraph);
        ICaseSectionParser caseClosingParser = new CaseBodyParser(wordParagraph);
        ICaseSectionParser casePanelParser = new CaseBodyParser(wordParagraph);
        return new Primo(
                        titleParser,
                        partiesParser,
                        subjectMatterParser,
                        caseBodyParser,
                        caseClosingParser,
                        casePanelParser
                        );
    }



    private static void createFile(String jsonOutputPath, String jsonString){
        try (FileWriter file = new FileWriter(jsonOutputPath)) {
            file.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
