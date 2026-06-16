package universite_paris8.iut.rdias.towerdefense.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

/*La classe abstraite Effect représente tous les effets
 * visuels et projectiles dans le jeu (flèches, boules de feu, etc.).
 * Les effets se déplacent vers leur cible et disparaissent
 * lorsqu'ils l'atteignent.
 * Un effect a comme attribut:
 * -L'environnement où il est attribué
 * -Sa position X et Y dans la map
 * -Son identifiant unique
 * -Ses dégâts infligés à la cible
 * -Un booléen indiquant s'il est terminé
 * -Sa cible (Actor)
 * -Sa vitesse de déplacement
 * -Sa direction sur l'axe X
 * -Sa direction sur l'axe Y
 * -Son angle de rotation (pour l'animation)
 */

public abstract class Effect {
    private static int nId = 0;
    private Environnement environnement;
    private DoubleProperty x;
    private DoubleProperty y;
    private int id;
    private int dmg;
    private boolean finished = false;
    private Actor target;
    private double speed;
    private DoubleProperty directionX;
    private DoubleProperty directionY;
    private DoubleProperty angle;

    public Effect(Environnement eEnv, double eX, double eY, int eDmg, Actor eTarget, double eSpeed) {
        this.environnement = eEnv;
        this.x = new SimpleDoubleProperty(eX);
        this.y = new SimpleDoubleProperty(eY);
        this.dmg = eDmg;
        this.id = nId;
        nId++;
        this.target = eTarget;
        this.speed = eSpeed;
        this.directionX = new SimpleDoubleProperty(0);
        directionY = new SimpleDoubleProperty(0);
        angle = new SimpleDoubleProperty(0);
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

    public Environnement getEnvironnement() {
        return environnement;
    }

    public int getId() {
        return id;
    }

    public Actor getTarget() {
        return target;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirectionX() {
        return directionX.getValue();
    }

    public DoubleProperty directionXProperty() {
        return directionX;
    }

    public double getDirectionY() {
        return directionY.getValue();
    }

    public DoubleProperty directionYProperty() {
        return directionY;
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public double getAngle() {
        return angle.get();
    }

    public DoubleProperty angleProperty() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle.setValue(angle);
    }
}
