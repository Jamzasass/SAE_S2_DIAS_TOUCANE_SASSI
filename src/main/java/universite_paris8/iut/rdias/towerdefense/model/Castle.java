package universite_paris8.iut.rdias.towerdefense.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Castle {

    private final int maxHp = 500;
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

    //.
    public int getMaxHp() {
        return maxHp;
    }
    //.
}
