package org.example.r5a12.controller;

import javafx.stage.FileChooser;

import java.io.File;

public class FileChooserJson {

    private final FileChooser fileChooser;

    public FileChooserJson(){
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("selectionner votre fichier");

        //fichier d'origine
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("json", "*.json"))
        ;
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }
}
