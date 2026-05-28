package universite_paris8.iut.rdias.towerdefense.view;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.rdias.towerdefense.model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObsKnight implements ListChangeListener<Knight> {
    private ArrayList<SoldierView> knightSprite;
    private Pane grid;

    public ObsKnight(Pane oGrid) {
        this.grid = oGrid;
        this.knightSprite = new ArrayList<>();
    }

    public void animate() {
        for (SoldierView s : knightSprite) {
            s.switchImage();
        }
    }

    @Override
    public void onChanged(Change<? extends Knight> change) {
        while (change.next()) {
            List<Knight> ajout = (List<Knight>) change.getAddedSubList();
            List<Knight> retirer = (List<Knight>) change.getRemoved();

            for (Knight k : ajout) {
                SoldierView s = new SoldierView(k);
                grid.getChildren().add(s.getImage());
                grid.getChildren().add(s.getPvBar());
                knightSprite.add(s);
            }
            for (Knight k : retirer) {
                grid.getChildren().remove(grid.lookup("#" + k.getId() + ""));
            }
        }
    }

    public ArrayList<SoldierView> getKnightSprite() {
        return knightSprite;
    }


}
