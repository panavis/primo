package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.simple.JSONObject;

import java.io.IOException;

public class TestNonSampleFile {

    public static void main(String[] args) {
        String path = "/home/amucunguzi/Documents/cases_manually/demo/demo_two.docx";
        XWPFDocument wordDoc = TestsSetup.createWordDocumentObject(path);
        Converter converter = TestsSetup.getConverterObject(wordDoc, Keywords.CASE_BODY);
        converter.parseCaseSections();
        String jsonFile = "/home/amucunguzi/Documents/cases_manually/demo/demo_two.json";
        /*JSONObject gson = JsonObject.toParsedGson(converter.getParsedCase());
        try {
            JsonFileWriter.createFile(jsonFile, gson.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
