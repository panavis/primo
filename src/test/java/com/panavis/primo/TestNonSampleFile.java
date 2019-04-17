package com.panavis.primo;

import com.panavis.primo.Constants.Keywords;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

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
