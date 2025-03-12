package commons;

import java.util.Random;

public class BaseTest {

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }
}
