package kalah;

import java.util.HashMap;
import java.util.Map;

public class SeedList {
    private HashMap<Integer, Integer> mapP1 = new HashMap<>();
    private HashMap<Integer, Integer> mapP2 = new HashMap<>();

    public SeedList() {
        mapP1.put(1, 4);
        mapP1.put(2, 4);
        mapP1.put(3, 4);
        mapP1.put(4, 4);
        mapP1.put(5, 4);
        mapP1.put(6, 4);

        mapP2.put(1, 4);
        mapP2.put(2, 4);
        mapP2.put(3, 4);
        mapP2.put(4, 4);
        mapP2.put(5, 4);
        mapP2.put(6, 4);
    }

    public void setMapP1Seed(int key, int value) {
        mapP1.put(key, value);
    }
    public HashMap<Integer, Integer> getMapP1() {
        return mapP1;
    }

    public void setMapP2Seed(int key, int value) {
        mapP2.put(key, value);
    }

    public HashMap<Integer, Integer> getMapP2() {
        return mapP2;
    }
}
