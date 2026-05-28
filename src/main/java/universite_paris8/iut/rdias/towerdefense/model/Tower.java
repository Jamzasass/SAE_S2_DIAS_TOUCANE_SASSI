package universite_paris8.iut.rdias.towerdefense.model;

public abstract class Tower extends Actor{

    private final int cost;
    private double speedAttack;
    private int level;
    private int cooldown;


    public Tower(int tHp, int tDmg, int tId, double tRange, double tX, double tY, double tSpeedAttack, int tCost) {
        super(tHp, tDmg, tId, tRange, tX, tY);
        this.speedAttack = tSpeedAttack;
        this.cost = tCost;
        this.cooldown = 0;
        this.level = 1;
    }

    public int getCost() {
        return cost;
    }

    public double getSpeedAttack() {
        return speedAttack;
    }

    public int getLevel() {
        return level;
    }

    public abstract void upgrade();

    public void tick(){
        if (cooldown > 0) {
            cooldown--;
        }
    }

    protected boolean canAttack(){
        return cooldown <= 0;
    }

    protected void resetCooldown(int fps) {
        this.cooldown = (int) (fps / getSpeedAttack());
    }

    protected void setSpeedAttack(double speedAttack){this.speedAttack = speedAttack;}

    protected void lvlUp() {
        this.level++;
    }
}
