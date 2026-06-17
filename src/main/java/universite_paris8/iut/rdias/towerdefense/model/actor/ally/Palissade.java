package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

/*La sous-classe Palissade qui hérite de Tower.
 * Cette tour n'a pas d'attaque, elle se place sur le chemin
 * pour bloquer les ennemis. Elle ne peut être détruite
 * que par le bélier viking (RamWarrior). Ses dégâts,
 * sa portée d'attaque et sa vitesse d'attaque sont de zéro.
 */

public class Palissade extends Tower {
    public Palissade(Environnement pEnv, int palissadeId, double palissadeX, double palissadeY) {
        super(pEnv,
                pEnv.getSettings().getPalissadeHp(),
                0,
                palissadeId,
                0,
                palissadeX,
                palissadeY,
                1,
                pEnv.getSettings().getPalissadeCost()
        );
    }

    @Override
    public void act() {
    }


    @Override
    public boolean canBePlaced(int line, int col) {
        boolean result = false;
        Ground ground = getEnvironnement().getGround();

        if (line >= 0 && line < ground.heigth() && col >= 0 && col < ground.width() && ground.isPath(line, col)) {
            result = true;
        }
        return result;
    }

    public void place() {
        getEnvironnement().getGround().setTile((int)this.getY(), (int)this.getX(), 4);
        getEnvironnement().getGround().refreshBFS();
    }

    public void deleted() {
        System.out.println("deleted");
        getEnvironnement().getGround().setTile((int)this.getY(), (int)this.getX(), 1);
        getEnvironnement().getGround().refreshBFS();
    }

    @Override
    public int maxAllowed() {
        return 4;
    }
}
