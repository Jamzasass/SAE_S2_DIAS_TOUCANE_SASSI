package universite_paris8.iut.rdias.towerdefense;

import javafx.fxml.FXML;
import javafx.scene.layout.Border;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.view.GroundView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.rdias.towerdefense.view.ObsEnemy;
import universite_paris8.iut.rdias.towerdefense.view.ObsKnight;
import universite_paris8.iut.rdias.towerdefense.view.SoldierView;

public class Controller {

    @FXML
    private TilePane mapGrid;
    private Ground ground;
    private GroundView groundView;
    @FXML
    private Pane actorsArea;
    private Timeline gameLoop;
    private int temps;
    private Environnement env;
    private ObsEnemy obsEnemy;
    private ObsKnight obsKnight;
    public void initialize() {

        ground = new Ground();
        groundView = new GroundView(ground, mapGrid, actorsArea);
        actorsArea.prefHeightProperty().bind(mapGrid.heightProperty());
        actorsArea.prefWidthProperty().bind(mapGrid.widthProperty());
        double largeur = ground.width() * 16.0;
        double hauteur = ground.heigth() * 16.0;
        actorsArea.setMinSize(largeur, hauteur);
        actorsArea.setMaxSize(largeur, hauteur);
        env = new Environnement(ground);
        obsEnemy = new ObsEnemy(actorsArea);
        env.getEnemies().addListener(obsEnemy);
        obsKnight = new ObsKnight(actorsArea);
        env.getKnights().addListener(obsKnight);
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
                    env.unTour();
                    if (temps%10==0) {
                        obsEnemy.animate();
                        obsKnight.animate();
                    }

                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}
