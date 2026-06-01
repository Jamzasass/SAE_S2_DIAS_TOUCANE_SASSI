package universite_paris8.iut.rdias.towerdefense.model;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.view.ObsEnemy;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Environnement {

    private static int id = 1;
    private final Ground ground;
    private final ObservableList<Enemy> enemies;
    private final ObservableList<Knight> knights;
    private final ObservableList<Tower> towers;
    private static int cptSpawn = 0;
    private int delaySpawn = 60;
    private static final int[][] spanwPoints = {{4, 8}, {4, 38}, {4, 60}};


    public Environnement(Ground ground) {
        this.ground = ground;
        this.enemies = FXCollections.observableArrayList();
        this.knights = FXCollections.observableArrayList();
        this.towers = FXCollections.observableArrayList();
        int path = ground.heigth() - 1;
    }

    public void addEnemy(Enemy e){this.enemies.add(e);}
    public void delEnemy(Enemy e){this.enemies.remove(e);}
    public void addKnight(Knight k){this.knights.add(k);}
    public void delKnight(Knight k){this.knights.remove(k);}
    public void addTower(Tower t){this.towers.add(t);}
    public void delTower(Tower t){this.towers.remove(t);}

    public void unTour() {  // méthode unTour()
        cptSpawn++;
        if (cptSpawn == delaySpawn) {
            int random = (int)(Math.random() * spanwPoints.length);
            int[] spawn = spanwPoints[random];
            int line = spawn[0];
            int col = spawn[1];
            Vikings v = new Vikings(this, id, col, line);
            enemies.add(v);
            id++;
            cptSpawn = 0;
        }
        for (Enemy e : enemies) {
            e.act();
        }
        for (Tower t : towers) {
            t.act();
        }
        for (Knight k : knights) {
            k.act();
        }
        enemies.removeIf(e -> e.getX() >= ground.width() - 1);
    }

    public ObservableList<Enemy> getEnemies() {
        return enemies;
    }
    public ObservableList<Knight> getKnights() {
        return knights;
    }
    public ObservableList<Tower> getTowers() {
        return towers;
    }

    public Ground getGround() {
        return ground;
    }
}
