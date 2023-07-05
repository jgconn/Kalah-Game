package kalah;

import com.qualitascorpus.testsupport.IO;

public class PlayerTurn {
    private IO io;
    private String[] lstOfPlayers;
    private int currentTurn = 0;
    public  PlayerTurn(IO io, Rules rules) {
        this.io = io;
        this.lstOfPlayers = rules.getLstOfPlayers();
    }

    public String getPrompt() {
        String currentPlayer = lstOfPlayers[currentTurn];
        String input = io.readFromKeyboard("Player " + currentPlayer + "'s turn - Specify house number or 'q' to quit: ");
        return input;
    }

    public void nextTurn() {
        String currentPlayer = lstOfPlayers[currentTurn];
        //String playerNumber = currentPlayer.substring(1);

        currentTurn = (currentTurn + 1) % lstOfPlayers.length;
        setCurrentTurn(currentTurn);
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}
