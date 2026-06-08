package universite_paris8.iut.rdias.towerdefense.model.actor;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;

public abstract class Enemy extends Soldier {
    private int deathValue;
    private int cooldown = 0;

    public Enemy(Environnement env, int eHp, int eDmg, int eId, double eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, eRange, eX, eY, eSpeed, 42, 40);
        this.deathValue = eDeathValue;
    }

    public int getDeathValue(){
        return deathValue;
    }

    protected void tick(){
        if (cooldown > 0) {
            cooldown--;
        }
    }

    protected boolean canAct(){
        return cooldown <= 0;
    }

    protected void resetCooldown(int frames){
        this.cooldown = frames;
    }
}
