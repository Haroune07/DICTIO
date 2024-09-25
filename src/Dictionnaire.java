import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionnaire {

    private final Map<String, Mot> mots = new HashMap<>();

    private final File FichierMots = new File("src/ressources/ListeDeMots");

    public void ajouter(Mot m) {
        mots.put(m.getMot(), m);
        sauvegarder();
    }

    public void ajouter(String mot, String definition) {
        mots.put(mot, new Mot(mot, definition));
        sauvegarder();
    }

    public void enlever(String mot) {
        mots.remove(mot);
        sauvegarder();
    }

    public void modifier(String mot, String newDef) {

        mots.get(mot).setDefinition(newDef);
        sauvegarder();
    }

    public void toutEnlever() {
        mots.clear();
        sauvegarder();
    }

    public void sauvegarder() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FichierMots));

            for (String m : mots.keySet()) {
                writer.write(mots.get(m).toString()+ "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("fichier de sauvegarde introuvable");
        }
    }

    public void chargerMots() {
        try {

            Scanner lecteur = new Scanner(FichierMots);

            while (lecteur.hasNextLine()) {
                String ligne = lecteur.nextLine();

                String[] motEtDef = ligne.split(" : ");

                mots.put(motEtDef[0], new Mot(motEtDef[0].toLowerCase(), motEtDef[1]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("fichier de sauvegarde introuvable");
        }
    }

    public boolean contientMot(String mot) {

        return mots.containsKey(mot.toLowerCase());

    }

    public Mot getMotDuDictio(String mot) {
        return mots.get(mot);
    }

    public void chercherMot(String mot) {

        ArrayList<String> motPossibles = new ArrayList<>();

        for (String m : mots.keySet()) {
            if (m.toLowerCase().contains(mot.toLowerCase())) {
                motPossibles.add(m);
            }
        }

        if (!motPossibles.isEmpty()) {

            System.out.println("Voulez-vous dire: ");

            for (String str : motPossibles) {
                System.out.println(str + " ?");
            }

        } else {
            System.out.println("pas de mot trouvé");
        }

    }

    public void toutAfficher(){
        for (Mot m : mots.values()){
            System.out.println(m.toString());
        }


    }

}
