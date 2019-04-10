package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;

public interface ICaseSectionParser {

    SectionResult parse(int startParagraph);
}
