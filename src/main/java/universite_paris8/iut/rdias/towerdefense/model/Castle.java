package universite_paris8.iut.rdias.towerdefense.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*La classe Castle représente le château que le joueur
 * doit protéger.
 * Le château perd des points de vie lorsque les ennemis
 * atteignent sa position. Si ses points de vie tombent à 0,
 * la partie est perdue.
 * Un castle a comme attribut:
 * -ses points de vie maximum
 * -ses points de vie actuels (sous forme de propriété observable)
 * Deux attributs PV pour la barre de vie.
 */

public class Castle {

    private final int maxHp = 100;
    private IntegerProperty hpPlayer;

    public Castle() {
        this.hpPlayer = new SimpleIntegerProperty(maxHp);
    }

    public IntegerProperty getHpPlayerProperty() {
        return hpPlayer;
    }

    public int getHp() {
        return hpPlayer.get();
    }

    public void takeDamage(int amount) {
        hpPlayer.set(Math.max(0, hpPlayer.get() - amount));
    }

    public int getMaxHp() {
        return maxHp;
    }

}
