package com.nexttran.WordToJsonConverter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WordToJsonTestsSetup {

    private static File expectedJsonFolder = new File("/home/amucunguzi/Documents/cases_manually/json_expected");
    private static File wordFolder = new File("/home/amucunguzi/Documents/cases_manually/word_sample");
    static ArrayList<JSONObject> expectedJsonContent = new ArrayList<>();
    static ArrayList<XWPFDocument> wordDocxData = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        File[] allFilesInExpectedJsonFolder = WordToJsonTestsSetup.expectedJsonFolder.listFiles();
        ArrayList<String> expectedJsonPaths = getSortedFilePaths(allFilesInExpectedJsonFolder);

        for (String jsonPath : expectedJsonPaths) {
            if (isJsonDocument(jsonPath))  {
                JSONObject caseObject = readCaseJsonFile(jsonPath);
                expectedJsonContent.add(caseObject);
            }
        }

        File[] allFilesInWordFolder = WordToJsonTestsSetup.wordFolder.listFiles();
        ArrayList<String> wordFilePaths = getSortedFilePaths(allFilesInWordFolder);

        for (String wordPath : wordFilePaths) {
            if (isWordDocument(wordPath)) {
                XWPFDocument wordDoc = createWordDocumentObject(wordPath);
                wordDocxData.add(wordDoc);
            }
        }
    }

    private static JSONObject readCaseJsonFile(String jsonPath) {
        JSONParser jsonParser = new JSONParser();
        JSONObject caseObject = new JSONObject();
        try {
            caseObject = (JSONObject) jsonParser.parse(new FileReader(jsonPath));


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return caseObject;
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
}


