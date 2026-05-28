package universite_paris8.iut.rdias.towerdefense.model;

public abstract class Tower extends Actor{

    private final int cost;
    private double speedAct;
    private int level;
    private int cooldown;


    public Tower(int tHp, int tDmg, int tId, double tRange, double tX, double tY, double speedAct, int tCost) {
        super(tHp, tDmg, tId, tRange, tX, tY);
        this.speedAct = speedAct;
        this.cost = tCost;
        this.cooldown = 0;
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

    public abstract void upgrade();

    public void tick(){
        if (cooldown > 0) {
            cooldown--;
        }
    }

    protected boolean canAct(){
        return cooldown <= 0;
    }

    protected void resetCooldown(int fps) {
        this.cooldown = (int) (fps / getSpeedAct());
    }

    protected void setSpeedAct(double speedAct){this.speedAct= speedAct;}

    protected void lvlUp() {
        this.level++;
    }

    protected void setCooldown (int frames){
        this.cooldown = frames;
    }
}
