package universite_paris8.iut.rdias.towerdefense.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

import java.util.ArrayList;

/*La classe Environnement représente le monde du jeu.
 * L'environnement gère toutes les entités du jeu,
 * leur ajout, leur suppression et leur cycle d'action.
 * La méthode loop() appelle act() sur toutes les entités
 * et nettoie les entités mortes à la fin de chaque tick.
 * Un environnement a comme attribut:
 * -Le terrain (Ground) sur lequel se déroule le jeu
 * -Le château (Castle) à protéger
 * -Les listes observables des ennemis, chevaliers, tours et effets
 * -Les listes des acteurs et effets mourants à supprimer
 * -Le compteur de spawn et le délai entre les spawns
 * -Les coordonnées de spawn des ennemis
 * -L'ecu total du joueur (argent)
 * -Les paramètres du jeu (Settings)
 * -L'index de la vague actuelle
 * -La vague actuelle (Wave)
 *
 */

public class Environnement {

    private static int id = 1;
    private Ground ground;
    private Castle castle;
    private ObservableList<Enemy> enemies;
    private ObservableList<Knight> knights;
    private ObservableList<Tower> towers;
    private ObservableList<Effect> effects;
    private ObservableList<Animation> animations;
    private ArrayList<Actor> actorsDying;
    private ArrayList<Effect> effectsDying;
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
        this.animations = FXCollections.observableArrayList();
        this.actorsDying = new ArrayList<>();
        this.effectsDying = new ArrayList<>();
        this.balance = new SimpleIntegerProperty(10000);
        this.waveIndex = new SimpleIntegerProperty(1);
        this.settings = new Settings();
        this.wave = new Wave(this, waveIndex.get());
    }

    public void addEnemy(Enemy e){this.enemies.add(e);}
    public void delEnemy(Enemy e){this.enemies.remove(e);}
    public void addKnight(Knight k){this.knights.add(k);}
    public void delKnight(Knight k){this.knights.remove(k);}
    public void addTower(Tower t) {
        if (t instanceof Palissade) {
            ((Palissade) t).place();
        }
        this.towers.add(t);
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
    public void createTower(int towerType, int col, int line) {
        Tower tower = null;
        switch (towerType) {
            case 1 -> tower = new Archer(this, id, col, line);
            case 2 -> tower = new Barrack(this, id, col, line);
            case 3 -> tower = new Bramble(this, id, col, line);
            case 4 -> tower = new Palissade(this, id, col, line);
            case 5 -> tower = new SorcererTower(this, id, col, line);
            case 6 -> tower = new Ballista(this, id, col, line);
        }
        if (tower != null
                && balance.get() >= tower.getCost()
                && tower.canBePlaced(line, col)
                && countSameType(tower) <= tower.maxAllowed()) {
            addTower(tower);
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
    public void addAnimation(Animation a) {
        animations.add(a);
    }
    public void delAnimation(Animation a) {
        animations.remove(a);
    }

    public void earn(int gain) {
        balance.setValue(balance.getValue() + gain);
    }
    public IntegerProperty getBalanceProperty() {
        return balance;
    }

    public void loop() {
        wave.waveLoop();
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
    public ObservableList<Animation> getAnimations() {
        return animations;
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
    }
    public void startingWaveAnimation() {
        Animation nAnimation = new Animation(waveIndex.get()==1, wave.isMacronInflationActivated());
        addAnimation(nAnimation);
    }

    public IntegerProperty getWaveIndexProperty() {
        return waveIndex;
    }

    public void sellTower(Tower t) {
        t.sell();
    }

    public boolean upgradeTower(Tower t) {
        if (!t.canBeUpgraded()) return false;
        if (balance.get() < t.getUpgradeCost()) return false;
        balance.setValue(balance.getValue() - t.getUpgradeCost());
        t.upgrade();
        return true;
    }
}
