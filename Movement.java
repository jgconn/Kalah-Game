package kalah;

import java.util.Map;

public class Movement {
    private int currentTurn;
    private int playerInput;
    private int seedCount = 0;
    private int overFlow = 0;
    private int incrementHouse = 0;
    private boolean wipeInput = false;
    private int whoTurn;
    private int initialPlayerInput;
    public Movement(PlayerTurn playerTurn, SeedList seedList) {

    }

    public void moveSeed(PlayerTurn playerTurn, SeedList seedList) {
        // test
        this.playerInput = Integer.parseInt(playerTurn.getInput());
        this.initialPlayerInput = playerInput;
        this.currentTurn = playerTurn.getCurrentTurn();
        Runnable nextTurnFunction = playerTurn::nextTurn;

        if (currentTurn ==  0) {
            whoTurn = 0;
            moveP1seeds(playerInput, seedList, nextTurnFunction);
        } else {
            whoTurn = 1;
            moveP2seeds(playerInput, seedList, nextTurnFunction);
        }
    }

    public void moveP1seeds(int playerInput, SeedList seedList, Runnable nextTurnFunction) {
        seedCount = getSeedCount(seedList);

        for (int i = 0; i < seedCount; i++) {
            if (seedList.getMapP1Seeds(incrementHouse) != null) {
                seedList.setMapP1Seed(incrementHouse, seedList.getMapP1Seeds(incrementHouse) + 1);
                incrementHouse++;
            } else {
                overFlow++;
            }
        }

        if (overFlow > 0) {
            moveP2seeds( 1, seedList, nextTurnFunction);
            wipeInput = true;
        }

        //System.out.print(incrementHouse - 1);
        if (whoTurn == 0) {
            if (seedList.getMapP1Seeds(incrementHouse - 1) == 1 && incrementHouse  != 8) {
                int oppositeHouse = transformValue(incrementHouse - 1);
                seedList.setMapP1Seed(incrementHouse - 1, seedList.getMapP1Seeds(incrementHouse - 1) +
                        seedList.getMapP2Seeds(oppositeHouse));
                seedList.setMapP2Seed(oppositeHouse, 0);
                nextTurnFunction.run();

            } else if (incrementHouse - 1 == 7) {
                //Player score
            } else {

                nextTurnFunction.run();
            }
        }


        if (whoTurn == 0) {
            seedList.setMapP1Seed(initialPlayerInput, 0);
        }
    }

    public void moveP2seeds(int playerInput, SeedList seedList, Runnable nextTurnFunction) {
        boolean zeroSeedHouse = false;
        boolean playerScoreCaptured = false;
        int zeroSeedCapture = 0;
        seedCount = getSeedCount(seedList);

        for (int i = 0; i < seedCount; i++) {
            if (seedList.getMapP2Seeds(incrementHouse) != null) {
                if (seedList.getMapP2Seeds(incrementHouse) == 0) {
                    zeroSeedHouse = true;
                    zeroSeedCapture = incrementHouse;
                }
                if (incrementHouse == 7) {
                    playerScoreCaptured = true;
                }
                seedList.setMapP2Seed(incrementHouse, seedList.getMapP2Seeds(incrementHouse) + 1);
                incrementHouse++;
            } else {
                overFlow++;
            }
        }

        if (overFlow > 0) {
            zeroSeedHouse = false;
            moveP1seeds( 1, seedList, nextTurnFunction);
            wipeInput = true;
        }

        if (whoTurn == 1) {
            if (seedList.getMapP2Seeds(incrementHouse - 1) == 0 && incrementHouse  != 8) {
                int oppositeHouse = transformValue(incrementHouse - 1);
                seedList.setMapP2Seed(incrementHouse - 1, seedList.getMapP2Seeds(incrementHouse - 1) +
                        seedList.getMapP1Seeds(oppositeHouse));
                seedList.setMapP1Seed(oppositeHouse, 0);
                nextTurnFunction.run();
            } else if (incrementHouse - 1 == 7) {
                //Player score
            } else {
                nextTurnFunction.run();
            }
        }

        if (whoTurn == 1) {
            seedList.setMapP2Seed(initialPlayerInput, 0);
        }
    }

    public int getSeedCount(SeedList seedList) {
        if (currentTurn == 0 && overFlow == 0) {
            seedCount = seedList.getMapP1Seeds(playerInput);
            incrementHouse = playerInput + 1;
        } else if (currentTurn == 1 && overFlow == 0) {
            seedCount = seedList.getMapP2Seeds(playerInput);
            incrementHouse = playerInput + 1;
        } else if (overFlow > 0) {
            seedCount = overFlow;
            incrementHouse = 1;
            overFlow = 0;
        }

        return seedCount;
    }

    public int transformValue(int value) {
        if (value == 6) {
            return 1;
        } else if (value == 5) {
            return 2;
        } else if (value == 4) {
            return 3;
        } else if (value == 3) {
            return 4;
        } else if (value == 2) {
            return 5;
        } else if (value == 1) {
            return 6;
        } else {
            // Handle other values or invalid input
            return -1; // Example: Return -1 for invalid input
        }
    }
}