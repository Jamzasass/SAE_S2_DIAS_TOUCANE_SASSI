package universite_paris8.iut.rdias.towerdefense.model;

//Cette classe permet de stocker toutes les variables (les stats) de toutes nos tours et des ennemies.

/*La classe Settings permet de centraliser tous les paramètres du jeu
 * pour faciliter l'équilibrage et les modifications.
 * Elle nous donne une méthode multiplierStatsEnemy() pour
 * augmenter la difficulté des vagues au fil du temps et facilement.
 * Un settings a comme attribut:
 * -Les facteurs d'amélioration (upgradeFactor et upgradeCostFactor)
 * -Les statistiques de toutes les tours alliées
 * -Les statistiques de tous les soldats alliés (knight)
 * -Les statistiques de tous les ennemis
 */
public class Settings {

    //UpgradeSetting
    public static double upgradeFactor = 2;
    public static double upgradeCostFactor = 0.5;

    // towers (ally -> cost)
    // archer
    public static int archerDmg = 40;
    public static int archerHp = 150;
    public static int archerSpeedAttack = 2;
    public static int archerRange = 4;
    public static int archerCost = 100;

    // ballista
    public static int ballistaHp = 300;
    public static int ballistaDmg = 30;
    public static int ballistaSpeedAttack = 3;
    public static int ballistaRange = 6;
    public static int ballistaCost = 350;

    // barrack
    public static int barrackHp = 250;
    public static int barrackSpeedProduction = 8;
    public static int barrackSpeed = 0;
    public static int barrackCost = 200;

    // bramble
    public static double brambleSlowFactor = 0.5;
    public static int brambleSlowDuration = 30;
    public static int brambleCost = 50;

    // palissade
    public static int palissadeHp = 300;
    public static int palissadeCost = 300;

    // sorcererTower
    public static int sorcererTowerHp = 300;
    public static int sorcererTowerDmg = 50;
    public static int sorcererTowerZoneDuration = 3;
    public static int sorcererTowerSpeedAttack = 4;
    public static int sorcererTowerCost = 500;
    public static int sorcererTowerRange = 8;

    // Soldiers Ally (ally -> cost)
    // knight
    public static int knightHp = 150;
    public static int knightDmg = 25;
    public static double knightSpeed = 0.08;
    public static int knightCost = 0;

    // soliders Enemy (enemy -> deathValue)
    // viking
    public static int vikingHp = 60;
    public static int vikingDmg = 20;
    public static double vikingSpeed = 0.05;
    public static int vikingDeathValue = 15;

    // berserker
    public static int berserkerHp = 125;
    public static int berserkerDmg = 20;
    public static double berserkerSpeed = 0.07;
    public static int berserkerDeathValue = 45;

    // archerViking
    public static int archerVikingHp = 50;
    public static int archerVikingDmg = 20;
    public static double archerVikingSpeed = 0.06;
    public static int archerVikingDeathValue = 25;
    public static int archerVikingRange = 4;

    // ramwarrior
    public static int ramwarriorHp = 250;
    public static int ramwarriorDmg = 100;
    public static double ramwarriorSpeed = 0.04;
    public static int ramwarriorDeathValue = 80;

    // shieldwarrior
    public static int shieldwarriorHp = 250;
    public static int shieldwarriorDmg = 20;
    public static double shieldwarriorSpeed = 0.04;
    public static int shieldwarriorDeathValue = 70;

    public int getArcherDmg() {
        return archerDmg;
    }

    public int getArcherHp() {
        return archerHp;
    }

    public int getArcherSpeedAttack() {
        return archerSpeedAttack;
    }

    public int getArcherCost() {
        return archerCost;
    }

    public int getArcherRange() {
        return archerRange;
    }

    public int getBallistaHp() {
        return ballistaHp;
    }

    public int getBallistaSpeedAttack() {
        return ballistaSpeedAttack;
    }

    public int getBallistaDmg() {
        return ballistaDmg;
    }

    public int getBallistaRange() {
        return ballistaRange;
    }

    public int getBallistaCost() {
        return ballistaCost;
    }

    public int getBarrackHp() {
        return barrackHp;
    }

    public int getBarrackSpeedProduction() {
        return barrackSpeedProduction;
    }

    public int getBarrackSpeed() {
        return barrackSpeed;
    }

    public int getBarrackCost() {
        return barrackCost;
    }

    public double getBrambleSlowFactor() {
        return brambleSlowFactor;
    }

    public int getBrambleSlowDuration() {
        return brambleSlowDuration;
    }

