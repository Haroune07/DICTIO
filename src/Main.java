import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Dictionnaire dictio = new Dictionnaire();
        dictio.chargerMots();

        Scanner scanner = new Scanner(System.in);

        int choix = -1;

        System.out.println();

        while (choix != 8) {

            System.out.println("\n1. Ajouter un mot");
            System.out.println("2. Modifier une définition");
            System.out.println("3. Chercher un mot");
            System.out.println("4. Chercher une définition");
            System.out.println("5. Enlever un mot");
            System.out.println("6. Enlever tous les mots");
            System.out.println("7. Afficher tous les mots");
            System.out.println("8. Sortir\n");

            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("entrez un nouveau mot");
                    String mot = scanner.nextLine();

                    System.out.println("entrez sa définition");
                    String def = scanner.nextLine();

                    dictio.ajouter(mot, def);
                    System.out.println("mot ajouté");
                    break;

                case 2:

                    System.out.println("entrez le mot à modifier");

                    String motModif = scanner.nextLine();

                    if (dictio.contientMot(motModif.toLowerCase())){
                        System.out.println("entrez sa nouvelle def");

                        String newDef = scanner.nextLine();

                        dictio.modifier(motModif, newDef);
                        System.out.println("mot modifié!");
                    }

                    else {
                        System.out.println("mot introuvable");
                    }

                    break;

                case 3:

                    System.out.println("entrez le mot dont vous chechez la définition");

                    String motCherché = scanner.nextLine();

                    if (dictio.contientMot(motCherché)){
                        System.out.println(dictio.getMotDuDictio(motCherché).getDefinition());
                    }
                    else {
                        System.out.println("mot introuvable");
                    }

                    break;

                case 4:

                    System.out.println("Entrez le mot que vous voulez chercher");

                    String motCherché2 = scanner.nextLine();

                    if (dictio.contientMot(motCherché2)){
                        dictio.chercherMot(motCherché2);
                    }
                    else {
                        System.out.println("rien n'a été trouvé");
                    }

                    break;

                case 5:

                    System.out.println("entrez le mot que vous voulez enlever");

                    dictio.enlever(scanner.nextLine());

                    System.out.println("mot enlevé!");
                    break;

                case 6:

                    System.out.println("tous les mots ont été enelvés");

                    dictio.toutEnlever();
                    break;

                case 7:
                    dictio.toutAfficher();
                    break;

            }
        }

        dictio.sauvegarder();

    }

}
