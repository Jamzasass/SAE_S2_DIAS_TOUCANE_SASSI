package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Palissade extends Tower {

    private static final int hplvl1 = 200;
    private static final int hplvl2 = 300;

    public Palissade(Environnement env, int palissadeId, double palissadeX, double palissadeY) {
        super(env, hplvl1, 0, palissadeId, 0, palissadeX, palissadeY, 1, 300);
    }

    @Override
    public void act() {
    }

    @Override
    public void takeDamage(int amount) {
    }

    @Override
    public void upgrade() {
        if (getLevel() < 2) {
            setHp(hplvl2);
            lvlUp();
        }
    }

    @Override
    public boolean canBePlaced(int line, int col) {
        Ground ground = getEnvironnement().getGround();
        if (line < 0 || line >= ground.heigth() || col < 0 || col >= ground.width()) {
            return false;
        }
        return ground.isPath(line, col);
    }

    @Override
    public int maxAllowed() {
        return 6;
    }
}
