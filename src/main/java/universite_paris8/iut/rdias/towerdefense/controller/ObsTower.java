package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.view.TowerView;

import java.util.ArrayList;
import java.util.List;

public class ObsTower implements ListChangeListener<Tower> {
    private ArrayList<TowerView> towerSprite;
    private Pane grid;

    public ObsTower(Pane oGrid) {
        this.grid = oGrid;
        this.towerSprite = new ArrayList<>();
    }

    @Override
    public void onChanged(Change<? extends Tower> change) {
        while (change.next()) {
            List<Tower> ajout = (List<Tower>) change.getAddedSubList();
            List<Tower> retirer = (List<Tower>) change.getRemoved();

            for (Tower t : ajout) {
                TowerView tv = new TowerView(t);
                grid.getChildren().add(tv.getImage());
                if (tv.getHpBarFirst() != null) {
                    grid.getChildren().add(tv.getHpBarFirst());
                    grid.getChildren().add(tv.getHpBarSecond());
                }
                towerSprite.add(tv);
            }
            for (Tower t : retirer) {
                grid.getChildren().remove(grid.lookup("#" + "t" + t.getId()));
                grid.getChildren().remove(grid.lookup("#" + "tFB" + t.getId()));
                grid.getChildren().remove(grid.lookup("#" + "tSB" + t.getId()));
            }
        }
    }

    public ArrayList<TowerView> getTowerSprite() {
        return towerSprite;
    }

}
