package universite_paris8.iut.rdias.towerdefense.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Archer;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Barrack;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.Viking;
import java.util.ArrayList;

public class Environnement {

    private static int id = 1;
    private Ground ground;
    private Castle castle;
    private ObservableList<Enemy> enemies;
    private ObservableList<Knight> knights;
    private ObservableList<Tower> towers;
    private ArrayList<Actor> actorsDying;
    private static int cptSpawn = 0;
    private int delaySpawn = 60;
    private static final int[][] spanwPoints = {{4, 8}, {4, 38}, {4, 60}};
    private IntegerProperty balance;
    private Settings settings;
    private int waveIndex;
    private Wave wave;


    public Environnement(Ground ground) {
        this.ground = ground;
        this.castle = new Castle();
        this.enemies = FXCollections.observableArrayList();
        this.knights = FXCollections.observableArrayList();
        this.towers = FXCollections.observableArrayList();
        this.actorsDying = new ArrayList<>();
        this.balance = new SimpleIntegerProperty(0);
        this.waveIndex = 0;
        this.settings = new Settings();
        this.wave = new Wave(this, waveIndex, 2, 5);
    }

    public void addEnemy(Enemy e){this.enemies.add(e);}
    public void delEnemy(Enemy e){this.enemies.remove(e);}
    public void addKnight(Knight k){this.knights.add(k);}
    public void delKnight(Knight k){this.knights.remove(k);}
    public void addTower(Tower t){this.towers.add(t);}
    public void delTower(Tower t){this.towers.remove(t);}
    public void addDyingActor(Actor a) {
        this.actorsDying.add(a);
    }
    public void takeDmgCastle(int dmg) {
        castle.takeDamage(dmg);
    }
    public void addArcher(int col, int line) {
        Tower archer = new Archer(this, settings.getArcherHp(), settings.getArcherDmg(), id, (double) col, (double) line, settings.getArcherSpeedAttack(), settings.getArcherCost());
        this.addTower(archer);
        balance.setValue(balance.getValue() - archer.getCost());
    }
    public void addBarrack(int col, int line) {
        Tower barrack = new Barrack(this, settings.getArcherHp(), id, (double) col, (double) line, settings.getBarrackSpeedProduction(), settings.getBarrackCost());
        this.addTower(barrack);
        balance.setValue(balance.getValue() - barrack.getCost());
    }
    public void earn(int gain) {
        balance.setValue(balance.getValue() + gain);
    }
    public IntegerProperty getBalanceProperty() {
        return balance;
    }

    public void loop() {  // méthode unTour()
        cptSpawn++;
        wave.waveLoop(cptSpawn);
        for (Knight k : knights) {
            k.act();
        }
        for (Tower t : towers) {
            t.act();
        }
        for (Enemy e : enemies) {
            e.act();
        }

        for (Actor a : actorsDying) {
            if (a instanceof Enemy) {
                delEnemy((Enemy) a);
            }
            else if (a instanceof Tower) {
                delTower((Tower) a);
            }
            else if (a instanceof Knight) {
                delKnight((Knight) a);
            }
        }
        actorsDying.clear();
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
    public Castle getCastle() {
        return castle;
    }

    public Ground getGround() {
        return ground;
    }
    public int getId() {
        return id;
    }
    public Settings getSettings() {
        return settings;
    }
    public void incrementId() {
        id++;
    }
    public void nextWave() {
        waveIndex++;
        wave = new Wave(this, waveIndex, 2, 5);
        enemies.clear();
        knights.clear();
    }
}
