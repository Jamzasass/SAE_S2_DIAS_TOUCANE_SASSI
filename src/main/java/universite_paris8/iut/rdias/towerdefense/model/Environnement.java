package universite_paris8.iut.rdias.towerdefense.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private final Ground ground;
    private final ObservableList<Enemy> enemies; // liste observable d'ennemis
    private static int cptSpawn = 0;
    private int delaySpawn = 60;


    public Environnement(Ground ground) {
        this.ground = ground;
        this.enemies = FXCollections.observableArrayList();
        int path = ground.heigth() - 1;
        enemies.add(new Vikings(1,0,0));
    }

    public void add(Enemy e){this.enemies.add(e);}

    public void unTour() {  // méthode unTour()
        cptSpawn++;
        if (cptSpawn >= delaySpawn) {
            enemies.add(new Vikings(1, 0, 0));
            cptSpawn = 0;
        }
        for (Enemy e : enemies) {
            e.move();
        }
        enemies.removeIf(e -> e.getX() >= ground.width() - 1);
    }

    public ObservableList<Enemy> getEnemies() {
        return enemies;
    }
}
