package dice_battle.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiceTest {
    @Test
    @DisplayName("주사위를 굴리면 1부터 6 사이의 랜덤한 값이 나온다.")
    void roll_success() {
        //given, when
        final int MIN_DICE_VALUE = 1;
        final int MAX_DICE_VALUE = 6;
        final int actual = Dice.roll();

        //then
        Assertions.assertThat(actual).isGreaterThanOrEqualTo(MIN_DICE_VALUE);
        Assertions.assertThat(actual).isLessThanOrEqualTo(MAX_DICE_VALUE);
    }

}
