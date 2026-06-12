package universite_paris8.iut.rdias.towerdefense.model.actor;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rdias.towerdefense.model.Environnement;

public abstract class Actor {
    private Environnement env;
    private IntegerProperty hp;
    private int dmg;
    private int id;
    private double range;
    private DoubleProperty x;
    private DoubleProperty y;
    private int maxHp;

    public Actor(Environnement env, int aHp, int aDmg, int aId, double aRange, double aX, double aY) {
        this.env = env;
        this.hp = new SimpleIntegerProperty(aHp);
        this.dmg = aDmg;
        this.id = aId;
        this.range = aRange;
        this.x = new SimpleDoubleProperty(aX);
        this.y = new SimpleDoubleProperty(aY);
        this.maxHp = aHp;
    }

    public abstract void act();

    //Getters
    public int getHp() {
        return hp.getValue();
    }
    public IntegerProperty getHpProperty() {
        return hp;
    }
    public int getDmg() {
        return dmg;
    }
    public int getId() {
        return id;
    }
    public double getRange() {
        return range;
    }

    protected void setX(double valeur) {
        this.x.set(valeur);
    }

    protected void setY(double valeur) {
        this.y.set(valeur);
    }

    public double getX() {
        return x.doubleValue();
    }
    public double getY() {
        return y.doubleValue();
    }
    public Environnement getEnvironnement() {
        return this.env;
    }
    public DoubleProperty getXProperty() {return x;}
    public DoubleProperty getYProperty() {return y;}

    public boolean isLiving(){return this.hp.getValue() > 0;}

    public void die(){
        this.hp.setValue(0);
        try {
            if (this instanceof Enemy) {
                env.earn(((Enemy) this).getDeathValue());
            }
            this.env.addDyingActor(this);
        } catch (Exception ex) {
            System.out.println("  EXCEPTION: " + ex.getMessage());
        }

    }

    public void takeDamage(int amount) {
        this.hp.setValue(Math.max(0, this.hp.getValue() - amount));
        if (hp.getValue() <= 0) {
            this.die();
        }
    }

    protected void setHp(int hp){
        this.hp.setValue(hp);
    }

    protected void setDmg(int dmg){
        this.dmg = dmg;
    }

    public int getMaxHp(){
        return maxHp;
    }

    protected void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }
}
