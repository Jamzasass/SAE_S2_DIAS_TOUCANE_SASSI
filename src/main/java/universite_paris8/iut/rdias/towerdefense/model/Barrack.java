package universite_paris8.iut.rdias.towerdefense.model;

public class Barrack extends Tower{

    private static final int hplvl1 = 250;
    private static final int hplvl2 = 325;
    private static final int speedProductionlvl1 = 6;
    private static final int SpeedProductionlvl2 = 3;
    private static final int knightHPlvl1 = 70;
    private static final int knightHPlvl2 = 105;
    private static final int knightDmglvl1 = 20;
    private static final int knightDmglvl2 = 30;
    private static int nextKnightId = 0;
    private int speedProduction;

    public Barrack (int barrackId, double barrackX, double barrackY, int speedProduction){
        super(hplvl1, 0, barrackId, 0, barrackX, barrackY, 0, 200);
        this.speedProduction = speedProductionlvl1;
    }

    @Override
    public void act(Environnement env){
        tick();
        Knight k = new Knight(getLevel() == 2 ? knightHPlvl2 : knightHPlvl1, getLevel() == 2 ? knightDmglvl2 : knightDmglvl1, nextKnightId++, getX(), getY());
        env.addKnight(k);
    }

    @Override
    public void upgrade(){
        if (getLevel() < 2){
            setHp(hplvl2);
            setSpeedProduction(SpeedProductionlvl2);
            //lvlUp();
        }
    }

    protected void setSpeedProduction(int speedProduction){
        this.speedProduction = speedProduction;
    }

}
