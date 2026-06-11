package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Palissade extends Tower {


    private static final int hplvl2 = 300;

    public Palissade(Environnement pEnv, int palissadeId, double palissadeX, double palissadeY) {
        super(pEnv,
                pEnv.getSettings().getPalissadeHp(),
                0,
                palissadeId,
                0,
                palissadeX,
                palissadeY,
                1,
                pEnv.getSettings().getPalissadeCost());
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
//            setHp(hplvl2);
//            lvlUp();
        }
    }

    @Override
    public boolean canBePlaced(int line, int col) {
        boolean result = false;
        Ground ground = getEnvironnement().getGround();

        if (line >= 0 && line < ground.heigth() && col >= 0 && col < ground.width() && ground.isPath(line, col)) {
            ground.setTile((int)this.getY(), (int)this.getX(), 4);
            ground.refreshBFS();
            result = true;
        }
        return result;
    }

    @Override
    public int maxAllowed() {
        return 4;
    }
}
