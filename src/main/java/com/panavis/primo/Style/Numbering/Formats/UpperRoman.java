package com.panavis.primo.Style.Numbering.Formats;

import com.panavis.primo.Style.Numbering.RomanNumber;
import com.panavis.primo.Style.Numbering.UnitNumbering;

public class UpperRoman extends UnitNumbering {

    public UpperRoman(String formatDisplay) {
        super(formatDisplay);
    }

    @Override
    public UnitNumbering setCurrentNumbering(int number) {
        this.current = getNumbering(number);
        return this;
    }

    private String getNumbering(int number) {
        String currentLetter = RomanNumber.toRoman(number);
        return getFormattedString(formatDisplay, currentLetter);
    }

    @Override
    public UnitNumbering setLogicalNextNumbering(int number) {
        this.logicalNext = getNumbering(number + 1);
        return this;
    }

    @Override
    public UnitNumbering setNumberingStyle(String style) {
        this.style = style;
        return this;
    }
}
