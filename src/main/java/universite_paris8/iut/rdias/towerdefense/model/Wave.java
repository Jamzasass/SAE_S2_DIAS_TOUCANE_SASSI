package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.*;

public class Wave {
    private Environnement env;
    private int waveIndex;
    private static final int[][] spanwPoints = {{4, 8}, {4, 38}, {4, 60}};
    private int nbEnemies;
    private int delayBetweenSpawns;
    private boolean macronInflationActivated;

    public Wave(Environnement env, int waveIndex) {
        this.env = env;
        this.waveIndex = waveIndex;
        this.nbEnemies = (int)(10 + 20 * Math.log(waveIndex + 1));
        this.delayBetweenSpawns = 3;
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
        int radomSelectEnemy = (int) (Math.random() * 100);
        if (waveIndex == 1){
            e = new Viking(env, env.getSettings().getVikingHp(), env.getSettings().getArcherDmg(), env.getId(), env.getSettings().getVikingSpeed(), col, line);
        }
        else if (waveIndex <= 2) {
            if (radomSelectEnemy < 30) {
                e = new ArcherViking(env, env.getSettings().getArcherVikingHp(), env.getSettings().getArcherVikingDmg(), env.getId(), env.getSettings().getArcherVikingRange(), col, line, env.getSettings().getArcherVikingSpeed(), env.getSettings().getArcherVikingDeathValue());
            }
            else {
                e = new Viking(env, env.getSettings().getVikingHp(), env.getSettings().getArcherDmg(), env.getId(), env.getSettings().getVikingSpeed(), col, line);
            }
        }
        else if (waveIndex <= 3) {
            if (radomSelectEnemy < 30) {
                e = new ArcherViking(env, env.getSettings().getArcherVikingHp(), env.getSettings().getArcherVikingDmg(), env.getId(), env.getSettings().getArcherVikingRange(), col, line, env.getSettings().getArcherVikingSpeed(), env.getSettings().getArcherVikingDeathValue());
            }
            else if (radomSelectEnemy < 50) {
                e = new Berserker(env, env.getSettings().getBerserkerHp(), env.getSettings().getBerserkerDmg(), env.getId(), col, line, env.getSettings().getArcherVikingSpeed(), env.getSettings().getArcherVikingDeathValue());
            }
            else {
                e = new Viking(env, env.getSettings().getVikingHp(), env.getSettings().getArcherDmg(), env.getId(), env.getSettings().getVikingSpeed(), col, line);
            }
        }
        else if (waveIndex <= 4) {
            if (radomSelectEnemy < 10) {
                e = new ShieldViking(env, env.getSettings().getShieldwarriorHp(), env.getSettings().getShieldwarriorDmg(), env.getId(), col, line, env.getSettings().getShieldwarriorSpeed(), env.getSettings().getShieldwarriorDeathValue());
            }
            else if (radomSelectEnemy < 20) {
                e = new ArcherViking(env, env.getSettings().getArcherVikingHp(), env.getSettings().getArcherVikingDmg(), env.getId(), env.getSettings().getArcherVikingRange(), col, line, env.getSettings().getArcherVikingSpeed(), env.getSettings().getArcherVikingDeathValue());
            }
            else if (radomSelectEnemy < 30) {
                e = new Berserker(env, env.getSettings().getBerserkerHp(), env.getSettings().getBerserkerDmg(), env.getId(), col, line, env.getSettings().getArcherVikingSpeed(), env.getSettings().getArcherVikingDeathValue());
            }
            else {
                e = new Viking(env, env.getSettings().getVikingHp(), env.getSettings().getArcherDmg(), env.getId(), env.getSettings().getVikingSpeed(), col, line);
            }
        }
        else if (waveIndex <= 10) {
            if (radomSelectEnemy < 10) {
                e = new RamWarrior(env, env.getSettings().getShieldwarriorHp(), env.getSettings().getShieldwarriorDmg(), env.getId(), col, line, env.getSettings().getShieldwarriorSpeed(), env.getSettings().getShieldwarriorDeathValue());
            }
            else if (radomSelectEnemy < 15) {
                e = new ShieldViking(env, env.getSettings().getShieldwarriorHp(), env.getSettings().getShieldwarriorDmg(), env.getId(), col, line, env.getSettings().getShieldwarriorSpeed(), env.getSettings().getShieldwarriorDeathValue());
            }
            else if (radomSelectEnemy < 20) {
                e = new ArcherViking(env, env.getSettings().getArcherVikingHp(), env.getSettings().getArcherVikingDmg(), env.getId(), env.getSettings().getArcherVikingRange(), col, line, env.getSettings().getArcherVikingSpeed(), env.getSettings().getArcherVikingDeathValue());
            }
            else if (radomSelectEnemy < 25) {
                e = new Berserker(env, env.getSettings().getBerserkerHp(), env.getSettings().getBerserkerDmg(), env.getId(), col, line, env.getSettings().getArcherVikingSpeed(), env.getSettings().getArcherVikingDeathValue());
            }
            else {
                e = new Viking(env, env.getSettings().getVikingHp(), env.getSettings().getArcherDmg(), env.getId(), env.getSettings().getVikingSpeed(), col, line);
            }
        }
        return e;
    }

}
