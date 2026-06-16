package universite_paris8.iut.rdias.towerdefense.model.actor;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rdias.towerdefense.model.Environnement;

/*La sous-classe Tower qui hérite de Actor.
* Une tower a comme attribut spécifique:
* -son coût d'achat
* -sa vitesse d'attaque (en demi-seconde)
* -son temps de recharge (cooldown)
* -son niveau (pour les améliorations)
*/

public abstract class Tower extends Actor{

    private final int cost;
    private int speedAct; //en demi seconde (si 1 = une attaque possible toutes le 1/2 secondes
    private int cooldown;
    private IntegerProperty level;


    public Tower(Environnement env, int tHp, int tDmg, int tId, double tRange, double tX, double tY, int speedAct, int tCost) {
        super(env, tHp, tDmg, tId, tRange, tX, tY);
        this.cost = tCost;
        this.speedAct = speedAct;
        this.cooldown = speedAct*30;
        this.level = new SimpleIntegerProperty(1);
    }

    public Actor searchTarget() {
        Enemy closest = null;
        double minDist = Math.pow(getRange(), 2);
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (!e.isLiving()) continue;
            double dist = calcDistanceFromTargerUsingPyth(e);
            if (dist < minDist) {
                minDist = dist;
                closest = e;
            }
        }
        return closest;
    }

    //Getter
    public int getCost() {
        return cost;
    }
    public double getSpeedAct() {
        return speedAct;
    }
    public int getLevel(){ return level.get();}
    public int getCooldown() {
        return cooldown;
    }

    public void upgrade() {
        double factor = getEnvironnement().getSettings().getUpgradeFactor();
        int newMaxHp = (int) (getMaxHp() * factor);
        int newDmg   = (int) (getDmg() * factor);
        setMaxHp(newMaxHp);
        setHp(newMaxHp);
        setDmg(newDmg);
        lvlUp();
    }

    public int getUpgradeCost() {
        double f = getEnvironnement().getSettings().getUpgradeCostFactor();
        return (int) (getCost() * f);
    }

    public boolean canBeUpgraded() {
        return getLevel() < 2;
    }

    public void tick(){
        cooldown++;
    }

    public boolean canAct(){
        return cooldown >= speedAct*30;
    }

    public void resetCooldown() {
        this.cooldown = 0;
    }

    public void setSpeedAct(int speedAct){this.speedAct = speedAct;}

    public void lvlUp() {
        this.level.set(this.level.get() + 1);
    }

    public void sell(){
        int refund = this.cost / 2;
        getEnvironnement().earn(refund);
        getEnvironnement().delTower(this);
    }
    public boolean canBePlaced(int line, int col) {
        var ground = getEnvironnement().getGround();
        if (line < 0 || line >= ground.heigth() || col < 0 || col >= ground.width()) {
            return false;
        }
        if (!ground.isGrass(line, col)) {
            return false;
        }
        return isAdjacentToPath(line, col);
    }

    public boolean isAdjacentToPath(int line, int col) {
        var ground = getEnvironnement().getGround();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int range = placementRange();
        for (int[] d : dirs) {
            for (int step = 1; step <= range; step++) {
                int nL = line + d[0] * step;
                int nC = col + d[1] * step;
                if (nL >= 0 && nL < ground.heigth() && nC >= 0 && nC < ground.width()) {
                    if (ground.isPath(nL, nC)) return true;
                }
            }
        }
        return false;
    }

    public int maxAllowed() {
        return Integer.MAX_VALUE;
    }

    public int placementRange(){
        return 1;
    }

    public IntegerProperty getLevelProperty() {
        return level;
    }
}
