package universite_paris8.iut.rdias.towerdefense.model;

public class Animation {
    private static int nId = 0;
    private int id;
    private boolean macronActivated;

    public Animation(boolean aMacronActivated) {
        this.id = nId;
        nId++;
        this.macronActivated = aMacronActivated;
    }

    public int getId() {
        return id;
    }
    public boolean isMacronActivated() {
        return macronActivated;
    }
}
