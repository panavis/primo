package com.panavis.WordToJsonConverter.Style;

public class UnitNumbering {

    public String current;
    public String realNext;
    public String logicalNext;
    public String style;

    public UnitNumbering(String current, String logicalNext, String style) {
        this.current = current;
        this.logicalNext = logicalNext;
        this.realNext = "";
        this.style = style;
    }
}
