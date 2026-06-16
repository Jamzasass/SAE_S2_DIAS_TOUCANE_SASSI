package universite_paris8.iut.rdias.towerdefense.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.controller.Controller;
import universite_paris8.iut.rdias.towerdefense.model.Animation;
import universite_paris8.iut.rdias.towerdefense.model.Effect;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.Interpolator;


public class AnimationView {
    private static Image imageMacron = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/animation/sprite_macron.png"));
    private static Image imageSoldat = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/animation/sprite_knight.png"));
    private static Image imageGodard = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/animation/sprite_jeanLucGodard.png"));
    private ImageView image;
    private HBox paneViewContainer;
    private Animation animation;
    private double screenWidth;
    private double screenHeight;

    public AnimationView(Animation aAnimation, double aScreenWidth, double aScreenHeight) {
        this.animation = aAnimation;
        this.screenWidth = aScreenWidth;
        this.screenHeight = aScreenHeight;
        this.paneViewContainer = new HBox();
        Label l = new Label("Nouvelle Vague");
        if (this.animation.isMacronActivated()) {
            this.image = new ImageView(imageMacron);
            l.setText("Nouvelle Vague Inflation");
        }
        else {
            this.image = new ImageView(imageSoldat);
        }
        this.image.setFitHeight(128);
        this.image.setFitWidth(128);
        this.paneViewContainer.setId("a" + animation.getId());
        paneViewContainer.setStyle("-fx-text-alignment: center");

        l.getStyleClass().add("labelVague");

        this.paneViewContainer.getChildren().add(l);
        this.paneViewContainer.getChildren().add(image);
        this.paneViewContainer.toFront();
        l.setPrefHeight(paneViewContainer.getHeight());
        l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        l.setAlignment(Pos.CENTER);
        scroll();

    }
    public Pane getPaneViewContainer() {
        return paneViewContainer;
    }
    
    public void scroll() {
        paneViewContainer.setLayoutX(0);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(4), paneViewContainer);
        transition.setFromX(screenWidth-(screenWidth+paneViewContainer.getPrefWidth()));
        double finalDestination = (screenWidth - paneViewContainer.getPrefWidth());
        transition.setToX(finalDestination);
        //transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setOnFinished(event -> {
            if (paneViewContainer.getParent() != null) {
                ((Pane) paneViewContainer.getParent()).getChildren().remove(paneViewContainer);
            }
        });
        transition.play();
    }
}
