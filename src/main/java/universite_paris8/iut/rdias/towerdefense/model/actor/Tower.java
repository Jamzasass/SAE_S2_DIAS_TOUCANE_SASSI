package universite_paris8.iut.rdias.towerdefense.model.actor;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;

public abstract class Tower extends Actor{

    private final int cost;
    private int speedAct; //en demi seconde (si 1 = une attaque possible toutes le 1/2 secondes
    private int level;
    private int cooldown;


    public Tower(Environnement env, int tHp, int tDmg, int tId, double tRange, double tX, double tY, int speedAct, int tCost) {
        super(env, tHp, tDmg, tId, tRange, tX, tY);
        this.cost = tCost;
        this.speedAct = speedAct;
        this.cooldown = speedAct*30;
        this.level = 1;
    }

    public int getCost() {
        return cost;
    }

    public double getSpeedAct() {
        return speedAct;
    }

    public int getLevel() {
        return level;
    }
    public int getCooldown() {
        return cooldown;
    }

    public abstract void upgrade();

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
        this.level++;
    }

    public void sold(){
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

    private boolean isAdjacentToPath(int line, int col) {
        var ground = getEnvironnement().getGround();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : dirs) {
            int nL = line + d[0];
            int nC = col + d[1];
            if (nL >= 0 && nL < ground.heigth() && nC >= 0 && nC < ground.width()) {
                if (ground.isPath(nL, nC)) return true;
            }
        }
        return false;
    }

    public int maxAllowed() {
        return Integer.MAX_VALUE;
    }

}
