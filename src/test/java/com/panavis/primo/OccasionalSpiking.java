package com.panavis.primo;

public class OccasionalSpiking {

    public static void main(String[] args) {
        String folder = "/home/amucunguzi/Documents/cases_manually/job";
        String word = folder + "/breaking_003.docx";
        String json = folder + "/breaking_003.json";
        PrimoRunner runner = new PrimoRunner();
        System.out.println(runner.run(word, json));
    }
}
