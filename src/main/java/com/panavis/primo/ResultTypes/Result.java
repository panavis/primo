package com.panavis.primo.ResultTypes;

public class Result {

    public boolean value;
    public int index;

    public Result(boolean value, int index) {
        this.value = value;
        this.index = index;
    }

    public Result(boolean value) {
        this.value = value;
    }
}
