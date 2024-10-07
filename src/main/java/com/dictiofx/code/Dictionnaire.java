package com.dictiofx.code;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionnaire {

    private final Map<String, Mot> mots = new HashMap<>();

    private final File file = new File("src/main/java/com/dictiofx/code/DictioTxt.txt");

    public Dictionnaire(){
        charger();
    }

    public void ajouter(Mot mot){
        mots.put(mot.getMot(), mot);
    }

    public void ajouter(String mot, String def){
        Mot mot1 = new Mot(mot, def);
        ajouter(mot1);
    }

    public Mot get(String mot){
        return mots.get(mot);
    }

    public void sauvegarder() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            boolean firstEntry = true;

            for (Mot m : mots.values()) {
                String entry = m.getMot().toLowerCase().concat(" @@ ").concat(m.getDef());

                if (firstEntry) {
                    writer.write(entry);
                    firstEntry = false;
                } else {
                    writer.write("%%" + entry);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void charger() {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }

            String[] liste = stringBuilder.toString().split("%%");

            for (String md : liste) {
                if (!md.isBlank()) {
                    String[] motDef = md.split(" @@ ");
                    if (motDef.length == 2) {
                        ajouter(motDef[0], motDef[1]);
                    } else {
                        System.out.println("Format incorrect for entry: " + md);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("File not Found.");
        }
    }


    public Map<String, Mot> mots(){
        return mots;
    }

    public boolean contient(String mot){

        for (String s : mots.keySet()){
            if (s.equalsIgnoreCase(mot)){
                return true;
            }
        }
        return false;
    }

}
