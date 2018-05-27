/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malarzeapp.model;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Kuba
 */
public class Animation {

    private ScaleTransition st;
    private RotateTransition rt;
    private FadeTransition ft;
    private ImageView artImageView;

    public Animation(ImageView artImageView) {
        this.artImageView = artImageView;
    }

    public void play() {
        st = new ScaleTransition(Duration.millis(2000), artImageView);
        st.setFromX(0);
        st.setFromY(0);
        st.setToX(1);
        st.setToY(1);
        st.setCycleCount(1);
        st.setAutoReverse(false);
        st.play();

        rt = new RotateTransition(Duration.millis(2000), artImageView);
        rt.setFromAngle(0);
        rt.setByAngle(360);
        rt.setCycleCount(1);
        rt.setAutoReverse(false);
        rt.play();

        ft = new FadeTransition(Duration.millis(2000), artImageView);
        ft.setFromValue(0.2);
        ft.setToValue(1.0);
        ft.play();
    }

    public void stop() {
        if (st != null && rt != null && ft != null) {
            st.stop();
            rt.stop();
            ft.stop();
        }
    }
}
