package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;

public interface ICaseSubjectMatter {

    SectionResult parse(int startParagraph);
}
