package com.panavis.WordToJsonConverter;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.Parsers.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.*;
import java.util.*;

public class TestsSetup {

    private static File expectedJsonFolder = new File("/home/amucunguzi/Documents/cases_manually/json_expected");
    private static File wordFolder = new File("/home/amucunguzi/Documents/cases_manually/word_sample");
    static ArrayList<JsonObject> expectedJsonContent = new ArrayList<>();
    static ArrayList<XWPFDocument> wordDocxData = new ArrayList<>();

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
                XWPFDocument wordDoc = createWordDocumentObject(wordPath);
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

    static XWPFDocument createWordDocumentObject(String wordPath) {
        XWPFDocument wordDoc = null;
        try {
            wordDoc = new XWPFDocument(OPCPackage.open(wordPath));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return wordDoc;
    }

    private static boolean isWordDocument(String wordPath) {
        return wordPath.endsWith(".docx");
    }

    static Converter getConverterObject(XWPFDocument wordDocument, String section) {
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
        return new Converter(titleParser, partiesParser, subjectMatterParser,
                            caseBodyParser,caseClosingParser);
    }

    private static boolean isSectionBeyondTitle(String section) {
        return section.equals(PARTIES) ||
                section.equals(SUBJECT_MATTER) ||
                section.equals(CASE_BODY) ||
                section.equals(CASE_CLOSING);
    }

    private static boolean isSectionBeyondParties(String section) {
        return section.equals(SUBJECT_MATTER) ||
                section.equals(CASE_BODY) ||
                section.equals(CASE_CLOSING);
    }

    private static boolean isSectionBeyondSubjectMatter(String section) {
        return section.equals(CASE_BODY) ||
                section.equals(CASE_CLOSING);
    }

    private static boolean isSectionBeyondBody(String section) {
        return section.equals(CASE_CLOSING);
    }
}

class MockSectionParser implements ICaseSectionParser {

    public SectionResult parse(int startParagraph) {
        return new SectionResult(new JsonObject(), 0);
    }
}
