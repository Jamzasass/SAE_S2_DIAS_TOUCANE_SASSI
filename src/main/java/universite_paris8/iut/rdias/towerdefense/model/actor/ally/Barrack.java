package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;


/*La sous-classe Barrack qui hérite de Tower.
 * Cette tour ne possède pas d'attaque, elle produit
 * des chevaliers (Knight) qui vont combattre les ennemis.
 * Sa portée d'attaque et ses dégâts sont de zéro.
 * Une barrack a comme attribut spécifique:
 * -les coordonnées de la tile chemin la plus proche
 */

public class Barrack extends Tower {
    private int[] coordClosePath;
    private int nbKnight;
    private int nbKnightMax;

    public Barrack (Environnement bEnv, int barrackId, double barrackX, double barrackY) {
        super(  bEnv,
                bEnv.getSettings().getBarrackHp(),
                0,
                barrackId,
                0,
                barrackX,
                barrackY,
                bEnv.getSettings().getBarrackSpeedProduction(),
                bEnv.getSettings().getBarrackCost()
        );
        nbKnightMax = getEnvironnement().getSettings().getBarrackNbKnightMax();
        coordClosePath = getEnvironnement().getGround().getClosestPath((int) getY(), (int) getX());
        nbKnight = 0;
    }

    @Override
    public void act() {
        tick();
        if (canAct() && hasEnemies(getEnvironnement()) && nbKnight < nbKnightMax) {
            Knight k = new Knight(getEnvironnement(), getEnvironnement().getNextId(), coordClosePath[1], coordClosePath[0], this);
            getEnvironnement().addKnight(k);
            this.incrementNbKnight();
            resetCooldown();
        }
    }

    public boolean hasEnemies(Environnement env) {
        for (Enemy e : env.getEnemies()){
            if (e.isLiving()) {
                return true;
            }
        }
        return false;
    }

    public int[] getCoordClosePath() {
        return coordClosePath;
    }

    public void incrementNbKnight() {
        nbKnight++;
    }
    public void decrementNbKnight() {
        nbKnight--;
    }
}
