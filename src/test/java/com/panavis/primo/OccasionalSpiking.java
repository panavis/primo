package com.panavis.primo;

public class OccasionalSpiking {

    public static void main(String[] args) {
        String folder = "/home/anselme/Documents/p_spiking";

        String word = folder + "/case_000.docx";
        String json = folder + "/case_000.json";
        Main runner = new Main();
        System.out.println("Success: " + runner.run(word, json));
        System.out.println("valid parties: " + runner.validParties);
        System.out.println("valid subject matter: " + runner.validSubjectMatter);
        System.out.println("valid case body: " + runner.validCaseBody);
    }
}
