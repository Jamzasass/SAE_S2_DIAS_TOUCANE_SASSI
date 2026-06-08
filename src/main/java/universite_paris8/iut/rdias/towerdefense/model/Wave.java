package universite_paris8.iut.rdias.towerdefense.model;

public class Wave {
    private Environnement env;
    private int waveIndex;
    private static final int[][] spanwPoints = {{4, 8}, {4, 38}, {4, 60}};
    private int nbEnemies;
    private int delayBetweenSpawns;
    private boolean macronInflationActivated;

    public Wave(Environnement env, int waveIndex, int nbEnemies, int delayBetweenSpawns) {
        this.env = env;
        this.waveIndex = waveIndex;
        this.nbEnemies = nbEnemies;
        this.delayBetweenSpawns = delayBetweenSpawns;
        if (waveIndex > 4) {
            int random = (int)(Math.random()*4);
            this.macronInflationActivated = random==3;
        }
    }

    public void waveLoop(int cptLap) {
        if (cptLap % (delayBetweenSpawns*30) == 0 && nbEnemies>0) {
            int random = (int)(Math.random() * spanwPoints.length);
            int[] spawn = spanwPoints[random];
            int line = spawn[0];
            int col = spawn[1];
            Vikings v = new Vikings(env, env.getId(), col, line);
            env.addEnemy(v);
            env.incrementId();
            nbEnemies--;
        }
        else if (nbEnemies==0) {
            if (env.getEnemies().isEmpty() && (cptLap*10) % (delayBetweenSpawns*30) == 0) {
                env.nextWave();
            }
        }

    }

    public int getNbEnemies() {
        return nbEnemies;
    }

    public int getDelayBetweenSpawns() {
        return delayBetweenSpawns;
    }

    public boolean isMacronInflationActivated() {
        return macronInflationActivated;
    }

}
