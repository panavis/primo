package com.panavis.primo.ResultTypes;

public class Result {

    public boolean isValid;
    public int startParagraph;

    public Result(boolean isValid, int startParagraph) {
        this.isValid = isValid;
        this.startParagraph = startParagraph;
    }

    public Result(boolean isValid) {
        this.isValid = isValid;
    }
}
