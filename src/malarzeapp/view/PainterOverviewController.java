/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malarzeapp.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.util.Duration;
import malarzeapp.MainApp;
import malarzeapp.model.Painter;
import malarzeapp.model.Animation;

/**
 * FXML Controller class
 *
 * @author Kuba
 */
public class PainterOverviewController implements Initializable {

    private int numberOfArt = 0;
    private int numberOfPainter = 0;
    private Animation animation;
    Painter selectedPainter = new Painter();
    @FXML
    private Label artDescriptionLabel = new Label();

    @FXML
    private ImageView artImageView2 = new ImageView();

    @FXML
    private ImageView artImageView = new ImageView();

    //private ChoiceBox<> painterChoiceBox;
    @FXML
    private ChoiceBox<Painter> painterChoiceBox = new ChoiceBox<>();

    private MainApp mainApp;
    private double initialMousePositionX;
    private double endMousePositionX;

    public PainterOverviewController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void handleNextArt() {
        numberOfArt++;
        numberOfArt %= mainApp.getPainterData().get(numberOfPainter).getArt().size();
        showSelectedImage(numberOfPainter, numberOfArt);
        System.out.println("ToRight");
    }

    @FXML
    private void handlePreviousArt() {
        numberOfArt--;

        numberOfArt = (mainApp.getPainterData().get(numberOfPainter).getArt().size() + numberOfArt) % mainApp.getPainterData().get(numberOfPainter).getArt().size();
        showSelectedImage(numberOfPainter, numberOfArt);
        System.out.println("ToLeft");
    }

    public void setMainApp(MainApp mainApp) {

        this.mainApp = mainApp;

        painterChoiceBox.setItems(mainApp.getPainterData());
        painterChoiceBox.getSelectionModel().selectFirst();
        painterChoiceBox.setTooltip(new Tooltip("Wybierz malarza"));
        showSelectedImage(numberOfPainter, numberOfArt);
        painterChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                numberOfArt = 0;
                numberOfPainter = number2.intValue();

                showSelectedImage(numberOfPainter, numberOfArt);

            }
        });
        artImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
        artImageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
        artImageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
        artImageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                initialMousePositionX = event.getX();
            }
        });
        artImageView.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                endMousePositionX = event.getX();
                if (initialMousePositionX - endMousePositionX > 50) {
                    handlePreviousArt();
                } else if (initialMousePositionX - endMousePositionX < -50) {
                    handleNextArt();
                }
            }
        });
        artImageView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
    }

    private void showSelectedImage(int painter, int art) {

        artImageView.setImage(new Image("file:" + mainApp.path + mainApp.getPainterData().get(painter).getArt().get(art).getFileName()));
        artImageView.setFitWidth(500);
        artImageView.setFitHeight(500);
        artImageView.setPreserveRatio(true);
        artImageView.setSmooth(true);
        artImageView.setCache(true);
        animation = new Animation(this.artImageView);
//        if (animation != null) {
//            animation.stop();
//        }
        
        animation.play();

        artDescriptionLabel.setText(mainApp.getPainterData().get(painter).getArt().get(art).getArtName() + "\n" + mainApp.getPainterData().get(painter).getArt().get(art).getDescription());
    }
}
