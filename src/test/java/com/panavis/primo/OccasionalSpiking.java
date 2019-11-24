package com.panavis.primo;

public class OccasionalSpiking {

    public static void main(String[] args) {
        String folder = "/home/anselme/Documents/p_spiking";

        String word = folder + "/case_undone_t1.docx";
        String json = folder + "/case_undone_t1.json";
        PrimoSetup runner = new PrimoSetup();
        System.out.println("Success: " + runner.run(word, json));
        System.out.println("valid title: " + runner.validTitle);
        System.out.println("valid parties: " + runner.validParties);
        System.out.println("valid subject matter: " + runner.validSubjectMatter);
        System.out.println("valid case body: " + runner.validCaseBody);
        System.out.println("valid case panel: " + runner.validPanel);
    }
}
