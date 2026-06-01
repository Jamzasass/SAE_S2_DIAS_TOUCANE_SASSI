package universite_paris8.iut.rdias.towerdefense.model;

public class SorcererTower extends Tower {
    private int dmgPerSec;
    private int zoneDuration;

    public SorcererTower(Environnement env, int tHp, int tDmg, int tId, double tRange, double tX, double tY, double tSpeedAttack, int sTdmgPerSec, int sCost, int sTZoneDuration, int sDmgPersec) {
        super(env, tHp, tDmg, tId, tRange, tX, tY, tSpeedAttack, sCost);
        this.dmgPerSec = sTdmgPerSec;
        this.zoneDuration = sTZoneDuration;
    }

    @Override
    public void act() {
        System.out.println("attackkckezfkojkergojikpezfjvcghk");
    }
    public void upgrade() {
        System.out.println("upgrading ...");
    }
}
