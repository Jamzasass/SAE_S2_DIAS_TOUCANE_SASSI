package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.algorithm.BFS;
import universite_paris8.iut.rdias.towerdefense.view.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.rdias.towerdefense.view.DistanceView;
import javafx.scene.control.ProgressBar;

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
    private CastleView castleView;
    @FXML private HBox towerActionMenu;
    @FXML private Button btnSellCancel;
    @FXML private Button btnUpgradePut;
    @FXML private Button archerTower;
    @FXML private Button barrackTower;
    @FXML private Button brambleTower;
    @FXML private Button palissadeTower;
    @FXML private Button sorcererTower;
    @FXML private Button ballistaTower;

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
        hpBar.progressProperty().bind(
                env.getCastle().getHpPlayerProperty().divide((double) env.getCastle().getMaxHp())
        );

        castleView = new CastleView(env.getCastle());
        ImageView c = new ImageView();
        c.setFitHeight(64);
        c.setFitWidth(64);
        c.setLayoutX(39*16);
        c.setLayoutY((40*16)-5);
        c.imageProperty().bind(castleView.getCastleImage().imageProperty());
        actorsArea.getChildren().add(c);


        bfs = new BFS(ground);
        DistanceView n = new DistanceView(ground, bfs.getDistancesMap(), 4, 8);
        n.show();

        //définition et démarrage d'un gameloop qui fait env.unTour()
        initAnimation();
        keySelectionInit();
        gameLoop.play();

        archerTower.setOnAction(e -> towerSelected = 1);
        barrackTower.setOnAction(e -> towerSelected = 2);


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
                    env.loop();
                    if (temps % 10 == 0) {
                        obsEnemy.animate();
                        obsKnight.animate();
                    }
                    if (temps %15 == 0) {
                        castleView.switchImage();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void keySelectionInit() {
        towerSelected = 0;
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
            else if (e.getCode() == KeyCode.E){
                towerSelected = 3;
            }
            else if (e.getCode() == KeyCode.R){
                towerSelected = 4;
            }
            else if (e.getCode() == KeyCode.T){
                towerSelected = 5;
            }
        });
    }

    public void mouseClikedTile(MouseEvent e, int line, int col) {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            for (Tower t : env.getTowers()) {
                if ((int)t.getX() == col && (int)t.getY() == line) {
                    btnSellCancel.setText("Vendre (" + t.getCost() / 2 + " 💰)");
                    btnUpgradePut.setText("Améliorer");
                    btnSellCancel.setOnAction(ev -> {
                        t.sold();
                        towerActionMenu.setVisible(false);
                    });
                    btnUpgradePut.setOnAction(ev -> {
                        t.upgrade();
                        towerActionMenu.setVisible(false);
                    });
                    towerActionMenu.setVisible(true);
                    return;
                }
            }
            if (towerSelected == 1 || towerSelected == 2) {
                final int fcol = col;
                final int fline = line;
                btnUpgradePut.setText("✔");
                btnSellCancel.setText("✘");
                btnUpgradePut.setOnAction(ev -> {
                if (towerSelected == 1){
                    env.addArcher(fcol, fline);
                }
                else if(towerSelected == 2){
                    env.addBarrack(fcol, fline);
                }
                else if (towerSelected == 3) {
                    env.addBallista(fcol, fline);
                }
                else if (towerSelected == 4){
                    env.addBramble(fcol, fline);
                }
                else {
                    env.addPalissade(fcol, fline);
                }
                towerSelected = 0;
                towerActionMenu.setVisible(false);
                btnSellCancel.setText("Vendre");
                btnUpgradePut.setText("Améliorer");
                });
                btnSellCancel.setOnAction(ev -> {
                    towerSelected = 0;
                    towerActionMenu.setVisible(false);
                    btnSellCancel.setText("Vendre");
                    btnUpgradePut.setText("Améliorer");
                });
                towerActionMenu.setVisible(true);

            }
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

    @FXML
    private ProgressBar hpBar;

    //full brouillon
    public int getTemps() {
        return temps;
    }

}
