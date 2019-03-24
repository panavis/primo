package com.panavis.WordToJsonConverter.Style.Numbering.Formats;

import com.panavis.WordToJsonConverter.Style.Numbering.RomanNumber;
import com.panavis.WordToJsonConverter.Style.Numbering.UnitNumbering;

public class LowerRoman extends UnitNumbering {

    public LowerRoman(String formatDisplay) {
        super(formatDisplay);
    }

    @Override
    public UnitNumbering setCurrentNumbering(int number) {
        this.current = getNumbering(number);
        return this;
    }

    @Override
    public UnitNumbering setLogicalNextNumbering(int number) {
        this.logicalNext = getNumbering(number + 1);
        return this;
    }

    private String getNumbering(int number) {
        String currentLetter = RomanNumber.toRoman(number);
        return getFormattedString(formatDisplay, currentLetter).toLowerCase();
    }

    @Override
    public UnitNumbering setNumberingStyle(String style) {
        this.style = style;
        return this;
    }
}
