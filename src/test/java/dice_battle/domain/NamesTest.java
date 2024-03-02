package dice_battle.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {

    @Test
    @DisplayName("이름 값을 입력하면 Name 객체들이 정상적으로 생성된다.")
    void construcor_success() {
        //given, when
        final Names names = new Names("아마란스,보름,짱구");

        //then
        Assertions.assertThat(names.calculateSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("공백을 포함된 참여자 이름 목록을 입력하면 예외를 반환한다.")
    void construcor_fail_contain_blank_name() {
        //given, when
        final ThrowingCallable actual = () -> new Names("아마란스,,보름, ,짱구");

        //then
        Assertions.assertThatThrownBy(actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름 값은 공백이 될 수 없습니다.");
    }

    @Test
    @DisplayName("여러 이름 객체 중 중복된 이름이 존재한다면 true를 반환한다.")
    void existDuplicate_true() {
        //given
        final Names names = new Names("아마란스,아마란스,보름");

        //when
        final boolean result = names.existDuplicate();

        //then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("여러 이름 객체 중 중복된 이름이 존재하지 않는다면 false를 반환한다.")
    void existDuplicate_false() {
        //given
        final Names names = new Names("아마란스,보름");

        //when
        final boolean result = names.existDuplicate();

        //then
        Assertions.assertThat(result).isFalse();
    }
}
