package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.Effect;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.ZoneSpell;
import universite_paris8.iut.rdias.towerdefense.view.EffectView;

import java.util.ArrayList;
import java.util.List;

public class ObsEffect implements ListChangeListener<Effect> {
    private ArrayList<EffectView> effectSprite;
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
                EffectView s = new EffectView(e);
                grid.getChildren().add(s.getImage());
                effectSprite.add(s);
            }
            for (Effect e : retirer) {
                if (e instanceof ZoneSpell) {
                    for (EffectView ef : effectSprite) {
                        if (ef.getId() == e.getId()) {
                            ef.blow();
                        }
                    }
                }
                else {
                    grid.getChildren().remove(grid.lookup("#" + "e" + e.getId()));
                }

            }
        }
    }

    public ArrayList<EffectView> getEnemiesSprite() {
        return effectSprite;
    }
}
