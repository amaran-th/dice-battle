package dice_battle.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("참여자의 생존 여부를 조회할 수 있다.")
    void isAlive_success() {
        //given
        final Participant participant = new Participant(new Name("아마란스"));

        //when
        final boolean actual = participant.isAlive();

        //then
        Assertions.assertThat(actual).isTrue();

    }

    @Test
    @DisplayName("데미지를 입을 수 있다.")
    void sufferDamage_success() {
        //given
        final Participant participant = new Participant(new Name("아마란스"));
        final int expected = 64;

        //when
        participant.sufferDamage(36);
        final int actual = participant.getHealthPoint().getValue();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("체력이 0이 되면 참여자는 전투불능이 된다.")
    void sufferDamage_success_dead() {
        //given
        final Participant participant = new Participant(new Name("아마란스"));

        //when
        participant.sufferDamage(120);
        final boolean actual = participant.isAlive();

        //then
        Assertions.assertThat(actual).isFalse();

    }
}
