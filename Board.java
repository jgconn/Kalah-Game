package kalah;

import com.qualitascorpus.testsupport.IO;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private IO io;
    private Rules rules;
    private SeedList seedList2;

    public Board(IO io, Rules rules, SeedList seedList) {
        this.io = io;
        this.rules = rules;
        this.seedList2 = seedList;
        printBoard();
    }

    public void printBoard() {
        outerBoarder();
        player2Board();
        innerBoarder();
        player1Board();
        outerBoarder();
    }

    public int compareWinner(int whoTurn) {
        int checkWinner;
        int checkPlayer;
        Map<Integer, Integer> hashMap;

        if (whoTurn == 0) {
            hashMap = seedList2.getMapP1();
            checkPlayer = 0;
        } else {
            hashMap = seedList2.getMapP2();
            checkPlayer = 1;
        }

        checkWinner = checkWinner(hashMap, checkPlayer);

        return checkWinner;
    }

    private int checkWinner(Map<Integer, Integer> hashMap, int player) {
        boolean allValuesZero = true;
        for (int i = 1; i < 7; i++) {
            if (hashMap.getOrDefault(i, 0) != 0) {
                allValuesZero = false;
                break;
            }
        }

        if (allValuesZero) {
            if (player == 0) {
                return 0; // Player 0 wins
            } else if (player == 1) {
                return 1; // Player 1 wins
            }
        }

        return -1; // No winner
    }

    public void getScores(int winner, int totalScore) {
        int player1Score = seedList2.getMapP1Seeds(7);
        int player2Score = seedList2.getMapP2Seeds(7);

        if (winner == 0) {
            player2Score += totalScore;
            io.println("\tplayer 1:" + player1Score);
            io.println("\tplayer 2:" + player2Score);
        } else if (winner == 1) {
            player1Score += totalScore;
            io.println("\tplayer 1:" + player1Score);
            io.println("\tplayer 2:" + player2Score);
        }

        if (player1Score == player2Score) {
            io.println("A tie!");
        } else if (player1Score > player2Score) {
            io.println("Player 1 wins!");
        } else if (player2Score > player1Score) {
            io.println("Player 2 wins!");
        }
    }

    public int addUpScore(int winner) {
        int sumScore = 0;
        Map<Integer, Integer> hashMap = null;
        
        if (winner == 0) {
            hashMap = seedList2.getMapP2();
        } else if (winner == 1) {
            hashMap = seedList2.getMapP1();
        }

        for (int i = 1; i <= 6; i++) {
            if (hashMap.containsKey(i)) {
                sumScore += hashMap.get(i);
            }
        }
        return sumScore;
    }

    public void outerBoarder() {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    public void player2Board() {
        //io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");
        String[] lstOfPlayers = rules.getLstOfPlayers();

        Map<Integer, Integer> mapP1 = seedList2.getMapP1();
        Map<Integer, Integer> mapP2 = seedList2.getMapP2();

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

    public void player1Board() {
        String[] lstOfPlayers = rules.getLstOfPlayers();

        Map<Integer, Integer> mapP1 = seedList2.getMapP1();
        Map<Integer, Integer> mapP2 = seedList2.getMapP2();

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
