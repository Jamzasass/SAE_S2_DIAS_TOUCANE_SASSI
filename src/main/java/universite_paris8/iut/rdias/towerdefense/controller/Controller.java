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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.rdias.towerdefense.model.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.algorithm.BFS;
import universite_paris8.iut.rdias.towerdefense.view.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.ProgressBar;
import universite_paris8.iut.rdias.towerdefense.view.DistanceView;

public class Controller {

    //  Constants
    private static final int TILE_SIZE = 16;
    private static final int TOWER_ARCHER = 1;
    private static final int TOWER_BARRACK = 2;
    private static final int TOWER_BRAMBLE = 3;
    private static final int TOWER_PALISSADE = 4;
    private static final int TOWER_SORCERER = 5;
    private static final int TOWER_BALLISTA = 6;

    // Fxml
    @FXML private TilePane mapGrid;
    @FXML private Label balanceLabel;
    @FXML private Label hpPlayer;
    @FXML private Label waveLabel;
    @FXML private Pane actorsArea;
    @FXML private HBox towerActionMenu;
    @FXML private Button btnSell;
    @FXML private Button btnUpgrade;
    @FXML private Button archerTower;
    @FXML private Button barrackTower;
    @FXML private Button brambleTower;
    @FXML private Button palissadeTower;
    @FXML private Button sorcererTower;
    @FXML private Button ballistaTower;
    @FXML private ProgressBar hpBar;

    // Model
    private Ground ground;
    private Environnement env;

    // View
    private GroundView groundView;
    private CastleView castleView;
    private ObsEnemy obsEnemy;
    private ObsKnight obsKnight;
    private ObsTower obsTower;
    private ObsEffect obsEffect;

    private Timeline gameLoop;
    public static int temps;

    // Tower sprites
    private Image spriteArcher;
    private Image spriteBarrack;
    private Image spriteBallista;
    private Image spriteBramble;
    private Image spritePalissade;
    private Image spriteSorcerer;

    // Drag and drop state
    private ImageView ghostImage;
    private Circle rangeCircle;
    private int towerSelected = 0;

    public void initialize() {
        loadTowerSprites();
        setupModel();
        setupView();
        bindProperties();
        setupEventHandlers();
        startGameLoop();
    }

    // Initialization

    private void loadTowerSprites() {
        spriteArcher = new Image(GroundView.class.getResourceAsStream(
                "/universite_paris8/iut/rdias/towerdefense/sprite/tower/archer_tower/sprite_ArcherTowerNiv1_1.png"));
        spriteBarrack = new Image(GroundView.class.getResourceAsStream(
                "/universite_paris8/iut/rdias/towerdefense/sprite/tower/barrack_tower/sprite_BarrackTower1.png"));
        spriteBallista = new Image(GroundView.class.getResourceAsStream(
                "/universite_paris8/iut/rdias/towerdefense/sprite/tower/tower_ballista/sprite_BallistaTower1.png"));
        spriteBramble = new Image(GroundView.class.getResourceAsStream(
                "/universite_paris8/iut/rdias/towerdefense/sprite/tower/bramble_tower/sprite_BrambleTower4.png"));
        spritePalissade = new Image(GroundView.class.getResourceAsStream(
                "/universite_paris8/iut/rdias/towerdefense/sprite/tower/palissade_tower/sprite_PalissadeTower1.png"));
        spriteSorcerer = new Image(GroundView.class.getResourceAsStream(
                "/universite_paris8/iut/rdias/towerdefense/sprite/tower/wizard_tower/sprite_SorcererTower1.png"));
    }

    private void setupModel() {
        ground = new Ground();
        env = new Environnement(ground);
    }

    private void setupView() {
        groundView = new GroundView(ground, mapGrid, actorsArea, env);

        actorsArea.prefHeightProperty().bind(mapGrid.heightProperty());
        actorsArea.prefWidthProperty().bind(mapGrid.widthProperty());
        double largeur = ground.width() * TILE_SIZE;
        double hauteur = ground.heigth() * TILE_SIZE;
        actorsArea.setMinSize(largeur, hauteur);
        actorsArea.setMaxSize(largeur, hauteur);

        obsEnemy = new ObsEnemy(actorsArea);
        env.getEnemies().addListener(obsEnemy);
        obsKnight = new ObsKnight(actorsArea);
        env.getKnights().addListener(obsKnight);
        obsTower = new ObsTower(actorsArea);
        env.getTowers().addListener(obsTower);
        obsEffect = new ObsEffect(actorsArea);
        env.getEffects().addListener(obsEffect);
        groundView.drawMap();

        castleView = new CastleView(env.getCastle());
        ImageView c = new ImageView();
        c.setFitHeight(64);
        c.setFitWidth(64);
        c.setLayoutX(39 * TILE_SIZE);
        c.setLayoutY((40 * TILE_SIZE) - 5);
        c.imageProperty().bind(castleView.getCastleImage().imageProperty());
        actorsArea.getChildren().add(c);

        BFS bfs = new BFS(ground);
        DistanceView n = new DistanceView(ground, bfs.getDistancesMap(), 4, 8);
        n.show();
    }

