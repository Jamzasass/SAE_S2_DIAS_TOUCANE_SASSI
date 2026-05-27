package universite_paris8.iut.rdias.towerdefense.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Actor {
    private int hp;
    private int dmg;
    private int id;
    private double range;
    private DoubleProperty x;
    private DoubleProperty y;

    public Actor(int aHp, int aDmg, int aId, double aRange, double aX, double aY) {
        this.hp = aHp;
        this.dmg = aDmg;
        this.id = aId;
        this.range = aRange;
        this.x = new SimpleDoubleProperty(aX);
        this.y = new SimpleDoubleProperty(aY);
    }

    public abstract void act(Environnement env);

    //Getters
    public int getHp() {
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
    public DoubleProperty getXProperty() {return x;}
    public DoubleProperty getYProperty() {return y;}

    public boolean isLiving(){return this.hp > 0;}

    public void die(){this.hp = 0;}

    public void takeDamage(int amount) {
        this.hp = Math.max(0, this.hp - amount);
    }

    protected void setHp(int hp){
        this.hp = hp;
    }

    protected void setDmg(int dmg){
        this.dmg = dmg;
    }

}
