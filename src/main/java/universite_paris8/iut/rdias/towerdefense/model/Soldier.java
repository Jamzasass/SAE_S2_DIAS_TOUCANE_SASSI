package universite_paris8.iut.rdias.towerdefense.model;

import java.util.List;

public abstract class Soldier extends Actor{

    private double speed;
    private double directionX;
    private double directionY;
    private List<int[]> chemin;
    private int indexCible;
    private int xCible;
    private int yCible;

    public Soldier (Environnement env, int sHp, int sDmg, int sId, double sRange, double sX, double sY, double sSpeed, int xCible, int yCible) {
        super(env, sHp, sDmg, sId, sRange, sX, sY);
        this.speed = sSpeed;
        this.directionX = 0;
        this.directionY = 0;
        this.chemin = null;
        this.indexCible = 1;
        this.xCible = xCible;
        this.yCible = yCible;
    }
    public void setChemin(List<int[]> sChemin) {
        this.chemin = sChemin;
        this.indexCible = 1;
        majDirection();
    }
    public boolean cheminTermine() {
        return chemin == null || indexCible >= chemin.size();
    }

    public void move(){
        if (chemin == null) {
            this.wayChangement();
        }
        if (cheminTermine())  {
            wayChangement();
            return;
        }

        double newX = getX() + (speed * directionX);
        double newY = getY() + (speed * directionY);

        try {
            setX(newX);
            setY(newY);
            if (this instanceof Knight)
                System.out.println("ca bouge" + this);
        } catch (Exception ex) {
            System.out.println("  EXCEPTION: " + ex.getMessage());
        }

        int[] cible = chemin.get(indexCible);
        if (!getEnvironnement().getGround().isPath(cible[0], cible[1]) && !getEnvironnement().getGround().isCastle(cible[0], cible[1])) {
            wayChangement();
        }
        else if (atteint(cible[1], cible[0]) ) {
            setX(cible[1]);
            setY(cible[0]);
            indexCible++;
            majDirection();

            //wayChangement();
        }

    }
    private boolean atteint(double cibleX, double cibleY) {
        boolean okX = (directionX > 0 && getX() >= cibleX)
                || (directionX < 0 && getX() <= cibleX)
                ||  directionX == 0;
        boolean okY = (directionY > 0 && getY() >= cibleY)
                || (directionY < 0 && getY() <= cibleY)
                ||  directionY == 0;
        return okX && okY;
    }
    private void majDirection() {
        if (cheminTermine()) {
            directionX = 0;
            directionY = 0;
            return;
        }
        int[] cible = chemin.get(indexCible);
        directionX = Math.signum(cible[1] - getX());
        directionY = Math.signum(cible[0] - getY());
    }

    public abstract void wayChangement();

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

    public List<int[]> getChemin() {
        return chemin;
    }
}
