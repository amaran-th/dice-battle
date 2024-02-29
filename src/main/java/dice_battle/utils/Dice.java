package dice_battle.utils;

import java.util.Random;

public class Dice {

    private static final int MAX_EYE_VALUE = 6;

    public static int roll() {
        return new Random().nextInt(1, MAX_EYE_VALUE + 1);
    }
}
