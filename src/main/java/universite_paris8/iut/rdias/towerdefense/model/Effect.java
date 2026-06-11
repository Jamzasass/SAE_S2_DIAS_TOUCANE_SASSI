package universite_paris8.iut.rdias.towerdefense.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Effect {
    private Environnement environnement;
    private DoubleProperty x;
    private DoubleProperty y;
    private int id;
    private int dmg;
    private boolean finished = false;

    public Effect(Environnement eEnv, double eX, double eY, int eDmg, int eId) {
        this.environnement = eEnv;
        this.x = new SimpleDoubleProperty(eX);
        this.y = new SimpleDoubleProperty(eY);
        this.dmg = eDmg;
        this.id = eId;
    }

    public abstract void act();

    public boolean isFinished() { return finished; }
    public DoubleProperty getXProperty() { return x; }
    public DoubleProperty getYProperty() { return y; }
    public double getX() { return x.getValue(); }
    public double getY() { return y.getValue(); }
    public void finished() {
        finished = true;
        environnement.delEffect(this);
    }
    public int getDmg() {
        return dmg;
    }
    public void setX(double nX) {
        x.setValue(nX);
    }
    public void setY(double nY) {
        y.setValue(nY);
    }

    public int getId() {
        return id;
    }
}
