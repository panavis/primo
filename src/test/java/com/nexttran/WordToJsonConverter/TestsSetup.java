package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;
import com.nexttran.WordToJsonConverter.Wrappers.JsonParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TestsSetup {

    private static File expectedJsonFolder = new File("/home/amucunguzi/Documents/cases_manually/json_expected");
    private static File wordFolder = new File("/home/amucunguzi/Documents/cases_manually/word_sample");
    static ArrayList<JsonObject> expectedJsonContent = new ArrayList<>();
    static ArrayList<XWPFDocument> wordDocxData = new ArrayList<>();

    public static void setUp() {
        File[] allFilesInExpectedJsonFolder = TestsSetup.expectedJsonFolder.listFiles();
        ArrayList<String> expectedJsonPaths = getSortedFilePaths(allFilesInExpectedJsonFolder);

        for (String jsonPath : expectedJsonPaths) {
            if (isJsonDocument(jsonPath))  {
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

    private static XWPFDocument createWordDocumentObject(String wordPath) {
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
        ICaseParties partiesParser = (section.equals("parties") || section.equals("subject_matter")) ?
                                new CasePartiesParser(wordParagraph) : new MockPartiesParser();
        ICaseSubjectMatter subjectMatterParser = section.equals("subject_matter") ?
                                new CaseSubjectMatterParser(wordParagraph) : new MockSubjectMatterParser();
        return new Converter(titleParser, partiesParser, subjectMatterParser);
    }
}

class MockPartiesParser implements ICaseParties {

    public SectionResult parse(int startParagraph) {
        return new SectionResult(new JsonObject(), 0);
    }
}

class MockSubjectMatterParser implements ICaseSubjectMatter {

    public SectionResult parse(int startParagraph) {
        return new SectionResult(new JsonObject(), 0);
    }
}

