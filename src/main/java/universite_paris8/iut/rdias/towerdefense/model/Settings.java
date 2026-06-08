package universite_paris8.iut.rdias.towerdefense.model;

//Cette classe permet de stocker toutes les variables (les stats) de toutes nos tours et des ennemies.

public class Settings {

    // --------- towers (ally -> cost)
    // archer
    private int archerDmg;
    private int archerHp;
    private int archerSpeedAttack;
    private int archerRange;
    private int archerCost;

    // ballista
    private int ballistaHp;
    private int ballistaDmg;
    private int ballistaSpeedAttack;
    private int ballistaRange;
    private int ballistaCost;

    // barrack
    private int barrackHp;
    private int barrackSpeedProduction;
    private int barrackSpeed;
    private int barrackCost;

    // bramble
    private double brambleSlowFactor;
    private int brambleSlowDuration;
    private int brambleCost;

    // palissade
    private int palissadeHp;
    private int palissadeCost;

    // sorcererTower
    private int sorcererTowerHp;
    private int sorcererTowerDmg;
    private int sorcererTowerZoneDuration;
    private int sorcererTowerSpeedAttack;
    private int sorcererTowerCost;

    // Soldiers Ally (ally -> cost)
    // knight
    private int knightHp;
    private int knightDmg;
    private double knightSpeed;
    private int knightCost;

    // soliders Enemy (enemy -> deathValue)
    // viking
    private int vikingHp;
    private int vikingDmg;
    private double vikingSpeed;
    private int vikingDeathValue;

    // berserker
    private int berserkerHp;
    private int berserkerDmg;
    private double berserkerSpeed;
    private int berserkerDeathValue;

    // bowmanviking
    private int bowmanvikingHp;
    private int bowmanvikingDmg;
    private double bowmanvikingSpeed;
    private int bowmanvikingDeathValue;
    private int bowmanvikingRange;

    // ramwarrior
    private int ramwarriorHp;
    private int ramwarriorDmg;
    private double ramwarriorSpeed;
    private int ramwarriorDeathValue;

    // shieldwarrior
    private int shieldwarriorHp;
    private int shieldwarriorDmg;
    private double shieldwarriorSpeed;
    private int shieldwarriorDeathValue;


    public Settings() {
        // --------- towers
        // archer
        this.archerHp = 150;
        this.archerDmg = 40;
        this.archerRange = 4;
        this.archerSpeedAttack = 2;
        this.archerCost = 100;

        // ballista
        this.ballistaHp = 300;
        this.ballistaDmg = 30;
        this.ballistaRange = 4;
        this.ballistaSpeedAttack = 3;
        this.ballistaCost = 350;

        // barrack
        this.barrackHp = 250;
        this.barrackSpeedProduction = 10;
        this.barrackSpeed = 0;
        this.barrackCost = 200;

        // bramble
        this.brambleSlowFactor = 0.5;
        this.brambleSlowDuration = 30;
        this.brambleCost = 50;

        // palissade
        this.palissadeHp = 200;
        this.palissadeCost = 300;

        // sorcererTower
        this.sorcererTowerHp = 300;
        this.sorcererTowerDmg = 10;          // dégâts par seconde
        this.sorcererTowerZoneDuration = 3;
        this.sorcererTowerSpeedAttack = 2;
        this.sorcererTowerCost = 500;

        // --------- soldiers ally
        // knight
        this.knightHp = 70;
        this.knightDmg = 10;
        this.knightSpeed = 0.07;
        this.knightCost = 0; // il n'y en aurra surement pas

        // --------- soldiers enemy
        // viking
        this.vikingHp = 70;
        this.vikingDmg = 20;
        this.vikingSpeed = 0.05;
        this.vikingDeathValue = 15;

        // berserker
        this.berserkerHp = 125;
        this.berserkerDmg = 20;
        this.berserkerSpeed = 0.06;
        this.berserkerDeathValue = 45;

        // bowmanviking
        this.bowmanvikingHp = 50;
        this.bowmanvikingDmg = 20;
        this.bowmanvikingSpeed = 0.10;
        this.bowmanvikingDeathValue = 25;
        this.bowmanvikingRange = 4;

        // ramwarrior
        this.ramwarriorHp = 250;
        this.ramwarriorDmg = 100;
        this.ramwarriorSpeed = 0.03;
        this.ramwarriorDeathValue = 80;

        // shieldwarrior
        this.shieldwarriorHp = 250;
        this.shieldwarriorDmg = 20;
        this.shieldwarriorSpeed = 0.03;
        this.shieldwarriorDeathValue = 70;
    }

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

    public int getBowmanvikingHp() {
        return bowmanvikingHp;
    }

    public int getBowmanvikingDmg() {
        return bowmanvikingDmg;
    }

    public double getBowmanvikingSpeed() {
        return bowmanvikingSpeed;
    }

    public int getBowmanvikingDeathValue() {
        return bowmanvikingDeathValue;
    }

    public int getBowmanvikingRange() {
        return bowmanvikingRange;
    }


    public int getRamwarriorHp() {
        return ramwarriorHp;
    }

    public int getRamwarriorDmg() {
        return ramwarriorDmg;
    }

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
}
