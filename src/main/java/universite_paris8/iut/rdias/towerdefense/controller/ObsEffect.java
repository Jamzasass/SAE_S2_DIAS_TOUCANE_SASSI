package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.Effect;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.view.ProjectileView;
import universite_paris8.iut.rdias.towerdefense.view.SoldierView;

import java.util.ArrayList;
import java.util.List;

public class ObsEffect implements ListChangeListener<Effect> {
    private ArrayList<ProjectileView> effectSprite;
    private Pane grid;

    public ObsEffect(Pane oGrid) {
        this.grid = oGrid;
        this.effectSprite = new ArrayList<>();
    }

//    public void animate() {
//        for (SoldierView s : effectSprite) {
//            try {
//                s.switchImage();
//            } catch (Exception ex) {
//                System.out.println("  EXCEPTION: " + ex.getMessage());
//            }
//
//        }
//    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Effect> change) {
        while (change.next()) {
            List<Effect> ajout = (List<Effect>) change.getAddedSubList();
            List<Effect> retirer = (List<Effect>) change.getRemoved();

            for (Effect e : ajout) {
                ProjectileView s = new ProjectileView((Projectile) e);
                grid.getChildren().add(s.getImage());
                effectSprite.add(s);
            }
            for (Effect e : retirer) {
                grid.getChildren().remove(grid.lookup("#" + "e" + e.getId()));
            }
        }
    }

    public ArrayList<ProjectileView> getEnemiesSprite() {
        return effectSprite;
    }
}
