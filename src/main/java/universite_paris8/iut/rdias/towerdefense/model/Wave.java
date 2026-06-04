package universite_paris8.iut.rdias.towerdefense.model;

public class Wave {
    private int nbEnemies;
    private int delayBetweenSpawns;

    public Wave(int nbEnemies, int delayBetweenSpawns) {
        this.nbEnemies = nbEnemies;
        this.delayBetweenSpawns = delayBetweenSpawns;
    }

    public int getNbEnemies() {
        return nbEnemies;
    }

    public int getDelayBetweenSpawns() {
        return delayBetweenSpawns;
    }


}
