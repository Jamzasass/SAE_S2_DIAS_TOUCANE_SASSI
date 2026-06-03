package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Archer;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Barrack;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.algorithm.BFS;
import universite_paris8.iut.rdias.towerdefense.view.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.rdias.towerdefense.view.DistanceView;

public class Controller {

    @FXML
    private TilePane mapGrid;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label hpPlayer;
    private Ground ground;
    private GroundView groundView;
    @FXML
    private Pane actorsArea;
    private Timeline gameLoop;
    public static int temps;
    private Environnement env;
    private ObsEnemy obsEnemy;
    private ObsKnight obsKnight;
    private ObsTower obsTower;

    private BFS bfs;

    private static int towerSelected;

    public void initialize() {

        ground = new Ground();
        env = new Environnement(ground);
        groundView = new GroundView(ground, mapGrid, actorsArea, env);
        actorsArea.prefHeightProperty().bind(mapGrid.heightProperty());
        actorsArea.prefWidthProperty().bind(mapGrid.widthProperty());
        double largeur = ground.width() * 16.0;
        double hauteur = ground.heigth() * 16.0;
        actorsArea.setMinSize(largeur, hauteur);
        actorsArea.setMaxSize(largeur, hauteur);

        obsEnemy = new ObsEnemy(actorsArea);
        env.getEnemies().addListener(obsEnemy);
        obsKnight = new ObsKnight(actorsArea);
        env.getKnights().addListener(obsKnight);
        obsTower = new ObsTower(actorsArea);
        env.getTowers().addListener(obsTower);
        groundView.drawMap();

        balanceLabel.textProperty().bind(env.getBalanceProperty().asString());
        hpPlayer.textProperty().bind(env.getCastle().getHpPlayerProperty().asString());

        bfs = new BFS(ground);
        DistanceView n = new DistanceView(ground, bfs.getDistancesMap(), 4, 8);
        n.show();

        //définition et démarrage d'un gameloop qui fait env.unTour()
        initAnimation();
        keySelectionInit();
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
                (ev -> {
                    env.unTour();
                    if (temps % 10 == 0) {
                        obsEnemy.animate();
                        obsKnight.animate();
                    }

                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void keySelectionInit() {
        towerSelected = 1;
        mapGrid.setOnMouseClicked(e -> {
            javafx.geometry.Point2D local = mapGrid.sceneToLocal(e.getSceneX(), e.getSceneY());
            int col = (int)(local.getX() / 16);
            int line = (int)(local.getY() / 16);
            if (line < 0 || line >= ground.heigth() || col < 0 || col >= ground.width()) {
                System.out.println("hors map");
            }
            else {
                mouseClikedTile(e, line, col);
            }
        });
        actorsArea.setFocusTraversable(true);
        actorsArea.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                towerSelected = 1;
            }
            else if (e.getCode() == KeyCode.Z) {
                towerSelected = 2;
            }
        });
    }
    public void mouseClikedTile(MouseEvent e, int line, int col) {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            //ground.setTile(line, col, 3);
            //drawMap();
            Tower t;
            if (towerSelected == 1) {
                 t = new Archer(env, 1, col, line);
            }
            else {
                t = new Barrack(env, 1, col, line);
            }
            if (t != null)
                env.addTower(t);
        }
        else if (e.getButton().equals(MouseButton.MIDDLE)) {
            ground.setTile(line, col, 1);
            groundView.drawMap();
        }
        else {
            ground.setTile(line, col, 0);
            groundView.drawMap();
        }
    }

    //full brouillon
    public int getTemps() {
        return temps;
    }

}