    public int getBrambleCost() {
        return brambleCost;
    }

    public int getPalissadeHp() {
        return palissadeHp;
    }

    public int getPalissadeCost() {
        return palissadeCost;
    }

    public int getSorcererTowerHp() {
        return sorcererTowerHp;
    }

    public int getSorcererTowerDmg() {
        return sorcererTowerDmg;
    }

    public int getSorcererTowerZoneDuration() {
        return sorcererTowerZoneDuration;
    }

    public int getSorcererTowerSpeedAttack() {
        return sorcererTowerSpeedAttack;
    }

    public int getSorcererRange() {return sorcererTowerRange;}  

    public int getSorcererTowerCost() {
        return sorcererTowerCost;
    }

    public int getKnightHp() {
        return knightHp;
    }

    public int getKnightDmg() {
        return knightDmg;
    }

    public double getKnightSpeed() {
        return knightSpeed;
    }

    public int getKnightCost() {
        return knightCost;
    }

    public int getVikingHp() {
        return vikingHp;
    }

    public int getVikingDmg() {
        return vikingDmg;
    }

    public double getVikingSpeed() {
        return vikingSpeed;
    }

    public int getVikingDeathValue() {
        return vikingDeathValue;
    }

    public int getBerserkerHp() {
        return berserkerHp;
    }

    public int getBerserkerDmg() {
        return berserkerDmg;
    }

    public double getBerserkerSpeed() {
        return berserkerSpeed;
    }

    public int getBerserkerDeathValue() {
        return berserkerDeathValue;
    }

    public int getArcherVikingHp() {
        return archerVikingHp;
    }

    public int getArcherVikingDmg() {
        return archerVikingDmg;
    }

    public double getArcherVikingSpeed() {
        return archerVikingSpeed;
    }

    public int getArcherVikingDeathValue() {
        return archerVikingDeathValue;
    }

    public int getArcherVikingRange() {
        return archerVikingRange;
    }

    public int getRamwarriorHp() {
        return ramwarriorHp;
    }

    public int getRamwarriorDmg() {return ramwarriorDmg;}

    public double getRamwarriorSpeed() {
        return ramwarriorSpeed;
    }

    public int getRamwarriorDeathValue() {
        return ramwarriorDeathValue;
    }

    public int getShieldwarriorHp() {
        return shieldwarriorHp;
    }

    public int getShieldwarriorDmg() {
        return shieldwarriorDmg;
    }

    public double getShieldwarriorSpeed() {
        return shieldwarriorSpeed;
    }

    public int getShieldwarriorDeathValue() {
        return shieldwarriorDeathValue;
    }

    public static void multiplierStatsEnemy(double factor) {

        double halfFactor = 1 + (factor/2);
        factor = 1 + factor;

        // archerViking
        archerVikingDmg = (int) (archerVikingDmg * factor);
        archerVikingHp = (int) (archerVikingHp * factor);
        archerVikingSpeed = (int) (archerVikingSpeed * halfFactor);
        archerVikingDeathValue = (int) (archerVikingDeathValue * halfFactor);

        // berserker
        berserkerDmg = (int) (berserkerDmg * factor);
        berserkerHp = (int) (berserkerHp * factor);
        berserkerSpeed = (int) (berserkerSpeed * halfFactor);
        berserkerDeathValue = (int) (berserkerDeathValue * halfFactor);

        // shieldWarrior
        shieldwarriorHp = (int) (shieldwarriorHp * factor);
        shieldwarriorDmg = (int) (shieldwarriorDmg * factor);
        shieldwarriorSpeed = (int) (shieldwarriorSpeed * halfFactor);
        shieldwarriorDeathValue = (int) (shieldwarriorDeathValue * halfFactor);

        // ramWarrior
        ramwarriorHp = (int) (ramwarriorHp * factor);
        ramwarriorDmg = (int) (ramwarriorDmg * factor);
        ramwarriorSpeed = (int) (ramwarriorSpeed * halfFactor);
        ramwarriorDeathValue = (int) (ramwarriorDeathValue * halfFactor);

        // viking
        vikingHp = (int) (vikingHp * factor);
        vikingDmg = (int) (vikingDmg * factor);
        vikingSpeed = (int) (vikingSpeed * halfFactor);
        vikingDeathValue = (int) (vikingDeathValue * halfFactor);
    }

    public double getUpgradeFactor() {
        return upgradeFactor;
    }

    public double getUpgradeCostFactor() {
        return upgradeCostFactor;
    }
}

