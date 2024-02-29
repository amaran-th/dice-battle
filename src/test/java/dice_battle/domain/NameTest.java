package dice_battle.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @Test
    @DisplayName("이름 값을 입력하면 Name 객체가 정상적으로 생성된다.")
    void construcor_success() {
        //given, when
        final Name name = new Name("아마란스");

        //then
        Assertions.assertThat(name).isNotNull();

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @DisplayName("공백을 입력하면 예외를 반환한다.")
    void construcor_fail_blank(final String wrongName) {
        //given, when
        final ThrowingCallable actual = () -> new Name(wrongName);

        //then
        Assertions.assertThatThrownBy(actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름 값은 공백이 될 수 없습니다.");
    }


}
