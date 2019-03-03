package com.nexttran.WordToJsonConverter.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Headings {

    public static final String HABURANA = "HABURANA";
    public static final String ABABURANA = "ABABURANA";
    public static final String ABABURANYI = "ABABURANYI";
    public static final String UREGA = "UREGA";
    public static final String UREGWA = "UREGWA";
    public static final String ABAGOBOKESHWA = "ABAGOBOKESHWA";
    public static final String UWAJURIYE = "UWAJURIYE";
    public static final String ABAREGWA = "ABAREGWA";
    public static final String IKIREGERWA = "IKIREGERWA";
    public static final String IKIBURANWA = "IKIBURANWA";
    public static final String  URUKIKO = "URUKIKO";
    public static final String ICYAHA_AREGWA = "ICYAHA AREGWA";
    public static final String ICYAHA_ASHINJWA = "ICYAHA ASHINJWA";
    public static final List<String> PARTIES_HEADINGS = new ArrayList<>(Arrays.asList(
            "HABURANA",
            "ABABURANA",
            "ABABURANYI"));

    public static final List<String> SUBJECT_MATTER_HEADINGS = new ArrayList<>(
                                                Arrays.asList(Headings.IKIREGERWA, "IKIBURANWA",
                                                        "IBYAHA AREGWA", "ICYAHA ASHINJWA",
                                                        Headings.ICYAHA_AREGWA));
}
