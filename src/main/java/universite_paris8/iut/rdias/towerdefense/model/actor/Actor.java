package universite_paris8.iut.rdias.towerdefense.model.actor;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Palissade;

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
    public abstract Actor searchTarget();
    public int calcDistanceFromTargerUsingBFS(Actor a) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][(int)a.getY()][(int)a.getX()];
    }
    public double calcDistanceFromTargerUsingPyth(Actor a) {
        double dx = getX() - a.getX();
        double dy = getY() - a.getY();
        return dx * dx + dy * dy;
    }
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

    public void setX(double valeur) {
        this.x.set(valeur);
    }

    public void setY(double valeur) {
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
    public int getMaxHp(){
        return maxHp;
    }

    public boolean isLiving(){return this.hp.getValue() > 0;}

    public void die(){
        this.hp.setValue(0);
        try {
            if (this instanceof Enemy) {
                env.earn(((Enemy) this).getDeathValue());
            }
            else if (this instanceof Palissade) {
                ((Palissade) this).deleted();
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



    public void setHp(int hp){
        this.hp.setValue(hp);
    }

    public void setDmg(int dmg){
        this.dmg = dmg;
    }

    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }


}
