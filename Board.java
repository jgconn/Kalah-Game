package kalah;

import com.qualitascorpus.testsupport.IO;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private IO io;

    public Board(IO io, Rules rules, SeedList seedList) {
        this.io = io;
        printBoard(rules, seedList);
    }

    public void printBoard(Rules rules, SeedList seedList) {
        outerBoarder();
        player2Board(rules.getLstOfPlayers(), seedList);
        innerBoarder();
        player1Board(rules.getLstOfPlayers(), seedList);
        outerBoarder();
    }

    public void outerBoarder() {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    public void player2Board(String[] lstOfPlayers, SeedList seedList) {
        //io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");
        Map<Integer, Integer> mapP1 = seedList.getMapP1();
        Map<Integer, Integer> mapP2 = seedList.getMapP2();

        ArrayList<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(mapP2.entrySet());
        Collections.reverse(entryList); // Reverse the entry list

        io.print("| " + lstOfPlayers[1] + " | ");

        int count = 0;

        for (int i = 1; i < 7; i++) {
            Map.Entry<Integer, Integer> entry = entryList.get(i);
            count++;

            System.out.print(entry.getKey() + "[ " + entry.getValue() + "] | ");

            if (count >= 6) {
                break;
            }
        }

        io.println(mapP1.get(7) + "  |");
    }

    public void player1Board(String[] lstOfPlayers, SeedList seedList) {
        Map<Integer, Integer> mapP1 = seedList.getMapP1();
        Map<Integer, Integer> mapP2 = seedList.getMapP2();
        io.print("| " + mapP2.get(7) + "  | ");

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : mapP1.entrySet()) {
            count++;

            System.out.print(entry.getKey() + "[ " + entry.getValue() + "] | ");

            if (count >= 6) {
                break;
            }
        }

        io.println(lstOfPlayers[0] + " |");
    }

    public void innerBoarder() {
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
    }
}
