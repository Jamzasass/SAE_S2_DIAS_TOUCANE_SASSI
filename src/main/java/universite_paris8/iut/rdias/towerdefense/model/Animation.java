package universite_paris8.iut.rdias.towerdefense.model;

public class Animation {
    private static int nId = 0;
    private int id;
    private boolean gameBegining;
    private boolean macronActivated;

    public Animation(boolean aGameBegining, boolean aMacronActivated) {
        this.id = nId;
        nId++;
        this.gameBegining = aGameBegining;
        this.macronActivated = aMacronActivated;
    }

    public int getId() {
        return id;
    }

    public boolean isGameBegining() {
        return gameBegining;
    }

    public boolean isMacronActivated() {
        return macronActivated;
    }
}
