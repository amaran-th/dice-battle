package dice_battle.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    @Test
    @DisplayName("참여자 이름에 공백이 포함되어 있으면 예외를 반환한다.")
    void constructor_fail_blank_name() {
        //given, when
        final ThrowingCallable actual = () -> new Participants("아마란스,  ,,보름");

        //then
        Assertions.assertThatThrownBy(actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름 값은 공백이 될 수 없습니다.");

    }

    @Test
    @DisplayName("총 참여자 수가 1명 이하인 경우 예외를 반환한다.")
    void constructor_fail_less_than_2() {
        //given, when
        final ThrowingCallable actual = () -> new Participants("아마란스");

        //then
        Assertions.assertThatThrownBy(actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여자 수는 최소 2명 이상이어야 합니다.");
    }

    @Test
    @DisplayName("이름이 같은 참여자가 두 명 이상 존재한다면  예외를 반환한다.")
    void constructor_fail_duplicated_name() {
        //given, when
        final ThrowingCallable actual = () -> new Participants("아마란스,아마란스,짱구,보름");

        //then
        Assertions.assertThatThrownBy(actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 이름의 참여자는 2명 이상 존재할 수 없습니다.");
    }
}
