package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;

public interface ICaseBodyParser {

    SectionResult parse(int startParagraph);
}
