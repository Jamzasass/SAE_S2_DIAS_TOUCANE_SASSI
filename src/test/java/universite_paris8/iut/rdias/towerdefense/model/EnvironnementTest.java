package universite_paris8.iut.rdias.towerdefense.model;

import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.rdias.towerdefense.model.Effect;
import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.Viking;

import static org.junit.jupiter.api.Assertions.*;

/*La classe EnvironnementTest contient les tests unitaires
 * pour la classe Environnement.
 * Les tests utilisent JUnit 5.
 */

class EnvironnementTest {

    private Environnement env;
    private Ground ground;

    @BeforeEach
    void setUp() {
        this.ground = new Ground();
        this.env = new Environnement(this.ground);
    }

    @Test
    void testAddAndRemoveEnemy() {
        Enemy enemy = new Viking(env, 1, 5.0, 5.0);
        env.addEnemy(enemy);
        ObservableList<Enemy> enemies = env.getEnemies();
        assertEquals(1, enemies.size());
        assertTrue(enemies.contains(enemy));

        env.delEnemy(enemy);
        assertEquals(0, enemies.size());
    }

    @Test
    void testAddAndRemoveKnight() {
        Knight knight = new Knight(env, 1, 5.0, 5.0);
        env.addKnight(knight);
        ObservableList<Knight> knights = env.getKnights();
        assertEquals(1, knights.size());
        assertTrue(knights.contains(knight));

        env.delKnight(knight);
        assertEquals(0, knights.size());
    }

    @Test
    void testAddAndRemoveEffect() {
        Actor target = new Viking(env, 1, 5.0, 5.0);
        Effect effect = new Projectile(env, 1.0, 1.0, 10, target);
        env.addEffect(effect);
        ObservableList<Effect> effects = env.getEffects();
        assertEquals(1, effects.size());
        assertTrue(effects.contains(effect));

        env.delEffect(effect);
        //on appelle loop() pour supprimer l'effect
        env.loop();
        assertEquals(0, effects.size());
    }

    @Test
    void testEarnMoney() {
        int initialBalance = env.getBalanceProperty().get();
        env.earn(500);
        assertEquals(initialBalance + 500, env.getBalanceProperty().get());
    }

    @Test
    void testTakeDamageCastle() {
        int initialHp = env.getCastle().getHp();
        env.takeDmgCastle(10);
        assertEquals(initialHp - 10, env.getCastle().getHp());
        // Vérifier que les pv du chateau ne peuvent être endetter
        env.takeDmgCastle(10000);
        assertEquals(0, env.getCastle().getHp());
    }

    @Test
    void testAddDyingActorPlusLoop() {
        Enemy viking = new Viking(env, 1, 2.0, 3.0);
        env.addEnemy(viking);
        assertEquals(1, env.getEnemies().size());
        viking.die();

        assertFalse(viking.isLiving());

        assertTrue(env.getEnemies().contains(viking));

        // Lancer un tour
        env.loop();

        assertTrue(env.getEnemies().isEmpty());
    }

    @Test
    void testNextWave() {
        env.addEnemy(new Viking(env, 1, 0, 0));
        env.addKnight(new Knight(env, 2, 0, 0));

        int waveIndexInitial = env.getWaveIndexProperty().get();
        env.nextWave();

        assertEquals(waveIndexInitial + 1, env.getWaveIndexProperty().get());
        assertTrue(env.getEnemies().isEmpty());
        assertTrue(env.getKnights().isEmpty());
    }

    @Test
    void testGetIdPlusIncrementation() {
        int id1 = env.getId();
        int id2 = env.getId();
        assertNotEquals(id1, id2);
        assertTrue(id2 > id1);
    }
}