    private void bindProperties() {
        balanceLabel.textProperty().bind(env.getBalanceProperty().asString());
        hpPlayer.textProperty().bind(env.getCastle().getHpPlayerProperty().asString());
        hpBar.progressProperty().bind(
                env.getCastle().getHpPlayerProperty().divide((double) env.getCastle().getMaxHp())
        );
        waveLabel.textProperty().bind(env.getWaveIndexProperty().asString());
    }

    private void setupEventHandlers() {
        setupTowerButtons();
        setupMapClick();
        setupKeyboard();
        setupGameOver();
    }

    private void setupTowerButtons() {
        archerTower.setOnAction(e -> startDrag(spriteArcher, TOWER_ARCHER));
        barrackTower.setOnAction(e -> startDrag(spriteBarrack, TOWER_BARRACK));
        brambleTower.setOnAction(e -> startDrag(spriteBramble, TOWER_BRAMBLE));
        palissadeTower.setOnAction(e -> startDrag(spritePalissade, TOWER_PALISSADE));
        sorcererTower.setOnAction(e -> startDrag(spriteSorcerer, TOWER_SORCERER));
        ballistaTower.setOnAction(e -> startDrag(spriteBallista, TOWER_BALLISTA));
    }

    private void setupMapClick() {
        mapGrid.setOnMouseClicked(this::handleMapClick);
    }

