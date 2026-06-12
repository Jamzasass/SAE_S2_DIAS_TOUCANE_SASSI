package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.view.SoldierView;
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
                grid.getChildren().add(tv.getPaneViewContainer());
                towerSprite.add(tv);
            }
            for (Tower t : retirer) {
                grid.getChildren().remove(grid.lookup("#" + "t" + t.getId()));
            }
        }
    }

    public ArrayList<TowerView> getTowerSprite() {
        return towerSprite;
    }

    public void animate() {
        for (TowerView t : towerSprite) {
            try {
                t.switchImage();
            } catch (Exception ex) {
                System.out.println("  EXCEPTION: " + ex.getMessage());
            }

        }
    }
}
