package com.panavis.primo;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.Validation.ParsingValidator;
import com.panavis.primo.Validation.Validator;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.IOException;

public class OccasionalSpiking {

    public static void main(String[] args) throws IOException {
        File wordFolder = new File("/home/amucunguzi/Documents/cases_manually/word_sample");
        File[] docxFiles = wordFolder.listFiles();
        for (String wordPath : TestsSetup.getSortedFilePaths(docxFiles)) {
            XWPFDocument wordDoc = PrimoRunner.createWordDocumentObject(wordPath);
            Primo primo = TestsSetup.getConverterObject(wordDoc, Keywords.INTEKO);
            wordDoc.close();
            primo.parseCaseSections();
            Validator validator = new ParsingValidator(primo.getParsedCase());
        }
    }
}
