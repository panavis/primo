package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class CaseBodyParser implements ICaseBodyParser {

    private WordParagraph wordParagraph;

    public CaseBodyParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    public SectionResult parse(int startParagraph) {
        return new SectionResult(new JsonObject(), 0);
    }
}
