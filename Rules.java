package kalah;

public class Rules {
    private int numOfPlayers = 2;
    private String[] lstOfPlayers = {"P1", "P2"};
    private int numOfHouses = 6;
    private int numOfSeeds = 4;

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setLstOfPlayers(String[] lstOfPlayers) {
        this.lstOfPlayers = lstOfPlayers;
    }

    public String[] getLstOfPlayers() {
        return  lstOfPlayers;
    }

    public void setNumOfHouses(int numOfHouses) {
        this.numOfHouses = numOfHouses;
    }

    public int getNumOfHouses() {
        return numOfHouses;
    }

    public void setNumOfSeeds(int numOfSeeds) {
        this.numOfSeeds = numOfSeeds;
    }

    public int getNumOfSeeds() {
        return numOfSeeds;
    }
}
