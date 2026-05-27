//package universite_paris8.iut.rdias.towerdefense.model;
//
//public class Barrack extends Tower{
//
//    private static final int hplvl1 = 250;
//    private static final int hplvl2 = 325;
//    private static final double speedProductionlvl1 = 0.5;
//    private static final double SpeedProductionlvl2 = 0.2;
//    private static final int knightHPlvl1 = 70;
//    private static final int knightHPlvl2 = 105;
//    private static final int knightDmglvl1 = 20;
//    private static final int knightDmglvl2 = 30;
//    private double speedProduction;
//
//    public Barrack (int barrackId, double barrackX, double barrackY, double speedProduction){
//        super(hplvl1, 0, barrackId, 0, barrackX, barrackY, 0, 200);
//        this.speedProduction = speedProductionlvl1;
//    }
//
//    @Override
//    public void act(Environnement env){
//        tick();
//        Knight k = new knight(nextKnightId++, getX(), getY(), getLevel() == 2 ? knightHPlvl2 : knightHPlvl1, getLevel() == 2 ? knightDmglvl2 : knightDmglvl1 ); //Pour le spawn du chevalier
//        env.addKnight(k);
//        )
//    }
//
//    @Override
//    public void upgrade(){
//        if (getLevel() < 2){
//            setHp(hplvl2);
//            setSpeedProduction(SpeedProductionlvl2);
//            //lvlUp();
//        }
//    }
//
//    protected void setSpeedProduction(int speedProduction){
//        this.speedProduction = speedProduction;
//    }
//
//}
