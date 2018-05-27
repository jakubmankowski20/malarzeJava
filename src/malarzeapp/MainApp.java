/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malarzeapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import malarzeapp.model.Painter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import malarzeapp.view.PainterOverviewController;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    public String path="C:\\Users\\Jakub\\Downloads\\malarze\\";

    private ObservableList<Painter> painterData = FXCollections.observableArrayList();
    
    public MainApp() {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        String[] fileName = new String[getFileCount(folder)];
        fileName = getFilesName(folder);
        
        for (int i = 0; i < fileName.length; i++) {
            try {
                painterData.add(new Painter(folder, fileName[i]));
            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
        this.primaryStage.setTitle("Przeglądarka obrazów");
        this.primaryStage.setMinHeight(700);
        this.primaryStage.setMinWidth(500);
        
        initRootLayout();

        showPainterOverview();
    }
    
    public ObservableList<Painter> getPainterData() {
        return painterData;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private String[] getFilesName(File folder) {
        int counterTxtFile = 0;
        File[] listOfFiles = folder.listFiles();
        String[] fileName = new String[getFileCount(folder)];
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                fileName[counterTxtFile] = file.getName();
                //System.out.println(fileName[counterTxtFile]);
                counterTxtFile++;
            }
        }
        return fileName;
    }

    private int getFileCount(File folder) {
        int count = 0;
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                count++;
            }

        }
        return count;
    }
    
    public void initRootLayout(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showPainterOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PainterOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PainterOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
