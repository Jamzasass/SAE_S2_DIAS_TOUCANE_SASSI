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

        if (futureX>0 && futureY>0 && futureY<env.getGround().heigth() && futureX<env.getGround().width()) {
            if (env.getGround().isPath((int)futureY, (int)futureX)) {
                setX(futureX);
                setY(futureY);
            }
        }
        else
            this.changeDirection();


    }

    public double getSpeed(){
        return speed;
    }

    public void changeDirection() {
        double randomX = (Math.random()*2)-1;
        double randomY = (Math.random()*2)-1;
        if (randomX<0)
            directionX = (-1-randomX);
        else
            directionX = (1-randomX);

        if (randomY<0)
            directionY = (-1-randomY);
        else
            directionY = (1-randomY);


    }
}
