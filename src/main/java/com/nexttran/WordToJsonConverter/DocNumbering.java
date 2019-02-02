package com.nexttran.WordToJsonConverter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocNumbering {

    private XWPFDocument wordDocument;

    DocNumbering(XWPFDocument wordDocument) {
        this.wordDocument = wordDocument;
    }

    public static Map<Integer, String> getDocNumbering(List<XWPFParagraph> paragraphs) {
        return new HashMap<>();
    }
}
