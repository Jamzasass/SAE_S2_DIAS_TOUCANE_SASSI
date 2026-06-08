package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.ArcherViking;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.Viking;

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
            Enemy e = createNewEnemy();
            env.addEnemy(e);
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

    public Enemy createNewEnemy() {
        Enemy e = null;
        int random = (int)(Math.random() * spanwPoints.length);
        int[] spawn = spanwPoints[random];
        int line = spawn[0];
        int col = spawn[1];
        if (waveIndex < 4){
            e = new Viking(env, env.getSettings().getVikingHp(), env.getSettings().getArcherDmg(), env.getId(), env.getSettings().getVikingSpeed(), col, line);
        }
        else if (waveIndex >= 4) {
            int radomSelectEnemy = (int) (Math.random() * 100);
            if (radomSelectEnemy < 30) {
                e = new ArcherViking(env, env.getSettings().getArcherVikingHp(), env.getSettings().getArcherVikingDmg(), env.getId(), env.getSettings().getArcherVikingRange(), col, line, env.getSettings().getArcherVikingSpeed(), env.getSettings().getArcherVikingDeathValue());
                System.out.println("c'est un viking archer !!");
            }
            else {
                e = new Viking(env, env.getSettings().getVikingHp(), env.getSettings().getArcherDmg(), env.getId(), env.getSettings().getVikingSpeed(), col, line);
            }
        }
        return e;
    }

}
