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
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.model.Soldier;

public class ObsEnemy implements ListChangeListener<Enemy> {
    private ArrayList<SoldierView> enemiesSprite;
    private Pane grid;

    public ObsEnemy(Pane oGrid) {
        this.grid = oGrid;
        this.enemiesSprite = new ArrayList<>();
    }

    public void animate() {
        for (SoldierView s : enemiesSprite) {
            try {
                s.switchImage();
            } catch (Exception ex) {
                System.out.println("  EXCEPTION: " + ex.getMessage());
            }

        }
    }

    @Override
    public void onChanged(Change<? extends Enemy> change) {
        while (change.next()) {
            List<Enemy> ajout = (List<Enemy>) change.getAddedSubList();
            List<Enemy> retirer = (List<Enemy>) change.getRemoved();

            for (Enemy e : ajout) {
                SoldierView s = new SoldierView(e);
                grid.getChildren().add(s.getImage());
                grid.getChildren().add(s.getPvBar());
                enemiesSprite.add(s);
            }
            for (Enemy e : retirer) {
                grid.getChildren().remove(grid.lookup("#" + e.getId()));
            }
        }
    }

    public ArrayList<SoldierView> getEnemiesSprite() {
        return enemiesSprite;
    }


}
