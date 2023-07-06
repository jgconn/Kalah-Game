package kalah;

import java.util.Map;

public class Movement {
    public Movement(PlayerTurn playerTurn, SeedList seedList) {

    }

    public void moveSeed(int playerInput, int currentTurn, SeedList seedList) {
        // test
        int nextHouse = playerInput;
        if (currentTurn ==  0) {
            moveP1seeds(playerInput, nextHouse, seedList);
        } else {
            moveP2seeds(playerInput, nextHouse, seedList);
        }
    }

    public void moveP1seeds(int playerInput, int nextHouse, SeedList seedList) {
        int seedCount = seedList.getMapP1Seeds(playerInput);
        for (int i = 0; i < seedCount; i++) {
            try {
                seedList.setMapP1Seed(nextHouse + 1, seedList.getMapP1Seeds(nextHouse + 1) + 1);
                nextHouse++;
            }
            catch (NullPointerException e) {
                System.out.println("PPPOOOP");
            }
        }
        seedList.setMapP1Seed(playerInput, 0);
    }

    public void moveP2seeds(int playerInput, int nextHouse, SeedList seedList) {
        int seedCount = seedList.getMapP2Seeds(playerInput);
        for (int i = 0; i < seedCount; i++) {
            try {
                seedList.setMapP2Seed(nextHouse + 1, seedList.getMapP2Seeds(nextHouse + 1) + 1);
                nextHouse++;
            }
            catch (NullPointerException e) {
                System.out.println("PPPOOOP");
            }
        }
        seedList.setMapP2Seed(playerInput, 0);
    }

}
