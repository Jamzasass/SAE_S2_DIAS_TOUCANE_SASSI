package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.Settings;

public class SorcererTower extends Tower {
    private static final int hplvl2 = 400;
    private static final int dmgPerSeclvl2 = 15;
    private static final int zoneDurationlvl1 = 3;
    private static final int zoneDurationlvl2 = 4;
    private static final int speedAttacklvl1 = 2;
    private static final int speedAttacklvl2 = 3;

    private int dmgPerSec;
    private int zoneDuration;
    private int zoneFramesLeft;
    private int dmgCounter;

    public SorcererTower(Environnement env, int sorcererHp, int sorcererDmg, int sorcererId, double sorcererX, double sorcererY, int sorcererSpeedAttack, int sorcererCost) {
        super(env, sorcererHp, sorcererDmg, sorcererId, 3.0, sorcererX, sorcererY, sorcererSpeedAttack, sorcererCost);
        this.dmgPerSec = sorcererDmg;
        this.zoneDuration =
        this.zoneFramesLeft = 0;
        this.dmgCounter = 0;
    }

    @Override
    public void act() {
        tick();
        if (zoneFramesLeft > 0) {
            dmgCounter++;               //Applique les dégats toutes les sec.
            if (dmgCounter >= 60) {
                applyDamageInZone(getEnvironnement());
                dmgCounter = 0;
            }
            zoneFramesLeft--;
            if (zoneFramesLeft == 0) {
                resetCooldown();  // Une fois que la zone est terminée, il démarre un cooldown
            }
        } else if (canAct()) {          // Si pas de zone et que le cooldown soit ok, alors il peut recréer une zone.
            zoneFramesLeft = zoneDuration * 60;
            dmgCounter = 0;
        }
    }

    private void applyDamageInZone(Environnement env) {
        for (Enemy e : env.getEnemies()) {
            if (e.isLiving()) {
                double range = Math.hypot(e.getX() - getX(), e.getY() - getY());
                if (range <= getRange()) {
                    e.takeDamage(dmgPerSec);
                }
            }
        }
    }

    @Override
    public void upgrade() {
        if (getLevel() < 2) {
            setHp(hplvl2);
            setSpeedAct(speedAttacklvl2);
            dmgPerSec = dmgPerSeclvl2;
            zoneDuration = zoneDurationlvl2;
            lvlUp();
        }
    }
}
