package universite_paris8.iut.rdias.towerdefense.view;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.*;

import java.util.ArrayList;
import java.util.List;

public class ObsActor implements ListChangeListener<Actor> {
    private ArrayList<SoldierView> soldierSprite;
    private ArrayList<TowerView> towerSprite;
    private Pane grid;

    public ObsActor(Pane oGrid) {
        this.grid = oGrid;
        this.soldierSprite = new ArrayList<>();
        this.towerSprite = new ArrayList<>();
    }

    public void animate() {
        for (SoldierView s : soldierSprite) {
            try {
                s.switchImage();
            } catch (Exception ex) {
                System.out.println("  EXCEPTION: " + ex.getMessage());
            }
        }
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Actor> change) {
        while (change.next()) {
            List<Actor> ajout = (List<Actor>) change.getAddedSubList();
            List<Actor> retirer = (List<Actor>) change.getRemoved();

            for (Actor a : ajout) {
                if (a instanceof Soldier) {
                    SoldierView sv = new SoldierView((Soldier) a);
                    grid.getChildren().add(sv.getImage());
                    grid.getChildren().add(sv.getPvBar());
                    soldierSprite.add(sv);
                }
                else if (a instanceof Tower) {
                    TowerView tv = new TowerView((Tower) a);
                    grid.getChildren().add(tv.getImage());
                    towerSprite.add(tv);
                }
            }
            for (Actor a : retirer) {
                if (a instanceof Enemy)
                    grid.getChildren().remove(grid.lookup("#" + "v" + a.getId()));
                else if (a instanceof Knight)
                    grid.getChildren().remove(grid.lookup("#" + "k" + a.getId()));
                else if (a instanceof Tower)
                    grid.getChildren().remove(grid.lookup("#" + "t" + a.getId()));
            }
        }
    }

    public ArrayList<SoldierView> getSoldierSprite() {
        return soldierSprite;
    }
}
