package universite_paris8.iut.rdias.towerdefense.model;

public class SorcererTower extends Tower {
    private int dmgPerSec;
    private int zoneDuration;

    public SorcererTower(int tHp, int tDmg, int tId, double tRange, double tX, double tY, double tSpeedAttack, int sTdmgPerSec, int sTZoneDuration, int sDmgPersec) {
        super(tHp, tDmg, tId, tRange, tX, tY, tSpeedAttack);
        this.dmgPerSec = sTdmgPerSec;
        this.zoneDuration = sTZoneDuration;
    }

    @Override
    public void act() {
        System.out.println("attackkckezfkojkergojikpezfjvcghk");
    }
}
