package com.panavis.primo.Parsers;

import com.panavis.primo.ResultTypes.SectionResult;

public interface ICaseSectionParser {

    SectionResult parse(int startParagraph);
    boolean skippedParagraphs();
}
