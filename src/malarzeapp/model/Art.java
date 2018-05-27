/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malarzeapp.model;

public class Art {

    String fileName;
    String artName;
    String description;

    public Art(String fileName, String artName, String description) {
        this.fileName = fileName;
        this.artName = artName;
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public String getArtName() {
        return artName;
    }

    public String getDescription() {
        return description;
    }

}
