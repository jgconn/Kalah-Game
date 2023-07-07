# Kalah-Game in progress

while (i < seedCount) {
try {
int currentHouse = nextHouse + 1;
seedList.setMapP2Seed(currentHouse, seedList.getMapP2Seeds(currentHouse) + 1);
nextHouse++;
i++; // Increment the index when successful iteration occurs
} catch (NullPointerException e) {
rollOver = true;
rollOverSeed = i; // Capture the index of the last successful iteration
break; // Exit the loop when encountering a NullPointerException
}
}


for (int i = 0; i < seedCount; i++) {
int currentHouse = nextHouse + 1;
System.out.println(currentHouse);
if (seedList.getMapP1Seeds(currentHouse) != null) {

                seedList.setMapP1Seed(currentHouse, seedList.getMapP1Seeds(currentHouse) + 1);
                nextHouse++;
                checkBoardSide = 0;
            } else {
                rollOver = true;
                rollOverSeed = i;
                checkBoardSide = 1;
            }
        }