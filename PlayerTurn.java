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

    public void nextTurn() {
        String currentPlayer = lstOfPlayers[currentTurn];
        String playerNumber = currentPlayer.substring(1);

        io.println( "Player " + playerNumber + "'s turn - Specify house number or 'q' to quit: ");

        currentTurn = (currentTurn + 1) % lstOfPlayers.length;
    }
}
