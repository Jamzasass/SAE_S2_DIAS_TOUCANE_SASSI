package universite_paris8.iut.rdias.towerdefense.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.*;
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
    private ObservableList<Effect> effects;
    private ArrayList<Actor> actorsDying;
    private ArrayList<Effect> effectsDying;
    private static int cptSpawn = 0;
    private int delaySpawn = 60;
    private static final int[][] spanwPoints = {{4, 8}, {4, 38}, {4, 60}};
    private IntegerProperty balance;
    private Settings settings;
    private IntegerProperty waveIndex;
    private Wave wave;


    public Environnement(Ground ground) {
        this.ground = ground;
        this.castle = new Castle();
        this.enemies = FXCollections.observableArrayList();
        this.knights = FXCollections.observableArrayList();
        this.towers = FXCollections.observableArrayList();
        this.effects = FXCollections.observableArrayList();
        this.actorsDying = new ArrayList<>();
        this.effectsDying = new ArrayList<>();
        this.balance = new SimpleIntegerProperty(10000);
        this.waveIndex = new SimpleIntegerProperty(0);
        this.settings = new Settings();
        this.wave = new Wave(this, waveIndex.get());
    }

    public void addEnemy(Enemy e){this.enemies.add(e);}
    public void delEnemy(Enemy e){this.enemies.remove(e);}
    public void addKnight(Knight k){this.knights.add(k);}
    public void delKnight(Knight k){this.knights.remove(k);}
//    public void addTower(Tower t){this.towers.add(t);}
    public boolean addTower(Tower t) {
        int line = (int) t.getY();
        int col = (int) t.getX();
        if (balance.get() < t.getCost()) {
            return false;
        }
        if (!t.canBePlaced(line, col)) {
            return false;
        }
        if (countSameType(t) >= t.maxAllowed()) {
            return false;
        }
        this.towers.add(t);
        return true;
    }

    private long countSameType(Tower t) { //Permet de compter le nombre de tours pour gérer le maximum de Bramble et Palissade
        return towers.stream().filter(other -> other.getClass() == t.getClass()).count();
    }

    public void delTower(Tower t){this.towers.remove(t);}
    public void addDyingActor(Actor a) {
        this.actorsDying.add(a);
    }
    public void takeDmgCastle(int dmg) {
        castle.takeDamage(dmg);
    }
    public void addArcher(int col, int line) {
        Tower archer = new Archer(this, id, (double) col, (double) line);
        if (this.addTower(archer)) {
            balance.setValue(balance.getValue() - archer.getCost());
        }
    }
    public void addBarrack(int col, int line) {
        Tower barrack = new Barrack(this, id, (double) col, (double) line);
        if (this.addTower(barrack)){
            balance.setValue(balance.getValue() - barrack.getCost());
        }
    }

    public void addBallista(int col, int line){
        Tower ballista = new Ballista(this, id, (double) col, (double) line);
        if (this.addTower(ballista)){
            balance.setValue(balance.getValue() - ballista.getCost());
        }
    }

    public void addBramble(int col, int line){
        Tower bramble = new Bramble(this, id, (double) col, (double) line);
        if (this.addTower(bramble)){
            balance.setValue(balance.getValue() - bramble.getCost());
        }
    }

    public void addPalissade(int col, int line){
        Tower palissade = new Palissade(this, id, (double) col, (double) line);
        if (this.addTower(palissade)){
            balance.setValue(balance.getValue() - palissade.getCost());
        }
    }

    public void addSorcerer(int col, int line){
        Tower sorcerer = new SorcererTower(this, getId(),col, line);
        if (this.addTower(sorcerer)){
            balance.setValue(balance.getValue() - sorcerer.getCost());
        }
    }
    public void addEffect(Effect e) {
        effects.add(e);
    }
    public void delEffect(Effect e) {
        effectsDying.add(e);
    }
    public void killEffect(Effect e) {
        effects.remove(e);
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
        for (Effect ef : effects) {
            ef.act();
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
        for (Effect e : effectsDying) {
            killEffect(e);
        }
        effectsDying.clear();
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
    public ObservableList<Effect> getEffects() {
        return effects;
    }
    public Castle getCastle() {
        return castle;
    }

    public Ground getGround() {
        return ground;
    }
    public int getId() {
        incrementId();
        return id;
    }
    public Settings getSettings() {
        return settings;
    }
    public void incrementId() {
        id++;
    }
    public void nextWave() {
        waveIndex.set(waveIndex.get() + 1);
        wave = new Wave(this, waveIndex.get());
        enemies.clear();
        knights.clear();
    }

    public IntegerProperty getWaveIndexProperty() {
        return waveIndex;
    }

    public void soldTower(Tower t) {
        t.sold();
    }

    public boolean upgradeTower(Tower t) {
        if (!t.canBeUpgraded()) return false;
        if (balance.get() < t.getUpgradeCost()) return false;
        balance.setValue(balance.getValue() - t.getUpgradeCost());
        t.upgrade();
        return true;
    }
}
