package universite_paris8.iut.rdias.towerdefense;

import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.view.GroundView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Controller {

    @FXML
    private TilePane mapGrid;
    @FXML
    private Pane actorsArea;
    private Timeline gameLoop;
    private int temps;
    private Environnement env;
    public void initialize() {

        Ground ground = new Ground();
        GroundView groundView = new GroundView(ground, mapGrid);
        actorsArea.prefHeightProperty().bind(mapGrid.heightProperty());
        actorsArea.prefWidthProperty().bind(mapGrid.widthProperty());
        env = new Environnement(ground, actorsArea);
        groundView.drawMap();

        //définition et démarrage d'un gameloop qui fait env.unTour()
        initAnimation();
        gameLoop.play();
    }

    public void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==10000){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    if (temps%5 == 0) {
                        env.unTour();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}
