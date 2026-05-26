package universite_paris8.iut.rdias.towerdefense.model;

import java.util.List;

public abstract class Soldier extends Actor{
    private double speed;

    private double directionX;
    private double directionY;
    private List<int[]> chemin;  // positions {ligne, col} renvoyées par AStar
    private int indexCible;

    public Soldier (int sHp, int sDmg, int sId, double sRange, double sX, double sY, double sSpeed){
        super(sHp, sDmg, sId, sRange, sX, sY);
        this.speed = sSpeed;
        this.directionX = 0;
        this.directionY = 0;
        this.chemin = null;
        this.indexCible = 0;
    }
    public void setChemin(List<int[]> chemin) {
        this.chemin = chemin;
        this.indexCible = 0;
        majDirection();
    }
    public boolean cheminTermine() {
        return chemin == null || indexCible >= chemin.size();
    }

    public void move(Environnement env){
        if (cheminTermine()) return;

        double newX = getX() + (speed * directionX);
        double newY = getY() + (speed * directionY);

        try {
            setX(newX);
            setY(newY);
        } catch (Exception ex) {
            System.out.println("  EXCEPTION: " + ex.getMessage());
        }

        int[] cible = chemin.get(indexCible);
        if (atteint(cible[1], cible[0])) {
            setX(cible[1]);
            setY(cible[0]);

            indexCible++;
            majDirection();
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
        directionX = Math.signum(cible[1] - getX()); // col → X
        directionY = Math.signum(cible[0] - getY()); // ligne → Y
    }
    public double getSpeed(){
        return speed;
    }


}
