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
        this.image = new ImageView(imageMacron);

        this.paneViewContainer.setId("a" + animation.getId());

        paneViewContainer.setStyle("-fx-text-alignment: center");

        Label l = new Label("Vague Inflation");
        l.getStyleClass().add("labelVague");

        l.setStyle("-fx-background-color: red");
        this.paneViewContainer.getChildren().add(l);
        this.paneViewContainer.getChildren().add(image);
        this.paneViewContainer.toFront();
        l.setPrefHeight(paneViewContainer.getHeight());
        l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        l.setAlignment(Pos.CENTER);
        paneViewContainer.setLayoutY((screenHeight/2) - (paneViewContainer.getPrefHeight()/2));
        System.out.println(paneViewContainer.getPrefHeight());
        scroll();

    }
    public Pane getPaneViewContainer() {
        return paneViewContainer;
    }
    
    public void scroll() {

        // 2. On positionne le Pane tout à droite, invisible (hors-écran)
        paneViewContainer.setLayoutX(0);

        // 3. Configuration de la transition (Durée : 4 secondes)
        TranslateTransition transition = new TranslateTransition(Duration.seconds(4), paneViewContainer);

        // Au départ (FromX = 0), le décalage est nul, donc le Pane est à 'screenWidth'
        transition.setFromX(screenWidth-(screenWidth+paneViewContainer.getPrefWidth()));

        // À la fin, on veut qu'il sorte par la gauche. 
        // Distance = Largeur écran + Largeur du Pane lui-même (pour qu'il sorte à 100%)
        double finalDestination = (screenWidth - paneViewContainer.getPrefWidth());
        transition.setToX(finalDestination);

        // 4. Un peu de style : vitesse fluide (accélère au début, ralentit à la fin)
        transition.setInterpolator(Interpolator.EASE_BOTH);

        // 5. Nettoyage : Une fois l'animation finie, on peut cacher ou détruire le Pane
        transition.setOnFinished(event -> {
            if (paneViewContainer.getParent() != null) {
                // On le retire du jeu pour libérer de la mémoire
                ((Pane) paneViewContainer.getParent()).getChildren().remove(paneViewContainer);
            }
        });

        // C'est parti !
        transition.play();
    }
}
