package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.Animation;
import universite_paris8.iut.rdias.towerdefense.model.Effect;
import universite_paris8.iut.rdias.towerdefense.model.ZoneSpell;
import universite_paris8.iut.rdias.towerdefense.view.AnimationView;
import universite_paris8.iut.rdias.towerdefense.view.EffectView;

import java.util.ArrayList;
import java.util.List;

public class ObsAnimation implements ListChangeListener<Animation> {
    private ArrayList<AnimationView> animationSprite;
    private Pane grid;

    public ObsAnimation(Pane oGrid) {
        this.grid = oGrid;
        this.animationSprite = new ArrayList<>();
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Animation> change) {
        while (change.next()) {
            List<Animation> ajout = (List<Animation>) change.getAddedSubList();
            List<Animation> retirer = (List<Animation>) change.getRemoved();

            for (Animation a : ajout) {
                AnimationView aV = new AnimationView(a, grid.getWidth(), grid.getHeight());
                grid.getChildren().add(aV.getPaneViewContainer());
                animationSprite.add(aV);
            }
            for (Animation e : retirer) {
                grid.getChildren().remove(grid.lookup("#" + "e" + e.getId()));
            }
        }
    }

    public ArrayList<AnimationView> getEnemiesSprite() {
        return animationSprite;
    }
}
