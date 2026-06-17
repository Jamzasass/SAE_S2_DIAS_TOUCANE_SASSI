package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.Ground;

/*La sous-classe Bramble qui hérite de Tower.
 * Cette tour ne possède pas d'attaque, elle se place
 * directement sur le chemin et ralentit les ennemis
 * qui passent sur sa case. Ses dégâts sont de zéro.
 * Une bramble a comme attribut spécifique:
 * -son facteur de ralentissement
 * -la durée de son ralentissement
 */
public class Bramble extends Tower {

    private double slowFactor;
    private int slowDuration; //ralentis pendant 10sec

    public Bramble(Environnement bEnv, int brambleId, double brambleX, double brambleY) {
        super(  bEnv,
                1,
                0,
                brambleId,
                0,
                brambleX,
                brambleY,
                1,
                bEnv.getSettings().getBrambleCost());
        this.slowFactor = bEnv.getSettings().getBrambleSlowFactor();
        this.slowDuration = bEnv.getSettings().getBrambleSlowDuration();
    }

    @Override
    public void act() {
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (onTile(e)) {
                e.applySlow(this.slowFactor, this.slowDuration*30);
            }
        }
    }

    public boolean onTile(Enemy e){
        int enemyX = (int) e.getX();
        int enemyY = (int) e.getY();
        int brambleX = (int) getX();
        int brambleY = (int) getY();
        return enemyX == brambleX && enemyY == brambleY;
    }

    @Override
    public boolean isLiving(){
        return true;    // indestructible
    }

    @Override
    public void takeDamage(int amount) {
        // Bramble ignore les dégâts
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
        return 10;
    }

    @Override
    public boolean canBeUpgraded() {
        return false;
    }
}
