package universite_paris8.iut.rdias.towerdefense.model.actor;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Archer;
import static org.junit.jupiter.api.Assertions.*;

public class TowerJunit {

    private Environnement env;
    private Tower tower;
    private Ground ground;

    // Tests Upgrade
    @Test
    void testUpgradeIncreasesHp() {
        ground = new Ground();
        env = new Environnement(ground);
        // Créer une tour Archer de test
        tower = new Archer(env, 24 , 1.0, 1.0);

        int oldMaxHp = tower.getMaxHp();
        tower.upgrade();
        int newMaxHp = tower.getMaxHp();
        assertTrue(newMaxHp > oldMaxHp, "Le max HP doit augmenter après un upgrade");
    }

    @Test
    void testUpgradeIncreasesDamage() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24 , 1.0, 1.0);

        int oldDmg = tower.getDmg();
        tower.upgrade();
        int newDmg = tower.getDmg();
        assertTrue(newDmg > oldDmg, "Les dégâts doivent augmenter après un upgrade");
    }

    @Test
    void testUpgradeRestoresFullHp() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24 , 1.0, 1.0);

        tower.takeDamage(5);
        int hpBeforeUpgrade = tower.getHp();
        int maxHpBeforeUpgrade = tower.getMaxHp();
        tower.upgrade();
        assertEquals(tower.getMaxHp(), tower.getHp(),
                "Les HP doivent être au maximum après upgrade");
        assertTrue(tower.getMaxHp() > maxHpBeforeUpgrade,
                "Le max HP doit avoir augmenté");
    }

    // Tests coûts des upgrades

    @Test
    void testUpgradeCostCalculation() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24 , 1.0, 1.0);

        int baseCost = tower.getCost();
        int upgradeCost = tower.getUpgradeCost();
        double expectedCost = baseCost * env.getSettings().getUpgradeCostFactor();
        assertEquals((int) expectedCost, upgradeCost,
                "Le coût d'upgrade doit être: coutdeBase x le facteur");
    }

    // Tests Action & Cooldown

    @Test
    void testTickIncreasesCooldown() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24 , 1.0, 1.0);

        int initialCooldown = tower.getCooldown();
        tower.tick();
        int newCooldown = tower.getCooldown();
        assertEquals(initialCooldown + 1, newCooldown,
                "Le cooldown doit augmenter de 1 avec chaque tick");
    }

    @Test
    void testCanActAfterEnoughTicks() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24 , 1.0, 1.0);

        int speedAct = (int) tower.getSpeedAct();
        int requiredTicks = speedAct * 30;
        // Faire les ticks nécessaires
        for (int i = 0; i < requiredTicks; i++) {
            tower.tick();
        }
        assertTrue(tower.canAct(), "La tour doit pouvoir agir après le nombre de ticks suffisant");
    }

    @Test
    void testResetCooldownResetsToZero() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24 , 1.0, 1.0);

        for (int i = 0; i < 100; i++) {
            tower.tick();
        }
        assertTrue(tower.getCooldown() > 0);
        tower.resetCooldown();
        assertEquals(0, tower.getCooldown(),
                "Le cooldown doit être à 0 après reset");
        assertFalse(tower.canAct(),
                "La tour ne doit pas pouvoir agir après reset du cooldown");
    }

    // Tests pour le placement d'une tour

    @Test
    void testCanNotBePlacedOutOfBounds() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24 , 1.0, 1.0);

        assertFalse(tower.canBePlaced(-1, 0), "Hors limites: ligne négative");
        assertFalse(tower.canBePlaced(1000, 0), "Hors limites: ligne trop grande");
        assertFalse(tower.canBePlaced(0, -1), "Hors limites: colonne négative");
        assertFalse(tower.canBePlaced(0, 1000), "Hors limites: colonne trop grande");
    }

    @Test
    void testTowerMustBeOnGrass() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24, 1.0, 1.0);

        boolean canPlaceOnGrass = false;
        for (int line = 0; line < ground.heigth() && !canPlaceOnGrass; line++) {
            for (int col = 0; col < ground.width() && !canPlaceOnGrass; col++) {
                if (ground.isGrass(line, col)) {
                    if (tower.canBePlaced(line, col)) {
                        canPlaceOnGrass = true;
                    }
                }
            }
        }
        assertTrue(canPlaceOnGrass, "la tour peut être placer");
    }

    // Test pour vérifier que la tour peut être placer a côter du chemin
    @Test
    void testTowerCanBeAdjacentToPath() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24 , 1.0, 1.0);

        boolean foundAdjacent = false;
        for (int line = 0; line < ground.heigth() && !foundAdjacent; line++) {
            for (int col = 0; col < ground.width() && !foundAdjacent; col++) {
                if (ground.isGrass(line, col) && tower.isAdjacentToPath(line, col)) {
                    foundAdjacent = true;
                }
            }
        }
        assertTrue(foundAdjacent, "La tour peut être poser à côter d'un chemin");
    }


    // Test de vente de tour

    @Test
    void testSoldRemovesTowerFromEnvironment() {
        ground = new Ground();
        env = new Environnement(ground);
        env.earn(500);

        for (int line = 5; line < 15 && env.getTowers().isEmpty(); line++) {
            for (int col = 5; col < 15 && env.getTowers().isEmpty(); col++) {
                env.createTower(1, col, line);
            }
        }

        assertTrue(env.getTowers().size() > 0, "Une tour doit être créée");
        Tower tower = env.getTowers().get(0);
        tower.sell();
        assertEquals(0, env.getTowers().size(), "La tour doit être supprimée");
    }

    // Test pour savoir si le cooldown se reset après avoir effectuer
    @Test
    void testCooldownResetAfterAction() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24 , 1.0, 1.0);

        int requiredTicks = (int) (tower.getSpeedAct() * 30);
        for (int i = 0; i < requiredTicks; i++) {
            tower.tick();
        }
        assertTrue(tower.canAct(),"il attaque");
        tower.resetCooldown();
        assertFalse(tower.canAct(),"Il ne peut pas attaquer en raison de son cooldown");
        for (int i = 0; i < requiredTicks; i++) {
            tower.tick();
        }
        assertTrue(tower.canAct(), "Après reset et suffisamment de ticks, la tour peut agir de nouveau");
    }

    // Test pour vérifier si l'upgrade fonctionne correctement
    @Test
    void testCanBeUpgradedAtLevel1() {
        ground = new Ground();
        env = new Environnement(ground);
        tower = new Archer(env, 24, 1.0, 1.0);

        assertTrue(tower.canBeUpgraded(), "La tour peut monter de niveau");
        tower.upgrade();
        assertFalse(tower.canBeUpgraded(), "La tour ne peut monter de niveau (impossible de dépasser le lvl 2)");
    }
}
