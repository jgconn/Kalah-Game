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
    private boolean myScore = true;
    int playerInput2;
    public Movement(PlayerTurn playerTurn, SeedList seedList) {

    }

    public void moveSeed(PlayerTurn playerTurn, SeedList seedList) {
        // test
        this.playerInput = Integer.parseInt(playerTurn.getInput());
        this.currentTurn = playerTurn.getCurrentTurn();
        Runnable nextTurnFunction = playerTurn::nextTurn;
        this.playerInput2 = playerInput;

        if (currentTurn ==  0) {
            whoTurn = 0;
            moveP1seeds(playerInput, seedList, nextTurnFunction);
        } else {
            whoTurn = 1;
            moveP2seeds(playerInput, seedList, nextTurnFunction);
        }
    }

    public void moveP1seeds(int playerInput, SeedList seedList, Runnable nextTurnFunction) {
        boolean zeroSeedHouse = false;
        boolean playerScoreCaptured = false;
        int zeroSeedCapture = 0;
        seedCount = getSeedCount(seedList);
        //System.out.println(seedCount);

        for (int i = 0; i < seedCount; i++) {
            //System.out.println(incrementHouse);
            if (incrementHouse == 7 && whoTurn != 0) {
                overFlow++;
                playerScoreCaptured = false;
            }
            else if (seedList.getMapP1Seeds(incrementHouse) != null) {
                if (seedList.getMapP1Seeds(incrementHouse) == 0) {
                    //System.out.println(incrementHouse);
                    zeroSeedHouse = true;
                    zeroSeedCapture = incrementHouse;
                } else {
                    zeroSeedHouse = false;
                }
                if (incrementHouse == 7) {
                    playerScoreCaptured = true;
                }

                if (whoTurn != 0 && seedList.getMapP1Seeds(incrementHouse) == 7) {
                    overFlow++;
                    playerScoreCaptured = false;
                } else {
                    seedList.setMapP1Seed(incrementHouse, seedList.getMapP1Seeds(incrementHouse) + 1);
                    //incrementHouse++;
                }

                //seedList.setMapP1Seed(incrementHouse, seedList.getMapP1Seeds(incrementHouse) + 1);
                incrementHouse++;


            } else {
                overFlow++;
                playerScoreCaptured = false;
            }
        }

        if (overFlow > 0) {
            zeroSeedHouse = false;
            myScore = false;
            moveP2seeds( 1, seedList, nextTurnFunction);
            wipeInput = true;
        }

        //System.out.println(overFlow);
        //System.out.println(zeroSeedCapture);
        //System.out.println(playerScoreCaptured);
        if (whoTurn == 0) {
            if (overFlow == 0 && zeroSeedHouse) {
                seedList.setMapP1Seed(zeroSeedCapture, seedList.getMapP1Seeds(zeroSeedCapture) + seedList.getMapP2Seeds(zeroSeedCapture));
                System.out.println("Player 1 turn 1");
            } else if (overFlow == 0 && playerScoreCaptured) {
                System.out.println("Player 1 turn 2");
            }
            else {
                //System.out.println("Player 2 turn");
                nextTurnFunction.run();
            }
        }


        if (whoTurn == 0) {
            seedList.setMapP1Seed(playerInput2, 0);
            myScore = true;
        }
    }

    public void moveP2seeds(int playerInput, SeedList seedList, Runnable nextTurnFunction) {
        boolean zeroSeedHouse = false;
        boolean playerScoreCaptured = false;
        int zeroSeedCapture = 0;
        seedCount = getSeedCount(seedList);

        for (int i = 0; i < seedCount; i++) {
            //System.out.println("First line after for: " + incrementHouse);
            //System.out.println(whoTurn);
            if (incrementHouse == 7 && whoTurn == 0) {
                overFlow++;
                playerScoreCaptured = false;
                zeroSeedHouse = false;
            }
            else if (seedList.getMapP2Seeds(incrementHouse) != null) {
                if (seedList.getMapP2Seeds(incrementHouse) == 0) {
                    zeroSeedHouse = true;
                    zeroSeedCapture = incrementHouse;
                } else {
                    zeroSeedHouse = false;
                }
                if (incrementHouse == 7) {
                    playerScoreCaptured = true;
                }

                seedList.setMapP2Seed(incrementHouse, seedList.getMapP2Seeds(incrementHouse) + 1);
                    //incrementHouse++;


                //seedList.setMapP2Seed(incrementHouse, seedList.getMapP2Seeds(incrementHouse) + 1);
                //System.out.println("Above to increment++: " +incrementHouse);
                incrementHouse++;


            } else {


                overFlow++;
                playerScoreCaptured = false;
            }
        }

        //System.out.println(overFlow);

        if (overFlow > 0) {
            zeroSeedHouse = false;
            myScore = false;
            moveP1seeds( 1, seedList, nextTurnFunction);
            wipeInput = true;
        }

        if (whoTurn == 1) {
            if (overFlow == 0 && zeroSeedHouse) {
                seedList.setMapP2Seed(zeroSeedCapture, seedList.getMapP1Seeds(zeroSeedCapture) + seedList.getMapP2Seeds(zeroSeedCapture));
                //System.out.println("Player 2 turn");
            } else if (overFlow == 0 && playerScoreCaptured) {
                //System.out.println("Player 1 turn");
            }
            else {
                //System.out.println("Player 1 turn");
                nextTurnFunction.run();
            }
        }

        if (whoTurn == 1) {
            seedList.setMapP2Seed(playerInput2, 0);
            myScore = true;
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
}
