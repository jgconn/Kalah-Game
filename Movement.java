package kalah;

import java.util.Map;

public class Movement {
    private boolean rollOver = false;
    private int rollOverSeed = 0;
    public Movement(PlayerTurn playerTurn, SeedList seedList) {

    }

    public void moveSeed(int playerInput, PlayerTurn playerTurn, SeedList seedList) {
        // test
        int currentTurn = playerTurn.getCurrentTurn();
        Runnable nextTurnFunction = playerTurn::nextTurn;

        int nextHouse = playerInput;
        if (currentTurn ==  0) {
            moveP1seeds(playerInput, nextHouse, seedList, nextTurnFunction);
        } else {
            moveP2seeds(playerInput, nextHouse, seedList);
        }
    }

    public void moveP1seeds(int playerInput, int nextHouse, SeedList seedList, Runnable nextTurnFunction) {
        int seedCount = seedList.getMapP1Seeds(playerInput);
        for (int i = 0; i < seedCount; i++) {
            try {
                seedList.setMapP1Seed(nextHouse + 1, seedList.getMapP1Seeds(nextHouse + 1) + 1);
                nextHouse++;
            }
            catch (NullPointerException e) {
                rollOver = true;
                rollOverSeed = i;
            }
        }

        if (rollOver) {
            moveP2seeds( 1, 0, seedList);
        }

        seedList.setMapP1Seed(playerInput, 0);

        if (seedList.getMapP1Seeds(nextHouse) > 1 || nextHouse > 7) {
            nextTurnFunction.run();
        }
        rollOver = false;
    }

    public void moveP2seeds(int playerInput, int nextHouse, SeedList seedList) {
        int seedCount = seedList.getMapP2Seeds(playerInput);

        if (rollOver){
            seedCount = rollOverSeed;
            System.out.println(nextHouse);
        }

        for (int i = 0; i < seedCount; i++) {
            try {
                seedList.setMapP2Seed(nextHouse + 1, seedList.getMapP2Seeds(nextHouse + 1) + 1);
                nextHouse++;
            }
            catch (NullPointerException e) {
                System.out.println("PPPOOOP");
            }
        }
        if (rollOver = false) {
            seedList.setMapP2Seed(playerInput, 0);
        }
        rollOver = false;
    }

}
