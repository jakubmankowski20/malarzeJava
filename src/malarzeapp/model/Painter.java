package malarzeapp.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Painter {

    String name;
    String dateAndPlace;
    ArrayList<Art> art = new ArrayList<>();;
    File folder;
    public Painter()
    {
        
    }
    public Painter(File folder, String fileName) throws FileNotFoundException, IOException {
        this.folder = folder;
        String SplitBy = "\t";
        String line = null;
         
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(folder.toString() + "\\" + fileName), "ISO-8859-2"));
        if ((line = reader.readLine()) != null) {
            name = line;
        }
        if ((line = reader.readLine()) != null) {
            dateAndPlace = line;
        }
        while ((line = reader.readLine()) != null) {

            String[] lineValues = line.split(SplitBy);
            art.add(new Art(lineValues[0].replaceAll("\"", "").trim(), lineValues[1].replaceFirst("\"", "").trim().substring(0, lineValues[1].length() - 2).trim(), lineValues[2].replaceAll("\"", "").replaceAll("\\<[^>]*>","").trim().replaceAll("\\?", "")));
        }
        reader.close();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public String getDateAndPlace() {
        return dateAndPlace;
    }

    public ArrayList<Art> getArt() {
        return art;
    }
}
