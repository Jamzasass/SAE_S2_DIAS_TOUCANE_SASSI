package universite_paris8.iut.rdias.towerdefense.view;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.rdias.towerdefense.model.Actor;
import universite_paris8.iut.rdias.towerdefense.model.Enemy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObsEnemy implements ListChangeListener<Enemy> {
    private ArrayList<Image> enemiesSprite;
    private Pane grid;

    public ObsEnemy(Pane oGrid) {
        this.grid = oGrid;
        this.enemiesSprite = new ArrayList<>();
    }

    @Override
    public void onChanged(Change<? extends Enemy> change) {
        while (change.next()) {
            List<Enemy> ajout = (List<Enemy>) change.getAddedSubList();
            List<Enemy> retirer = (List<Enemy>) change.getRemoved();

            for (Enemy e : ajout) {


                Circle c = new Circle(16, Color.RED);
                c.layoutXProperty().bind(e.getXProperty().multiply(16).add(16/2.0));
                c.layoutYProperty().bind(e.getYProperty().multiply(16).add(16/2.0));
                grid.getChildren().add(c);
            }
        }
    }


}
