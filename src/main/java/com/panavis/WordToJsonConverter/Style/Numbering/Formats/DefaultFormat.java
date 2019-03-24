package com.panavis.WordToJsonConverter.Style.Numbering.Formats;

import com.panavis.WordToJsonConverter.Style.Numbering.UnitNumbering;
import static com.panavis.WordToJsonConverter.Utils.StringFormatting.*;

public class DefaultFormat extends UnitNumbering {

    public DefaultFormat() {
        super("");
        this.current = EMPTY_STRING;
        this.logicalNext = EMPTY_STRING;
        this.realNext = EMPTY_STRING;
        this.style = "Text";
    }

    @Override
    public UnitNumbering setCurrentNumbering(int number) {
        return this;
    }

    @Override
    public UnitNumbering setLogicalNextNumbering(int number) {
        return this;
    }

    @Override
    public UnitNumbering setNumberingStyle(String style) {
        return this;
    }
}
