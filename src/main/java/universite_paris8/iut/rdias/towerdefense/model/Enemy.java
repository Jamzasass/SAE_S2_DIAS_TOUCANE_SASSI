package universite_paris8.iut.rdias.towerdefense.model;

import java.util.List;

public abstract class Enemy extends Soldier {
    private int deathValue;

    public Enemy(Environnement env, int eHp, int eDmg, int eId, double eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, eRange, eX, eY, eSpeed, 42, 40);
        this.deathValue = eDeathValue;
    }

    public void wayChangement() {
        AStar astar =  new AStar(getEnvironnement().getGround(), 0);
        List<int[]> chemin = astar.trouverChemin((int)this.getY(), (int)this.getX(), getxCible(), getyCible());
        this.setChemin(chemin);
    }

    public int getDeathValue(){
        return deathValue;
    }

}
