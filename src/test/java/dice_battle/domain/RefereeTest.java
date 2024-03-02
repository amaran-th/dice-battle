package dice_battle.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {
//    @Test
//    @DisplayName("특정 참여자가 공격후 사망")
//    void attackParticipant_success() {
//        //given
//
//        //when
//
//        //then
//
//    }

    @Test
    @DisplayName("자기 자신을 공격 대상으로 지정하면 예외를 반환한다.")
    void validateTarget_fail_attack_myself() {
        //given
        final Referee referee = new Referee(new Participants("아마란스,짱구,보름"));
        final Name name = new Name("아마란스");
        //when
        final ThrowingCallable actual = () -> referee.validateTarget(name, name);

        //then
        Assertions.assertThatThrownBy(actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자기 자신을 공격할 수 없습니다.");


    }
}
