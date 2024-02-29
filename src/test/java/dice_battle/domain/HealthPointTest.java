package dice_battle.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HealthPointTest {

    @Test
    @DisplayName("데미지를 입히면 체력 값이 데미지 만큼 감소한다.")
    void sufferDamage_success() {
        //given
        final HealthPoint before = new HealthPoint();

        //when
        final HealthPoint after = before.sufferDamage(15);

        //then
        Assertions.assertThat(after)
                .extracting("value")
                .isEqualTo(85);
    }

    @Test
    @DisplayName("데미지량이 체력보다 많으면 체력은 0이 된다.")
    void sufferDamage_success_not_negative() {
        //given
        final HealthPoint before = new HealthPoint();

        //when
        final HealthPoint after = before.sufferDamage(120);

        //then
        Assertions.assertThat(after)
                .extracting("value")
                .isEqualTo(0);
    }

}