    private void setupKeyboard() {
        actorsArea.setFocusTraversable(true);
        actorsArea.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) towerSelected = TOWER_ARCHER;
            else if (e.getCode() == KeyCode.Z) towerSelected = TOWER_BARRACK;
            else if (e.getCode() == KeyCode.E) towerSelected = TOWER_BRAMBLE;
            else if (e.getCode() == KeyCode.R) towerSelected = TOWER_PALISSADE;
            else if (e.getCode() == KeyCode.T) towerSelected = TOWER_SORCERER;
            else if (e.getCode() == KeyCode.Y) towerSelected = TOWER_BALLISTA;
        });
    }

    private void setupGameOver() {
        env.getCastle().getHpPlayerProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() <= 0) {
                gameLoop.stop();
                showGameOver();
            }
        });
    }

    // Game Loop

    private void startGameLoop() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    env.loop();
                    if (temps % 10 == 0) {
                        obsEnemy.animate();
                        obsKnight.animate();
                    }
                    if (temps % 15 == 0) {
                        castleView.switchImage();
                        obsTower.animate();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }

    // mouse handling

    private void handleMapClick(MouseEvent e) {
        Point2D local = mapGrid.sceneToLocal(e.getSceneX(), e.getSceneY());
        int col = (int)(local.getX() / TILE_SIZE);
        int line = (int)(local.getY() / TILE_SIZE);

        if (!isValidTile(line, col)) {
            return;
        }

        if (!e.getButton().equals(MouseButton.PRIMARY)) {
            return;
        }

        // Check if clicking on existing tower
        Tower tower = findTowerAt(col, line);
        if (tower != null) {
            showTowerMenu(tower);
            return;
        }

        // Place new tower
        if (towerSelected > 0) {
            addTower(towerSelected, col, line);
            towerSelected = 0;
            stopDrag();
        }
    }

    private Tower findTowerAt(int col, int line) {
        for (Tower t : env.getTowers()) {
            if ((int)t.getX() == col && (int)t.getY() == line) {
                return t;
            }
        }
        return null;
    }

    private void showTowerMenu(Tower tower) {
        btnSell.setText("Sell (" + tower.getCost() / 2 + " 💰)");

        if (tower.canBeUpgraded()) {
            btnUpgrade.setText("Upgrade (" + tower.getUpgradeCost() + " 💰)");
            btnUpgrade.setDisable(false);
        } else {
            btnUpgrade.setText("Max");
            btnUpgrade.setDisable(true);
        }

        btnSell.setOnAction(ev -> {
            tower.sold();
            towerActionMenu.setVisible(false);
        });
        btnUpgrade.setOnAction(ev -> {
            if (!env.upgradeTower(tower)) {
                System.out.println("Upgrade impossible");
            }
            towerActionMenu.setVisible(false);
        });
        towerActionMenu.setVisible(true);
    }

    private boolean isValidTile(int line, int col) {
        return !(line < 0 || line >= ground.heigth() || col < 0 || col >= ground.width());
    }

    // Drag And Drop

    private void startDrag(Image sprite, int type) {
        towerSelected = type;
        stopDrag();

        ghostImage = new ImageView(sprite);
        ghostImage.setFitWidth(32);
        ghostImage.setFitHeight(32);
        ghostImage.setOpacity(0.5);
        ghostImage.setMouseTransparent(true);

        double range = getTowerRange(type);

        rangeCircle = new Circle(range);
        rangeCircle.setFill(Color.TRANSPARENT);
        rangeCircle.setStroke(Color.BLACK);
        rangeCircle.setStrokeWidth(1.5);
        rangeCircle.setOpacity(0.6);
        rangeCircle.setMouseTransparent(true);

        actorsArea.getChildren().addAll(rangeCircle, ghostImage);
        mapGrid.setOnMouseMoved(this::updateDragPosition);
    }

    private void updateDragPosition(MouseEvent e) {
        Point2D local = mapGrid.sceneToLocal(e.getSceneX(), e.getSceneY());
        int col = (int)(local.getX() / TILE_SIZE);
        int line = (int)(local.getY() / TILE_SIZE);
        double cx = col * TILE_SIZE + TILE_SIZE / 2;
        double cy = line * TILE_SIZE + TILE_SIZE / 2;

        ghostImage.setLayoutX(cx - 16);
        ghostImage.setLayoutY(cy - 16);
        rangeCircle.setCenterX(cx);
        rangeCircle.setCenterY(cy);
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

    private double getTowerRange(int towerType) {
        if (towerType == TOWER_ARCHER) {
            return env.getSettings().getArcherRange() * TILE_SIZE;
        }
        else if (towerType == TOWER_SORCERER) {
            return env.getSettings().getSorcererRange() * TILE_SIZE;
        }
        else if (towerType == TOWER_BALLISTA) {
            return env.getSettings().getBallistaRange() * TILE_SIZE;
        }
        return 0;
    }

    // Tower Placement

    private void addTower(int towerType, int col, int line) {
        if (towerType == TOWER_ARCHER) {
            env.addArcher(col, line);
        }
        else if (towerType == TOWER_BARRACK) {
            env.addBarrack(col, line);
        }
        else if (towerType == TOWER_BRAMBLE) {
            env.addBramble(col, line);
        }
        else if (towerType == TOWER_PALISSADE) {
            env.addPalissade(col, line);
        }
        else if (towerType == TOWER_SORCERER) {
            env.addSorcerer(col, line);
        }
        else if (towerType == TOWER_BALLISTA) {
            env.addBallista(col, line);
        }
    }

    // Game Over

    private void showGameOver() {
        int wave = env.getWaveIndexProperty().get();
        String message = getGameOverMessage(wave);

        VBox gameOverPane = new VBox(20);
        gameOverPane.setAlignment(javafx.geometry.Pos.CENTER);
        gameOverPane.setStyle("-fx-background-color: rgba(0,0,0,0.7);");
        gameOverPane.prefWidthProperty().bind(actorsArea.widthProperty());
        gameOverPane.prefHeightProperty().bind(actorsArea.heightProperty());

        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: red; -fx-font-family: 'Black Clover Font';");

        Label waveReached = new Label("Vague atteinte : " + wave);
        waveReached.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-family: 'Black Clover Font';");

        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #f0c040; -fx-font-family: 'Black Clover Font';");

        gameOverPane.getChildren().addAll(gameOverLabel, waveReached, messageLabel);
        actorsArea.getChildren().add(gameOverPane);
    }

    private String getGameOverMessage(int wave) {
        if (wave <= 2) {
            return "Tu fais honte au roi. Au bûcher !!!";
        } else if (wave <= 5) {
            return "Pff. T'es mauvais ! ";
        } else if (wave <= 10) {
            return "Mmmmh. Va lire l'art de la Guerre pour t'améliorer.";
        } else if (wave <= 15) {
            return "Bien ! Mais tu dois améliorer ta tactique.";
        } else {
            return "Tu as été promu en tant que tacticien de l'armée du Roi. Chapeau l'artiste.";
        }
    }

    public int getTemps() {
        return temps;
    }
}