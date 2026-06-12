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
    public boolean canBePlaced(int line, int col) {
        Ground ground = getEnvironnement().getGround();
        if (line < 0 || line >= ground.heigth() || col < 0 || col >= ground.width()) {
            return false;
        }
        if (!ground.isPath(line, col)) {
            return false;
        }
        ground.setTile(line, col, 4);
        ground.refreshBFS();
        return true;
    }

//    @Override
//    public boolean canBePlacedCheck(int line, int col) {
//        Ground ground = getEnvironnement().getGround();
//        if (line < 0 || line >= ground.heigth() || col < 0 || col >= ground.width()) {
//            return false;
//        }
//        return ground.isPath(line, col);
//    }

    @Override
    public int maxAllowed() {
        return 4;
    }
}
