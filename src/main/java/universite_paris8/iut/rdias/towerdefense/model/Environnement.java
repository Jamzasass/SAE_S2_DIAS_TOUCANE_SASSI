package universite_paris8.iut.rdias.towerdefense.model;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.view.ObsEnemy;

import java.util.Collection;
import java.util.Collections;

public class Environnement {

    private static int id = 1;
    private final Ground ground;
    private final ObservableList<Enemy> enemies; // liste observable d'ennemis
    private static int cptSpawn = 0;
    private int delaySpawn = 60;


    public Environnement(Ground ground) {
        this.ground = ground;
        this.enemies = FXCollections.observableArrayList();
        int path = ground.heigth() - 1;
    }

    public void add(Enemy e){this.enemies.add(e);}

    public void unTour() {  // méthode unTour()
        cptSpawn++;
        if (cptSpawn >= delaySpawn) {
            enemies.add(new Vikings(id, 8, 4));
            id++;
            cptSpawn = 0;
        }
        for (Enemy e : enemies) {
            e.move(this);
            int random = (int)(Math.random()*30);
            if (random == 1) {
                e.changeDirection();
                System.out.println("ch");
            }
        }
        enemies.removeIf(e -> e.getX() >= ground.width() - 1);
    }

    public ObservableList<Enemy> getEnemies() {
        return enemies;
    }

    public Ground getGround() {
        return ground;
    }
}
