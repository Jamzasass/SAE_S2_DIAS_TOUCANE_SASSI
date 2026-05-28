package universite_paris8.iut.rdias.towerdefense.model;

public class Barrack extends Tower{

    private static final int hplvl1 = 250;
    private static final int hplvl2 = 325;
    private static final int speedProductionlvl1 = 6;
    private static final int speedProductionlvl2 = 3;
    private static final int knightHPlvl1 = 70;
    private static final int knightHPlvl2 = 105;
    private static final int knightDmglvl1 = 20;
    private static final int knightDmglvl2 = 30;
    private static int nextKnightId = 0;

    public Barrack (Environnement env, int barrackId, double barrackX, double barrackY) {
        super(env, hplvl1, 0, barrackId, 0, barrackX, barrackY, speedProductionlvl1, 200);
    }

    @Override
    public void act(Environnement env){
        tick();
        if (canAct() && hasEnemies(env)){
            int hpKnight = getLevel() == 2 ? knightHPlvl2 : knightHPlvl1;
            int dmgKnight = getLevel() == 2 ? knightDmglvl2 : knightDmglvl1;
            Knight k = new Knight(env, hpKnight, dmgKnight, nextKnightId++, getX(), getY());
            env.addKnight(k);
//            setCooldown(speedAct*60);
        }
    }

    @Override
    public void upgrade(){
        if (getLevel() < 2){
            setHp(hplvl2);
            setSpeedAct(speedProductionlvl2);
            lvlUp();
        }
    }

    private boolean hasEnemies(Environnement env){
        for (Enemy e : env.getEnemies()){
            if (e.isLiving()) {
                return true;
            }
        }
        return false;
    }
}
