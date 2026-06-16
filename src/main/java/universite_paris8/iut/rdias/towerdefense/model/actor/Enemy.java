package universite_paris8.iut.rdias.towerdefense.model.actor;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;

/*La sous-classe Enemy qui hérite de Soldier.
 * Un enemy attaque par default le chateau.
 * Un enemy a comme attribut spécifique:
 * -sa valeur en or rapportée à sa mort
 */


public abstract class Enemy extends Soldier {
    private int deathValue;

    public Enemy(Environnement env, int eHp, int eDmg, int eId, double eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, eRange, eX, eY, eSpeed, 42, 40);
        this.deathValue = eDeathValue;
    }


    public int getDeathValue(){
        return deathValue;
    }
}
