package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;

public interface ICaseSubjectMatter {

    SectionResult parse(int startParagraph);
}
