package kalah;

import com.qualitascorpus.testsupport.IO;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private IO io;

    public Board(IO io, Rules rules, SeedList seedList) {
        this.io = io;

        outerBoarder();
        player2Board(rules.getLstOfPlayers(), seedList.getMapP2());
        innerBoarder();
        player1Board(rules.getLstOfPlayers(), seedList.getMapP1());
        outerBoarder();
    }

    public void printBoard(Rules rules, SeedList seedList) {
        outerBoarder();
        player2Board(rules.getLstOfPlayers(), seedList.getMapP2());
        innerBoarder();
        player1Board(rules.getLstOfPlayers(), seedList.getMapP1());
        outerBoarder();
    }

    public void outerBoarder() {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    public void player2Board(String[] lstOfPlayers, Map<Integer, Integer> mapP2) {
        //io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");

        io.print("| " + lstOfPlayers[1] + " | ");

        for (Map.Entry<Integer, Integer> set :
            mapP2.entrySet()) {
            io.print(set.getKey() + "[ " + set.getValue() + "] | ");
        }

        io.println("0  |");
    }

    public void player1Board(String[] lstOfPlayers, Map<Integer, Integer> mapP1) {
        io.print("| 0  | ");

        for (Map.Entry<Integer, Integer> set :
        mapP1.entrySet()) {
            io.print(set.getKey() + "[ " + set.getValue() + "] | ");
        }

        io.println(lstOfPlayers[0] + " |");
    }

    public void innerBoarder() {
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
    }
}
