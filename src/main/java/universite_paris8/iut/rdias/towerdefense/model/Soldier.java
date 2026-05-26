package universite_paris8.iut.rdias.towerdefense.model;

public abstract class Soldier extends Actor{
    private double speed;

    private double directionX;
    private double directionY;


    public Soldier (int sHp, int sDmg, int sId, double sRange, double sX, double sY, double sSpeed){
        super(sHp, sDmg, sId, sRange, sX, sY);
        this.speed = sSpeed;
        this.directionX = 0;
        this.directionY = 0;
    }

    public void move(Environnement env){
        double futureX = getX() + speed*(directionX);
        double futureY = getY() + speed*(directionY);

        setX(futureX);
        setY(futureY);

    }

    public double getSpeed(){
        return speed;
    }

    public void changeDirection() {
        double random = (Math.random()*2)-1;
        if (random<0) {
            directionY = (-1-random);
        }
        else
            directionY = (1-random);

        directionX = random;

    }
}
