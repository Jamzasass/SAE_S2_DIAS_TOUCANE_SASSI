package universite_paris8.iut.rdias.towerdefense.model;



public class Bramble extends Tower{

    private static final double slowFactor = 0.5;
    private static final int slowDuration =  30;

    public Bramble(Environnement env, int brambleId, double brambleX, double brambleY, double tSpeedAttack, int cost) {
        super(env, 0, 0, brambleId, 0, brambleX, brambleY, 1.0, 50);
    }

    @Override
    public void act(Environnement env) {
        for (Enemy e : env.getEnemies()) {
            if (onTile(e)) {
                e.applySlow(slowFactor, slowDuration);
            }
        }
    }

    private boolean onTile(Enemy e){
        int enemyX = (int) e.getX();
        int enemyY = (int) e.getY();
        int brambleX = (int) getX();
        int brambleY = (int) getY();
        return enemyX == brambleX && enemyY == brambleY;
    }

    @Override
    public boolean isLiving(){
        return true;
    }

    @Override
    public void upgrade() {
        System.out.println("pas d'upgrade");
    }

    @Override
    public void takeDamage(int amount){
        System.out.println("Indestructible");
    }
}
