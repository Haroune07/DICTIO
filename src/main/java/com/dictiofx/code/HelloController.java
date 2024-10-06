package com.dictiofx.code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private VBox scrollList;
    @FXML
    private TextArea wordArea;
    @FXML
    protected TextField search;
    @FXML
    private VBox searchList;

    Dictionnaire dictionnaire = new Dictionnaire();
    List<String> listeDeMots = new ArrayList<>();

    public HelloController() {

    }

    @FXML
    public void initialize() {
        chercher();
        listOfWords();
        search.setPromptText("Chercher...");
    }

    public void charger(ActionEvent e){
    }

    protected void listOfWords() {

        for (String s : dictionnaire.mots().keySet()) {
            Button button = new Button(s);
            button.setBackground(scrollList.getBackground());

            button.setOnAction(this::setTextArea);

            scrollList.getChildren().add(button);

            listeDeMots.add(s);
        }
    }

    private void setTextArea(ActionEvent event) {

        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        wordArea.setText(dictionnaire.get(buttonText).toTextAreaString());
    }

    public void addToList(ActionEvent e) {

        String[] motDef = wordArea.getText().trim().split("\\n\\n");

        if (motDef.length < 2) {
            System.out.println("Invalid input: Word or definition is missing.");
            return;
        }

        String mot = motDef[0].trim();  // Get the word and trim any excess space
        StringBuilder def = new StringBuilder();

        // Combine the rest of the lines as the definition
        for (int i = 1; i < motDef.length; i++) {
            if (!motDef[i].isBlank()) {
                def.append(motDef[i].trim()).append(" ");
            }
        }

        // Ensure there's no trailing space in the final definition
        String definition = def.toString().trim();

        if (!dictionnaire.contient(mot)) {
            if (!mot.isBlank() && !definition.isBlank()) {
                dictionnaire.ajouter(mot, definition);
                listeDeMots.add(mot);
            } else {
                System.out.println("Mot ou définition manquante.");
            }
        } else if (dictionnaire.contient(mot)) {
            // Check if the definition needs updating
            if (!dictionnaire.get(mot).getDef().equalsIgnoreCase(definition)) {
                dictionnaire.get(mot).setDef(definition);
                System.out.println("définition modifiée");
            } else {
                System.out.println("Le mot se trouve déjà dans le dictionnaire");
            }
        }

    }

    public void sauvegarder(ActionEvent e) {
        dictionnaire.sauvegarder();
    }

    public void chercher() {

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            searchList.getChildren().clear(); // Clear previous search results
            List<String> listeFiltre = listeDeMots.stream()
                    .filter(item -> item.toLowerCase().contains(newValue.toLowerCase()))
                    .toList();

            for (String mot : listeFiltre) {
                Button bouton = new Button(mot);
                bouton.setOnAction(_ -> setTextAreaForSearch(mot));
                bouton.setBackground(searchList.getBackground());// Pass the word directly
                searchList.getChildren().add(bouton);
            }
        });
    }

    private void setTextAreaForSearch(String buttonText) {
        wordArea.setText(dictionnaire.get(buttonText).toTextAreaString());
    }

}
