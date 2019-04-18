package com.panavis.primo;

public class OccasionalSpiking {

    public static void main(String[] args) {
        String folder = "/home/amucunguzi/Documents/cases_manually/demo";
        String word = folder + "/demo_four.docx";
        String json = folder + "/demo_four_two.json";
        PrimoRunner runner = new PrimoRunner();
        System.out.println(runner.run(word, json));
    }
}
