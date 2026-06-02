package universite_paris8.iut.rdias.towerdefense.model;

import java.util.List;

public abstract class Soldier extends Actor{

    private double speed;
    private double directionX;
    private double directionY;
    private int xCible;
    private int yCible;
    private int[] nextCase;

    public Soldier (Environnement env, int sHp, int sDmg, int sId, double sRange, double sX, double sY, double sSpeed, int xCible, int yCible) {
        super(env, sHp, sDmg, sId, sRange, sX, sY);
        this.speed = sSpeed;
        this.directionX = 0;
        this.directionY = 0;
        this.xCible = xCible;
        this.yCible = yCible;
        this.nextCase = new int[2];
        wayChangement();
        majDirection();
    }
    public void setChemin() {
        majDirection();
    }

    public void move(){
        double newX = getX() + (speed * directionX);
        double newY = getY() + (speed * directionY);

        try {
            setX(newX);
            setY(newY);
        } catch (Exception ex) {
            System.out.println("  EXCEPTION: " + ex.getMessage());
        }
        if (!getEnvironnement().getGround().isPath(nextCase[0], nextCase[1])
                && !getEnvironnement().getGround().isCastle(nextCase[0], nextCase[1])) {
            wayChangement();
        }
        else if (atteint(nextCase[1], nextCase[0]) ) {
            setX(nextCase[1]);
            setY(nextCase[0]);
            wayChangement();
        }
    }
    public boolean atteint(double cibleX, double cibleY) {
        return  valAbs(getX() - cibleX) < 0.05 && valAbs(getY() - cibleY) < 0.05;
    }
    public void majDirection() {
        double dx = nextCase[1] - getX();
        double dy = nextCase[0] - getY();
        if (dx == 0.0)
            directionX = 0;
        else if (dx > 0.0)
            directionX = 1;
        else
            directionX = -1;
        if (dy == 0.0)
            directionY = 0;
        else if (dy > 0.0)
            directionY = 1;
        else
            directionY = -1;
    }

    public void wayChangement() {
        int[][][][] mapBfs = getEnvironnement().getGround().getMapBFS().getDistancesMap();
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
        int shortestDist = -1;
        int[] bestNeighbor = new int[2];
        for (int[] d : dirs) {
            int nL = (int)getY() + d[0], nC = (int)getX() + d[1];
            if (nL < 0 || nL >= getEnvironnement().getGround().heigth()
                    || nC < 0 || nC >= getEnvironnement().getGround().width()) {
                continue;
            }
            if (!getEnvironnement().getGround().isPath(nL, nC) && !getEnvironnement().getGround().isCastle(nL, nC)) {
                continue;
            }
            int dCible = mapBfs[yCible][xCible][nL][nC];
            if (shortestDist == -1 || (dCible >= 0 && dCible < shortestDist)) {
                shortestDist = dCible;
                bestNeighbor[0] = nL;
                bestNeighbor[1] = nC;
            }
        }
        if (bestNeighbor[0] != 0 && bestNeighbor[1] != 0)
            nextCase = bestNeighbor;
        majDirection();
    }

    public double getSpeed(){
        return speed;
    }

    public int getxCible() {
        return xCible;
    }
    public int getyCible() {
        return yCible;
    }
    public void setxCible(int xCible) {
        this.xCible = xCible;
    }
    public void setyCible(int yCible) {
        this.yCible = yCible;
    }
    public double valAbs(double v) {
        if (v<0) return -v;
        else return v;
    }
}
