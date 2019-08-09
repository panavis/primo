package com.panavis.primo;

import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.Parsers.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.WordParagraph;
import com.panavis.primo.Wrappers.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.*;
import java.util.*;

public class TestsSetup {

    private static File expectedJsonFolder = new File("/home/anselme/Dropbox/panavis-code/Primo/devResources/json_expected");
    private static File wordFolder = new File("/home/anselme/Dropbox/panavis-code/Primo/devResources/word_sample");
    public static ArrayList<JsonObject> expectedJsonContent = new ArrayList<>();
    public static ArrayList<XWPFDocument> wordDocxData = new ArrayList<>();

    public static void setUp() {
        File[] allFilesInExpectedJsonFolder = TestsSetup.expectedJsonFolder.listFiles();
        ArrayList<String> expectedJsonPaths = getSortedFilePaths(allFilesInExpectedJsonFolder);

        for (String jsonPath : expectedJsonPaths) {
            if (isJsonDocument(jsonPath)) {
                JsonObject caseObject = readCaseJsonFile(jsonPath);
                expectedJsonContent.add(caseObject);
            }
        }

        File[] allFilesInWordFolder = TestsSetup.wordFolder.listFiles();
        ArrayList<String> wordFilePaths = getSortedFilePaths(allFilesInWordFolder);

        for (String wordPath : wordFilePaths) {
            if (isWordDocument(wordPath)) {
                XWPFDocument wordDoc = new PrimoRunner().createWordDocumentObject(wordPath);
                wordDocxData.add(wordDoc);
            }
        }
    }

    private static JsonObject readCaseJsonFile(String jsonPath) {
        return JsonParser.parseJsonFile(jsonPath);
    }

    private static boolean isJsonDocument(String filePath) {
        return filePath.endsWith(".json");
    }

    private static ArrayList<String> getSortedFilePaths(File[] FilesInFolder) {
        ArrayList<String> filePaths = new ArrayList<>();
        Arrays.stream(FilesInFolder).
                forEach(wordFile -> filePaths.add(wordFile.getPath()));
        Collections.sort(filePaths);
        return filePaths;
    }

    private static boolean isWordDocument(String wordPath) {
        return wordPath.endsWith(".docx");
    }

    public static Primo getConverterObject(XWPFDocument wordDocument, String section) {
        WordParagraph wordParagraph = new WordParagraph(wordDocument);
        CaseTitleParser titleParser = new CaseTitleParser(wordParagraph);
        ICaseSectionParser partiesParser = isSectionBeyondTitle(section) ?
                new CasePartiesParser(wordParagraph) :
                new MockSectionParser();
        ICaseSectionParser subjectMatterParser = isSectionBeyondParties(section) ?
                new CaseSubjectMatterParser(wordParagraph) :
                new MockSectionParser();
        ICaseSectionParser caseBodyParser = isSectionBeyondSubjectMatter(section) ?
                new CaseBodyParser(wordParagraph) :
                new MockSectionParser();
        ICaseSectionParser caseClosingParser = isSectionBeyondBody(section) ?
                new CaseClosingParser(wordParagraph) :
                new MockSectionParser();
        ICaseSectionParser casePanelParser = isSectionBeyondClosing(section) ?
                new CasePanelParser(wordParagraph) :
                new MockSectionParser();
        return new Primo(titleParser, partiesParser, subjectMatterParser,
                            caseBodyParser,caseClosingParser, casePanelParser);
    }

    private static boolean isSectionBeyondTitle(String section) {
        return section.equals(PARTIES) ||
                section.equals(SUBJECT_MATTER) ||
                section.equals(CASE_BODY) ||
                section.equals(CASE_CLOSING) ||
                section.equals(INTEKO);
    }

    private static boolean isSectionBeyondParties(String section) {
        return section.equals(SUBJECT_MATTER) ||
                section.equals(CASE_BODY) ||
                section.equals(CASE_CLOSING) ||
                section.equals(INTEKO);
    }

    private static boolean isSectionBeyondSubjectMatter(String section) {
        return section.equals(CASE_BODY) ||
                section.equals(CASE_CLOSING) ||
                section.equals(INTEKO);
    }

    private static boolean isSectionBeyondBody(String section) {
        return section.equals(CASE_CLOSING) ||
                section.equals(INTEKO);
    }

    private static boolean isSectionBeyondClosing(String section) {
        return section.equals(INTEKO);
    }
}

class MockSectionParser implements ICaseSectionParser {

    public SectionResult parse(int startParagraph) {
        return new SectionResult(new JsonObject(), 0);
    }

    @Override
    public boolean skippedParagraphs() {
        return false;
    }
}
