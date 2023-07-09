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

    public int compareWinner(SeedList seedList, int whoTurn) {
        int checkWinner;
        if (whoTurn == 0) {
            Map<Integer, Integer> hashMap1 = seedList.getMapP1();
            checkWinner = checkWinner(hashMap1, 0);
        } else {
            //System.out.println(whoTurn);
            Map<Integer, Integer> hashMap2 = seedList.getMapP2();
            checkWinner = checkWinner(hashMap2, 1);

        }

        return checkWinner;
    }

    public int checkWinner(Map<Integer, Integer> hashMap, int player) {
        if (player == 0) {
            for (int i = 1; i <= 7; i++) {
                if (i != 7 && hashMap.getOrDefault(i, 0) != 0) {
                    return -1; // Value is not equal to 0, not all values are zero
                }
            }
            return 0; // All values are equal to 0
        }
        if (player == 1) {
            for (int i = 1; i <= 7; i++) {

                if (i != 7 && hashMap.getOrDefault(i, 0) != 0) {
                    return -1; // Value is not equal to 0, not all values are zero
                }
            }
            //System.out.println("MOOBIE 22");
            return 1; // All values are equal to 0
        }
        return -1;
    }

    public void getScores(SeedList seedList, int winner, int totalScore) {
        if (winner == 0) {
            //System.out.println("winner == 0 WINNER");
            int sum = seedList.getMapP2Seeds(7) + totalScore;
            io.println("\tplayer 1:" + seedList.getMapP1Seeds(7));
            io.println("\tplayer 2:" + sum);
            if (sum == seedList.getMapP1Seeds(7)) {
                io.println("A tie!");
            } else {
                io.println("Player 2 wins!");
            }
        } else {
            //System.out.println("winner == 1 WINNER");
            int sum = seedList.getMapP1Seeds(7) + totalScore;
            io.println("\tplayer 1:" + sum);
            io.println("\tplayer 2:" + seedList.getMapP2Seeds(7));
            if (sum == seedList.getMapP2Seeds(7)) {
                io.println("A tie!");
            } else {
                io.println("Player 1 wins!");
            }
        }
    }

    public int addUpScore(SeedList seedList, int winner) {
        int totalScore = 0;
        if (winner == 0) {
            Map<Integer, Integer> hashMap = seedList.getMapP2();
            for (int i = 1; i <= 6; i++) {
                if (hashMap.containsKey(i)) {
                    totalScore += hashMap.get(i);
                }
            }
            return totalScore;
        } else if (winner == 1) {
            Map<Integer, Integer> hashMap = seedList.getMapP1();
            for (int i = 1; i <= 6; i++) {
                if (hashMap.containsKey(i)) {
                    totalScore += hashMap.get(i);
                }
            }
            return totalScore;
        }

        return totalScore;
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

            if (Integer.toString(entry.getValue()).length() == 2) {
                io.print(entry.getKey() + "[" + entry.getValue() + "] | ");
            } else {
                io.print(entry.getKey() + "[ " + entry.getValue() + "] | ");
            }


            if (count >= 6) {
                break;
            }
        }

        if (Integer.toString(mapP1.get(7)).length() == 2) {
            io.println("" + mapP1.get(7) + " |");
        } else {
            io.println(" " + mapP1.get(7) + " |");
        }
    }

    public void player1Board(String[] lstOfPlayers, SeedList seedList) {
        Map<Integer, Integer> mapP1 = seedList.getMapP1();
        Map<Integer, Integer> mapP2 = seedList.getMapP2();
        if (Integer.toString(mapP2.get(7)).length() == 2) {
            io.print("| " + mapP2.get(7) + " | ");
        } else{
            io.print("|  " + mapP2.get(7) + " | ");
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : mapP1.entrySet()) {
            count++;

            if (Integer.toString(entry.getValue()).length() == 2) {
                io.print(entry.getKey() + "[" + entry.getValue() + "] | ");
            } else {
                io.print(entry.getKey() + "[ " + entry.getValue() + "] | ");
            }

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
