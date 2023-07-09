package kalah;

public class Movement {
    private int currentTurn;
    private int playerInput;
    private int seedCount = 0;
    private int overFlow = 0;
    private int incrementHouse = 0;
    private int whoTurn;
    private int initialPlayerInput;
    private boolean lap = false;
    private boolean checkMe = false;
    int lapCount = 0;
    public Movement(PlayerTurn playerTurn, SeedList seedList) {

    }

    public void moveSeed(PlayerTurn playerTurn, SeedList seedList) {
        // test
        this.playerInput = Integer.parseInt(playerTurn.getInput());
        this.initialPlayerInput = playerInput;
        this.currentTurn = playerTurn.getCurrentTurn();
        Runnable nextTurnFunction = playerTurn::nextTurn;
        this.lap = false;
        this.lapCount = 0;

        if (currentTurn ==  0) {
            checkMe = false;
            lap = false;
            whoTurn = 0;
            moveP1seeds(playerInput, seedList, nextTurnFunction);
        } else {
            checkMe = false;
            lap = false;
            whoTurn = 1;
            moveP2seeds(playerInput, seedList, nextTurnFunction);
        }
    }

    public void moveP1seeds(int playerInput, SeedList seedList, Runnable nextTurnFunction) {

        seedCount = getSeedCount(seedList);

        for (int i = 0; i < seedCount; i++) {
            if (whoTurn == 1 && incrementHouse == 7) {
                overFlow++;
            }
            else if (seedList.getMapP1Seeds(incrementHouse) != null) {
                seedList.setMapP1Seed(incrementHouse, seedList.getMapP1Seeds(incrementHouse) + 1);
                incrementHouse++;
            } else {
                overFlow++;
            }
        }

        if (overFlow > 0) {
            //lap = true;
            //lapCount++;
            if (whoTurn == 0) {
                checkMe = true;
            } else if (whoTurn == 1) {
                checkMe = false;
            }
            //checkMe = true;
            moveP2seeds( 1, seedList, nextTurnFunction);
        }

        //System.out.print(incrementHouse - 1);
        int oppositeHouse = transformValue(incrementHouse - 1);
        if (whoTurn == 0) {
            if (seedList.getMapP1Seeds(incrementHouse - 1) == 1 && incrementHouse  != 8 &&
            seedList.getMapP2Seeds(oppositeHouse) > 0 && !checkMe) {
                seedList.setMapP1Seed(7, seedList.getMapP1Seeds(7) +
                        seedList.getMapP1Seeds(incrementHouse - 1) +
                        seedList.getMapP2Seeds(oppositeHouse));
                seedList.setMapP1Seed(incrementHouse - 1, 0);
                seedList.setMapP2Seed(oppositeHouse, 0);
                if (!lap) {
                    nextTurnFunction.run();
                }
                lap = true;

            } else if (incrementHouse - 1 == 7) {
                //Player score
            } else {
                if (!lap) {
                    nextTurnFunction.run();
                }
                lap = true;
            }
        }



    }

    public void moveP2seeds(int playerInput, SeedList seedList, Runnable nextTurnFunction) {
        seedCount = getSeedCount(seedList);

        for (int i = 0; i < seedCount; i++) {
            if (whoTurn == 0 && incrementHouse == 7) {
                overFlow++;
            }
            else if (seedList.getMapP2Seeds(incrementHouse) != null) {
                seedList.setMapP2Seed(incrementHouse, seedList.getMapP2Seeds(incrementHouse) + 1);
                incrementHouse++;
            } else {
                overFlow++;
            }
        }

        if (overFlow > 0) {
            //lap = true;
            //lapCount++;
            if (whoTurn == 0) {
                checkMe = false;
            } else if (whoTurn == 1) {
                checkMe = true;
            }
            //checkMe = true;
            moveP1seeds( 1, seedList, nextTurnFunction);
        }

        int oppositeHouse = transformValue(incrementHouse - 1);

        //System.out.println(incrementHouse);
        if (whoTurn == 1) {

            if (seedList.getMapP2Seeds(incrementHouse - 1) == 1 && incrementHouse  != 8 &&
            seedList.getMapP1Seeds(oppositeHouse) > 0 && !checkMe) {
                System.out.println(incrementHouse - 1);
                seedList.setMapP2Seed(7, seedList.getMapP2Seeds(7) +
                        seedList.getMapP2Seeds(incrementHouse - 1) +
                        seedList.getMapP1Seeds(oppositeHouse));
                seedList.setMapP1Seed(oppositeHouse, 0);
                seedList.setMapP2Seed(incrementHouse - 1, 0);

                if (!lap) {
                    nextTurnFunction.run();
                }
                lap = true;
            } else if (incrementHouse - 1 == 7) {
                //Player score
            } else {
                if (!lap) {
                    nextTurnFunction.run();
                }
                lap = true;
            }
        }
    }

    public int getSeedCount(SeedList seedList) {
        if (overFlow == 0) {
            incrementHouse = playerInput + 1;
            if (currentTurn == 0) {
                seedCount = seedList.getMapP1Seeds(playerInput);
                seedList.setMapP1Seed(initialPlayerInput, 0);
            } else {
                seedCount = seedList.getMapP2Seeds(playerInput);
                seedList.setMapP2Seed(initialPlayerInput, 0);
            }
        } else {
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