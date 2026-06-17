package universite_paris8.iut.rdias.towerdefense.model.actor.Environnement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Settings {

    public static double upgradeFactor = 2.0;
    public static double upgradeCostFactor = 1.5;

    // ARCHER TOWER
    public static int archerDmg = 45;
    public static int archerHp = 150;
    public static int archerSpeedAttack = 2;
    public static int archerRange = 4;
    public static IntegerProperty archerCost = new SimpleIntegerProperty(125);

    // BALLISTA TOWER
    public static int ballistaHp = 325;
    public static int ballistaDmg = 70;
    public static int ballistaSpeedAttack = 2;
    public static int ballistaRange = 8;
    public static IntegerProperty ballistaCost = new SimpleIntegerProperty(550);

    // BARRACK TOWER
    public static int barrackHp = 300;
    public static int barrackSpeedProduction = 6;
    public static int barrackSpeed = 0;
    public static IntegerProperty barrackCost = new SimpleIntegerProperty(275);
    public static int barrackNbKnightMax = 3;

    // BRAMBLE TOWER
    public static double brambleSlowFactor = 0.6;
    public static int brambleSlowDuration = 40;
    public static IntegerProperty brambleCost = new SimpleIntegerProperty(75);

    // PALISSADE TOWER
    public static int palissadeHp = 350;
    public static IntegerProperty palissadeCost = new SimpleIntegerProperty(300);

    // SORCERER TOWER
    public static int sorcererTowerHp = 300;
    public static int sorcererTowerDmg = 60;
    public static double sorcererRadiusBlow = 5;
    public static int sorcererTowerSpeedAttack = 3;
    public static IntegerProperty sorcererTowerCost = new SimpleIntegerProperty(750);
    public static int sorcererTowerRange = 6;

    // KNIGHT
    public static int knightHp = 60;
    public static int knightDmg = 35;
    public static double knightSpeed = 0.10;
    public static int knightCost = 0;
    public static int knightSpeedAct = 2;

    // VIKING
    public static int vikingHp = 60;
    public static int vikingDmg = 25;
    public static double vikingSpeed = 0.06;
    public static int vikingDeathValue = 20;
    public static int vikingSpeedAct = 2;

    // BERSERKER
    public static int berserkerHp = 130;
    public static int berserkerDmg = 35;
    public static double berserkerSpeed = 0.08;
    public static int berserkerDeathValue = 55;
    public static int berserkerSpeedAct = 2;

    // ARCHER VIKING
    public static int archerVikingHp = 55;
    public static int archerVikingDmg = 25;
    public static double archerVikingSpeed = 0.07;
    public static int archerVikingDeathValue = 35;
    public static int archerVikingRange = 4;
    public static int archerSpeedAct = 2;

    // BATTERING RAM
    public static int batteringRamHp = 300;
    public static int batteringRamDmg = 65;
    public static double batteringRamSpeed = 0.05;
    public static int batteringRamDeathValue = 100;
    public static int batteringRamSpeedAct = 2;

    // SHIELD VIKING
    public static int shieldwarriorHp = 280;
    public static int shieldwarriorDmg = 30;
    public static double shieldwarriorSpeed = 0.05;
    public static int shieldwarriorDeathValue = 85;
    public static int shieldwarriorSpeedAct = 2;

    // ARCHER TOWER GETTERS
    public int getArcherDmg() { return archerDmg; }
    public int getArcherHp() { return archerHp; }
    public int getArcherSpeedAttack() { return archerSpeedAttack; }
    public int getArcherCost() { return archerCost.getValue(); }
    public IntegerProperty getArcherCostProperty() { return archerCost; }
    public int getArcherRange() { return archerRange; }

    // BALLISTA TOWER GETTERS
    public int getBallistaHp() { return ballistaHp; }
    public int getBallistaSpeedAttack() { return ballistaSpeedAttack; }
    public int getBallistaDmg() { return ballistaDmg; }
    public int getBallistaRange() { return ballistaRange; }
    public int getBallistaCost() { return ballistaCost.getValue(); }
    public IntegerProperty getBallistaCostProperty() { return ballistaCost; }

    // BARRACK TOWER GETTERS
    public int getBarrackHp() { return barrackHp; }
    public int getBarrackSpeedProduction() { return barrackSpeedProduction; }
    public int getBarrackSpeed() { return barrackSpeed; }
    public int getBarrackCost() { return barrackCost.getValue(); }
    public IntegerProperty getBarrackCostProperty() { return barrackCost; }
    public int getBarrackNbKnightMax() { return barrackNbKnightMax; }

    // BRAMBLE TOWER GETTERS
    public double getBrambleSlowFactor() { return brambleSlowFactor; }
    public int getBrambleSlowDuration() { return brambleSlowDuration; }
    public int getBrambleCost() { return brambleCost.getValue(); }
    public IntegerProperty getBrambleCostProperty() { return brambleCost; }

    // PALISSADE TOWER GETTERS
    public int getPalissadeHp() { return palissadeHp; }
    public int getPalissadeCost() { return palissadeCost.getValue(); }
    public IntegerProperty getPalissadeCostProperty() { return palissadeCost; }

    // SORCERER TOWER GETTERS
    public int getSorcererTowerHp() { return sorcererTowerHp; }
    public int getSorcererTowerDmg() { return sorcererTowerDmg; }
    public double getSorcererRadiusBlow() { return sorcererRadiusBlow; }
    public int getSorcererTowerSpeedAttack() { return sorcererTowerSpeedAttack; }
    public int getSorcererRange() { return sorcererTowerRange; }
    public int getSorcererTowerCost() { return sorcererTowerCost.getValue(); }
    public IntegerProperty getSorcererTowerCostProperty() { return sorcererTowerCost; }

    // KNIGHT GETTERS
    public int getKnightHp() { return knightHp; }
    public int getKnightDmg() { return knightDmg; }
    public double getKnightSpeed() { return knightSpeed; }
    public int getKnightCost() { return knightCost; }
    public int getKnightSpeedAct() { return knightSpeedAct; }

    // VIKING GETTERS
    public int getVikingHp() { return vikingHp; }
    public int getVikingDmg() { return vikingDmg; }
    public double getVikingSpeed() { return vikingSpeed; }
    public int getVikingDeathValue() { return vikingDeathValue; }
    public int getVikingSpeedAct() { return vikingSpeedAct; }

    // BERSERKER GETTERS
    public int getBerserkerHp() { return berserkerHp; }
    public int getBerserkerDmg() { return berserkerDmg; }
    public double getBerserkerSpeed() { return berserkerSpeed; }
    public int getBerserkerDeathValue() { return berserkerDeathValue; }
    public int getBerserkerSpeedAct() { return berserkerSpeedAct; }

    // ARCHER VIKING GETTERS
    public int getArcherVikingHp() { return archerVikingHp; }
    public int getArcherVikingDmg() { return archerVikingDmg; }
    public double getArcherVikingSpeed() { return archerVikingSpeed; }
    public int getArcherVikingDeathValue() { return archerVikingDeathValue; }
    public int getArcherVikingRange() { return archerVikingRange; }
    public int getArcherSpeedAct() { return archerSpeedAct; }

    // BATTERING RAM GETTERS
    public int getBatteringRamHp() { return batteringRamHp; }
    public int getBatteringRamDmg() { return batteringRamDmg; }
    public double getBatteringRamSpeed() { return batteringRamSpeed; }
    public int getBatteringRamDeathValue() { return batteringRamDeathValue; }
    public int getBatteringRamSpeedAct() { return batteringRamSpeedAct; }

    // SHIELD WARRIOR GETTERS
    public int getShieldwarriorHp() { return shieldwarriorHp; }
    public int getShieldwarriorDmg() { return shieldwarriorDmg; }
    public double getShieldwarriorSpeed() { return shieldwarriorSpeed; }
    public int getShieldwarriorDeathValue() { return shieldwarriorDeathValue; }
    public int getShieldwarriorSpeedAct() { return shieldwarriorSpeedAct; }

    public static void multiplierStatsEnemy(double percentIncrease) {
        double fullMultiplier = 1.0 + percentIncrease;
        double halfMultiplier = 1.0 + (percentIncrease / 2);

        vikingHp = (int) (vikingHp * fullMultiplier);
        vikingDmg = (int) (vikingDmg * fullMultiplier);
        vikingSpeed = vikingSpeed * halfMultiplier;
        vikingDeathValue = (int) (vikingDeathValue * halfMultiplier);

        berserkerHp = (int) (berserkerHp * fullMultiplier);
        berserkerDmg = (int) (berserkerDmg * fullMultiplier);
        berserkerSpeed = berserkerSpeed * halfMultiplier;
        berserkerDeathValue = (int) (berserkerDeathValue * halfMultiplier);

        shieldwarriorHp = (int) (shieldwarriorHp * fullMultiplier);
        shieldwarriorDmg = (int) (shieldwarriorDmg * fullMultiplier);
        shieldwarriorSpeed = shieldwarriorSpeed * halfMultiplier;
        shieldwarriorDeathValue = (int) (shieldwarriorDeathValue * halfMultiplier);

        batteringRamHp = (int) (batteringRamHp * fullMultiplier);
        batteringRamDmg = (int) (batteringRamDmg * fullMultiplier);
        batteringRamSpeed = batteringRamSpeed * halfMultiplier;
        batteringRamDeathValue = (int) (batteringRamDeathValue * halfMultiplier);

        archerVikingHp = (int) (archerVikingHp * fullMultiplier);
        archerVikingDmg = (int) (archerVikingDmg * fullMultiplier);
        archerVikingSpeed = archerVikingSpeed * halfMultiplier;
        archerVikingDeathValue = (int) (archerVikingDeathValue * halfMultiplier);
    }

    public void inflation() {
        vikingDeathValue = (int) (vikingDeathValue * 0.80);
        shieldwarriorDeathValue = (int) (shieldwarriorDeathValue * 0.80);
        batteringRamDeathValue = (int) (batteringRamDeathValue * 0.80);
        archerVikingDeathValue = (int) (archerVikingDeathValue * 0.80);
        berserkerDeathValue = (int) (berserkerDeathValue * 0.80);

        archerCost.setValue((int) (archerCost.getValue() * 1.20));
        ballistaCost.setValue((int) (ballistaCost.getValue() * 1.20));
        barrackCost.setValue((int) (barrackCost.getValue() * 1.20));
        brambleCost.setValue((int) (brambleCost.getValue() * 1.20));
        palissadeCost.setValue((int) (palissadeCost.getValue() * 1.20));
        sorcererTowerCost.setValue((int) (sorcererTowerCost.getValue() * 1.20));
    }

    public void disinflation() {
        vikingDeathValue = (int) (vikingDeathValue / 0.80);
        shieldwarriorDeathValue = (int) (shieldwarriorDeathValue / 0.80);
        batteringRamDeathValue = (int) (batteringRamDeathValue / 0.80);
        archerVikingDeathValue = (int) (archerVikingDeathValue / 0.80);
        berserkerDeathValue = (int) (berserkerDeathValue / 0.80);

        archerCost.setValue((int) (archerCost.getValue() / 1.20));
        ballistaCost.setValue((int) (ballistaCost.getValue() / 1.20));
        barrackCost.setValue((int) (barrackCost.getValue() / 1.20));
        brambleCost.setValue((int) (brambleCost.getValue() / 1.20));
        palissadeCost.setValue((int) (palissadeCost.getValue() / 1.20));
        sorcererTowerCost.setValue((int) (sorcererTowerCost.getValue() / 1.20));
    }

    public double getUpgradeFactor() { return upgradeFactor; }
    public double getUpgradeCostFactor() { return upgradeCostFactor; }
}