package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    @FXML private Button btnSell;
    @FXML private Button btnUpgrade;
    @FXML private Button archerTower;
    @FXML private Button barrackTower;
    @FXML private Button brambleTower;
    @FXML private Button palissadeTower;
    @FXML private Button sorcererTower;
    @FXML private Button ballistaTower;
    private Circle rangeCircle;

    private BFS bfs;

    private static int towerSelected;

    private ImageView ghostImage;
    private static final Image spriteArcher = new javafx.scene.image.Image(Controller.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/archer_tower/sprite_ArcherTowerNiv1.png"));
    private static final Image spriteBarrack = new javafx.scene.image.Image(Controller.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/barrack_tower/sprite_BarrackTower1.png"));
    private static final Image spriteBallista = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/tower_ballista/sprite_BallistaTower1.png"));
    private static final Image spriteBramble = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/bramble_tower/sprite_BrambleTower4.png"));
    private static final Image spritePalissade = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/palissade_tower/sprite_PalissadeTower1.png"));
    private static final Image spriteSorcerer = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/wizard_tower/sprite_SorcererTower1.png"));

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

        archerTower.setOnAction(e -> startDrag(spriteArcher, 1));
        barrackTower.setOnAction(e -> startDrag(spriteBarrack, 2));
        brambleTower.setOnAction(e -> startDrag(spriteBramble, 3));
        palissadeTower.setOnAction(e -> startDrag(spritePalissade, 4));
        sorcererTower.setOnAction((e -> startDrag(spriteSorcerer, 5)));
        ballistaTower.setOnAction(e -> startDrag(spriteBallista, 6));

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
            else if (e.getCode() == KeyCode.Y){
                towerSelected = 6;
            }
        });
    }

    public void mouseClikedTile(MouseEvent e, int line, int col) {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            for (Tower t : env.getTowers()) {
                if ((int)t.getX() == col && (int)t.getY() == line) {
                    btnSell.setText("Sell (" + t.getCost() / 2 + " 💰)");

                    if (t.canBeUpgraded()) {
                        btnUpgrade.setText("Upgrade (" + t.getUpgradeCost() + " 💰)");
                        btnUpgrade.setDisable(false);
                    } else {
                        btnUpgrade.setText("Max");
                        btnUpgrade.setDisable(true);
                    }

                    btnSell.setOnAction(ev -> {
                        t.sold();
                        towerActionMenu.setVisible(false);
                    });
                    btnUpgrade.setOnAction(ev -> {
                        if (!env.upgradeTower(t)) {
                            System.out.println("Upgrade impossible");
                        }
                        towerActionMenu.setVisible(false);
                    });
                    towerActionMenu.setVisible(true);
                    return;
                }
            }

            if (towerSelected == 1){
                env.addArcher(col, line);
            }
            else if (towerSelected == 2){
                env.addBarrack(col, line);
            }
            else if (towerSelected == 3){
                env.addBramble(col, line);
            }
            else if (towerSelected == 4){
                env.addPalissade(col, line);
            }
            else if (towerSelected == 5 ){
                env.addSorcerer(col, line);
            }
            else if (towerSelected == 6){
                env.addBallista(col, line);
            }
            towerSelected = 0;
            stopDrag();

        }
    }

    @FXML
    private ProgressBar hpBar;

    //full brouillon
    public int getTemps() {
        return temps;
    }

    private void startDrag(Image sprite, int type) {
        towerSelected = type;
        stopDrag();
        ghostImage = new ImageView(sprite);
        ghostImage.setFitWidth(32);
        ghostImage.setFitHeight(32);
        ghostImage.setOpacity(0.5);
        ghostImage.setMouseTransparent(true);

        double range = 0;
        if (type == 1){
            range = env.getSettings().getArcherRange() * 16;
        }
        else if(type == 5 ){
           range = env.getSettings().getSorcererRange() * 16;
        }
        else if (type == 6){
            range = env.getSettings().getBallistaRange() * 16;
        }

        rangeCircle = new Circle(range);
        rangeCircle.setFill(Color.TRANSPARENT);
        rangeCircle.setStroke(Color.BLACK);
        rangeCircle.setStrokeWidth(1.5);
        rangeCircle.setOpacity(0.6);
        rangeCircle.setMouseTransparent(true);

        actorsArea.getChildren().addAll(rangeCircle, ghostImage);

        mapGrid.setOnMouseMoved(e -> {
            Point2D local = mapGrid.sceneToLocal(e.getSceneX(), e.getSceneY());
            int col = (int)(local.getX() / 16);
            int line = (int)(local.getY() / 16);
            double cx = col * 16 + 8;
            double cy = line * 16 + 8;
            ghostImage.setLayoutX(cx - 16);
            ghostImage.setLayoutY(cy - 16);
            rangeCircle.setCenterX(cx);
            rangeCircle.setCenterY(cy);
        });
    }

    private void stopDrag() {
        if (ghostImage != null) {
            actorsArea.getChildren().remove(ghostImage);
            ghostImage = null;
        }
        if (rangeCircle != null) {
            actorsArea.getChildren().remove(rangeCircle);
            rangeCircle = null;
        }
        mapGrid.setOnMouseMoved(null);
    }


}
