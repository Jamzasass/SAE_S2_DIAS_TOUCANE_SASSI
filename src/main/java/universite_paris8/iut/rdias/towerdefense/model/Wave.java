package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.*;

public class Wave {
    private Environnement env;
    private int waveIndex;
    private static final int[][] spawnPoints = {{4, 8}, {4, 38}, {4, 60}};
    private static final int[] spawnPointsStartGame = {4, 38};
    private int nbEnemies;
    private int delayBetweenSpawns;
    private boolean macronInflationActivated;
    private int waveCptLap;

    public Wave(Environnement env, int waveIndex) {
        this.env = env;
        this.waveIndex = waveIndex;
        this.nbEnemies = (int)(10 + 20 * Math.log(waveIndex + 1));
        this.delayBetweenSpawns = 3;
        this.waveCptLap = 0;
        if (waveIndex > 4) {
            int random = (int)(Math.random()*4);
            this.macronInflationActivated = random==3;
        }
        if (waveIndex == 15) {
            env.getSettings().multiplierStatsEnemy(1.3);
        } else if (waveIndex == 25) {
            env.getSettings().multiplierStatsEnemy(1.3);
        } else if (waveIndex == 35) {
            env.getSettings().multiplierStatsEnemy(1.3);
        }
    }

    public void waveLoop() {
        if (waveCptLap == 0) {
            env.startingWaveAnimation(); //Animation de début de jeu
        }
        else if (waveCptLap > delayBetweenSpawns*30*4 &&
                waveCptLap % (delayBetweenSpawns*30) == 0 && nbEnemies>0) {
            int[] spawn;
            if (waveIndex <= 1){
                spawn = spawnPointsStartGame;
            }
            else {
                int random = (int)(Math.random() * spawnPoints.length);
                spawn = spawnPoints[random];
            }
            int line = spawn[0];
            int col = spawn[1];

            Enemy e = createNewEnemy(col, line);
            env.addEnemy(e);
            env.incrementId();
            nbEnemies--;
        }
        else if (nbEnemies==0 && waveCptLap % (delayBetweenSpawns*30*2) == 0) {
            if (env.getEnemies().isEmpty()) {
                env.nextWave();
            }
        }
        this.waveCptLap++;

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

    public Enemy createNewEnemy(int col, int line) {
        Enemy e = null;
        int radomSelectEnemy = (int) (Math.random() * 100);

        if (waveIndex == 1) {
            e = new Viking(env, env.getId(), col, line);
        }
        else if (waveIndex <= 3) {
            if (radomSelectEnemy < 30) {
                e = new ArcherViking(env, env.getId(), col, line);
            }
            else {
                e = new Viking(env, env.getId(), col, line);
            }
        }
        else if (waveIndex <= 5) {
            if (radomSelectEnemy < 30) {
                e = new ArcherViking(env, env.getId(), col, line);
            }
            else if (radomSelectEnemy < 50) {
                e = new Berserker(env, env.getId(), col, line);
            }
            else {
                e = new Viking(env,env.getId(), col, line);
            }
        }
        else if (waveIndex <= 8) {
            if (radomSelectEnemy < 10) {
                e = new ShieldViking(env, env.getId(), col, line);
            }
            else if (radomSelectEnemy < 20) {
                e = new ArcherViking(env, env.getId(), col, line);
            }
            else if (radomSelectEnemy < 30) {
                e = new Berserker(env, env.getId(), col, line);
            }
            else {
                e = new Viking(env, env.getId(), col, line);
            }
        }
        else if (waveIndex <= 10) {
            if (radomSelectEnemy < 10) {
                e = new RamWarrior(env, env.getId(), col, line);
            }
            else if (radomSelectEnemy < 15) {
                e = new ShieldViking(env, env.getId(), col, line);
            }
            else if (radomSelectEnemy < 20) {
                e = new ArcherViking(env, env.getId(), col, line);
            }
            else if (radomSelectEnemy < 25) {
                e = new Berserker(env, env.getId(), col, line);
            }
            else {
                e = new Viking(env, env.getId(), col, line);
            }
        }
        return e;
    }

}
