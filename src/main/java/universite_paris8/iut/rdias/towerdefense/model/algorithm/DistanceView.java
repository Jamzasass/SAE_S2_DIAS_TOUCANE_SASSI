package universite_paris8.iut.rdias.towerdefense.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.model.algorithm.BFS;

/**
 * Affiche dans une fenetre la carte des distances calculee par le BFS,
 * pour une tuile d'origine donnee (lOrigin, cOrigin).
 *
 * Plus une case est loin de l'origine, plus sa couleur est foncee.
 * Les cases non atteignables (distance -1) sont grisees.
 *
 * Pour brancher ca sur ton projet, il te faut juste :
 *   - exposer la distancesMap depuis BFS (voir getter plus bas)
 *   - un Ground deja construit
 */
public class DistanceView {

    private static final int CELL = 16;   // taille d'une case en pixels

    private final Ground ground;
    private final int[][][][] distancesMap;
    private int lOrigin;
    private int cOrigin;

    private Canvas canvas;
    private Label info;

    public DistanceView(Ground ground, int[][][][] distancesMap, int lOrigin, int cOrigin) {
        this.ground = ground;
        this.distancesMap = distancesMap;
        this.lOrigin = lOrigin;
        this.cOrigin = cOrigin;
    }

    /** Ouvre une fenetre independante affichant la carte. */
    public void show() {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();

        canvas = new Canvas(ground.width() * CELL, ground.heigth() * CELL);

        info = new Label();
        info.setFont(Font.font(14));
        info.setStyle("-fx-padding: 8;");

        // clic sur une case -> elle devient la nouvelle origine (si c'est un chemin)
        canvas.setOnMouseClicked(e -> {
            int col = (int) (e.getX() / CELL);
            int li  = (int) (e.getY() / CELL);
            if (li >= 0 && li < ground.heigth() && col >= 0 && col < ground.width()
                    && (ground.isPath(li, col) || ground.isCastle(li, col))) {
                lOrigin = li;
                cOrigin = col;
                refresh();
            }
        });

        root.setTop(info);
        root.setCenter(canvas);

        refresh();

        stage.setScene(new Scene(root));
        stage.setTitle("Carte des distances BFS");
        stage.show();
    }

    /** Redessine la carte et met a jour le texte pour l'origine courante. */
    private void refresh() {
        info.setText("Origine : ligne " + lOrigin + ", colonne " + cOrigin
                + "  (clique sur une case de chemin pour changer d'origine)");
        draw(canvas.getGraphicsContext2D());
    }

    private void draw(GraphicsContext gc) {
        // distance max atteignable depuis l'origine, pour normaliser le degrade
        int max = maxDistance();
        if (max == 0) max = 1; // evite division par zero

        for (int li = 0; li < ground.heigth(); li++) {
            for (int col = 0; col < ground.width(); col++) {
                int d = distancesMap[lOrigin][cOrigin][li][col];
                double x = col * CELL;
                double y = li * CELL;

                if (d < 0) {
                    // case non atteignable / non chemin
                    gc.setFill(Color.web("#2a2a2e"));
                } else {
                    // ratio 0 (proche) -> 1 (loin)
                    double t = (double) d / max;
                    // teinte qui s'assombrit : on part d'un bleu clair vers un bleu nuit
                    Color light = Color.web("#bfe3ff");
                    Color dark  = Color.web("#0a1f3d");
                    gc.setFill(light.interpolate(dark, t));
                }
                gc.fillRect(x, y, CELL, CELL);

                // grille
                gc.setStroke(Color.web("#111111"));
                gc.setLineWidth(0.5);
                gc.strokeRect(x, y, CELL, CELL);

                // numero de distance
                if (d >= 0) {
                    // texte clair sur fond fonce, fonce sur fond clair
                    double t = (double) d / max;
                    gc.setFill(t > 0.5 ? Color.WHITE : Color.web("#0a1f3d"));
                    gc.setFont(Font.font(13));
                    gc.fillText(String.valueOf(d), x + CELL / 2.0 - 5, y + CELL / 2.0 + 4);
                }

                // marque l'origine
                if (li == lOrigin && col == cOrigin) {
                    gc.setStroke(Color.web("#ff5252"));
                    gc.setLineWidth(3);
                    gc.strokeRect(x + 2, y + 2, CELL - 4, CELL - 4);
                }
            }
        }
    }

    private int maxDistance() {
        int max = 0;
        for (int li = 0; li < ground.heigth(); li++) {
            for (int col = 0; col < ground.width(); col++) {
                max = Math.max(max, distancesMap[lOrigin][cOrigin][li][col]);
            }
        }
        return max;
    }
}