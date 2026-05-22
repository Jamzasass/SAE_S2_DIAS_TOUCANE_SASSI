package universite_paris8.iut.rdias.towerdefense.model;

public abstract class Soldier extends Actor{
    private double speed;

    public Soldier (int sHp, int sDmg, int sId, double sRange, double sX, double sY, double sSpeed){
        super(sHp, sDmg, sId, sRange, sX, sY);
        this.speed = sSpeed;
    }

    public void move(){
        double random = (Math.random());
        setX(getX()+ speed*(random));
        setY(getY()+ speed*(1-random));
    }

    public double getSpeed(){
        return speed;
    }
}
