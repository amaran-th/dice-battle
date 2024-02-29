package dice_battle.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Test
    @DisplayName("턴을 처음 생성하면 1의 값을 갖는다.")
    void constructor_success() {
        //given, when;
        final Turn actual = new Turn();

        //then
        Assertions.assertThat(actual).extracting("value").isEqualTo(1);
    }

    @Test
    @DisplayName("턴 수가 1 증가한다.")
    void countTurn_success() {
        //given
        final Turn before = new Turn();

        //when
        final Turn after = before.countTurn();

        //then
        Assertions.assertThat(after).extracting("value").isEqualTo(2);
    }

}
