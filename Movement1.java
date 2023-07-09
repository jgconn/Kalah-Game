package kalah;

public class Movement1 {
    private int currentPlayer;
    private int playerInput;
    private int seedCount = 0;
    private int overFlow = 0;
    private int currentHouse = 0;
    private int whoTurn;
    private boolean zeroSeedHouse = false;
    int initialPlayerInput;
    public Movement1(PlayerTurn playerTurn, SeedList seedList) {

    }

    public void moveSeed(PlayerTurn playerTurn, SeedList seedList) {
        // test
        this.playerInput = Integer.parseInt(playerTurn.getInput());
        this.initialPlayerInput = playerInput;
        this.currentPlayer = playerTurn.getCurrentTurn();
        Runnable nextTurnFunction = playerTurn::nextTurn;

        if (currentPlayer ==  0) {
            whoTurn = 0;
            moveP1seeds(playerInput, seedList, nextTurnFunction);
        } else {
            whoTurn = 1;
            moveP2seeds(playerInput, seedList, nextTurnFunction);
        }
    }

    public void moveP1seeds(int playerInput, SeedList seedList, Runnable nextTurnFunction) {
        int seedCount = getSeedCount(seedList);

        for (int i = 0; i < seedCount; i++) {
            if (seedList.getMapP1Seeds(currentHouse) != null) {
                seedList.setMapP1Seed(currentHouse, seedList.getMapP1Seeds(currentHouse) + 1);
                currentHouse++;
            } else {
                overFlow++;
            }
        }

        if (overFlow != 0) {
            switchToP2(1, seedList, nextTurnFunction);
        }

        if (seedList.getMapP1Seeds(currentHouse - 1) == 1) {
            int oppositeHouse = transformValue(currentHouse - 1);
            seedList.setMapP1Seed(currentHouse - 1, seedList.getMapP1Seeds(currentHouse - 1) +
                    seedList.getMapP2Seeds(oppositeHouse));
            seedList.setMapP2Seed(oppositeHouse, 0);
        } else if (currentHouse == 7) {

        } else {
            nextTurnFunction.run();
        }

        resetInitialInput(seedList);
    }

    public void switchToP2(int playerInput, SeedList seedList, Runnable nextTurnFunction) {
        int seedCount = getSeedCount(seedList);

        for (int i = 0; i < seedCount; i++) {
            if (currentHouse == 7 && whoTurn == 0) {
                overFlow++;
            } else if (seedList.getMapP2Seeds(currentHouse) != null) {
                seedList.setMapP2Seed(currentHouse, seedList.getMapP2Seeds(currentHouse) + 1);
                currentHouse++;
            } else {
                overFlow++;
            }
        }

        if (overFlow != 0) {
            switchToP1(1, seedList, nextTurnFunction);
        }
    }

    public void switchToP1(int playerInput, SeedList seedList, Runnable nextTurnFunction) {
        int seedCount = getSeedCount(seedList);

        for (int i = 0; i < seedCount; i++) {
            if (currentHouse == 7 && whoTurn == 0) {
                overFlow++;
            } else if (seedList.getMapP1Seeds(currentHouse) != null) {
                seedList.setMapP1Seed(currentHouse, seedList.getMapP1Seeds(currentHouse) + 1);
                currentHouse++;
            } else {
                overFlow++;
            }
        }

        if (overFlow != 0) {
            switchToP2(1, seedList, nextTurnFunction);
        }


    }

    public void moveP2seeds(int playerInput, SeedList seedList, Runnable nextTurnFunction) {
        int seedCount = getSeedCount(seedList);

        for (int i = 0; i < seedCount; i++) {
            if (seedList.getMapP2Seeds(currentHouse) != null) {
                seedList.setMapP2Seed(currentHouse, seedList.getMapP2Seeds(currentHouse) + 1);
                currentHouse++;
            } else {
                overFlow++;
            }
        }

        if (overFlow != 0) {
            switchToP1(1, seedList, nextTurnFunction);
        }

        if (seedList.getMapP2Seeds(currentHouse - 1) == 1) {
            int oppositeHouse = transformValue(currentHouse - 1);
            seedList.setMapP2Seed(currentHouse - 1, seedList.getMapP2Seeds(currentHouse - 1) +
                    seedList.getMapP1Seeds(oppositeHouse));
            seedList.setMapP1Seed(oppositeHouse, 0);
        } else if (currentHouse == 7) {

        } else {
            nextTurnFunction.run();
        }
        resetInitialInput(seedList);
    }

    public int getSeedCount(SeedList seedList) {
        if (overFlow == 0) {
            currentHouse = playerInput + 1;
            if (currentPlayer == 0) {
                seedCount = seedList.getMapP1Seeds(playerInput);
            } else {
                seedCount = seedList.getMapP2Seeds(playerInput);
            }
        } else {
            seedCount = overFlow;
            currentHouse = 1;
            overFlow = 0;
        }
        return seedCount;
    }

    public void resetInitialInput(SeedList seedList) {
        if (whoTurn == 0) {
            seedList.setMapP1Seed(initialPlayerInput, 0);
        } else {
            seedList.setMapP2Seed(initialPlayerInput, 0);
        }
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
