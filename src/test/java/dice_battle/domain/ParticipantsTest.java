package dice_battle.domain;

import java.util.Map;
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

    @Test
    @DisplayName("참여자 별 속도 값을 포함한 Map 객체를 반환한다.")
    void decideParticipantsSpeed_success() {
        //given
        final int MIN_DICE_VALUE = 1;
        final int MAX_DICE_VALUE = 6;
        final Participants participants = new Participants("아마란스,짱구,보름");

        //when
        final Map<Name, Integer> actual = participants.decideParticipantsSpeed();

        //then
        actual.entrySet().stream()
                .forEach(entry -> {
                    Assertions.assertThat(entry.getValue()).isGreaterThanOrEqualTo(MIN_DICE_VALUE);
                    Assertions.assertThat(entry.getValue()).isLessThanOrEqualTo(MAX_DICE_VALUE);
                });
        Assertions.assertThat(actual.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("지목받은 참여자의 체력을 데미지량만큼 감소시킨다.")
    void attackParticipant_success() {
        //given
        final Participants participants = new Participants("아마란스,짱구,보름");
        final Name name = new Name("아마란스");
        //when
        participants.attackParticipant(name, 20);

        //then
        Assertions.assertThat(participants.getParticipants().get(0).getHealthPoint()).isEqualTo(new HealthPoint(80));
    }

    @Test
    @DisplayName("존재하지 않은 참여자의 이름을 입력하면 예외를 반환한다.")
    void attackParticipant_fail_not_exist_participant() {
        //given
        final Participants participants = new Participants("아마란스,짱구,보름");
        final Name name = new Name("꿻뚫솈");
        //when
        final ThrowingCallable actual = () -> participants.attackParticipant(name, 20);

        //then
        Assertions.assertThatThrownBy(actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자입니다.");
    }


    @Test
    @DisplayName("존재하지 않은 참여자의 이름을 입력하면 예외를 반환한다.")
    void validateParticipantName_fail_not_exist_participant() {
        //given
        final Participants participants = new Participants("아마란스,짱구,보름");
        final Name name = new Name("꿻뚫솈");
        //when
        final ThrowingCallable actual = () -> participants.validateParticipantName(name);

        //then
        Assertions.assertThatThrownBy(actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자입니다.");
    }

    @Test
    @DisplayName("체력이 0인 참여자의 이름을 입력하면 예외를 반환한다.")
    void validateParticipantName_fail_aleady_dead() {
        //given
        final Participants participants = new Participants("아마란스,짱구,보름");
        final Name name = new Name("아마란스");
        //when
        participants.attackParticipant(name, 120);
        final ThrowingCallable actual = () -> participants.validateParticipantName(name);

        //then
        Assertions.assertThatThrownBy(actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 전투불능이 된 참여자를 지목할 수 없습니다.");
    }

    @Test
    @DisplayName("지목받은 참여자가 전투불능 상태라면 true를 반환한다.")
    void isDead_true() {
        //given
        final Participants participants = new Participants("아마란스,짱구,보름");
        final Name name = new Name("아마란스");
        //when
        participants.attackParticipant(name, 120);
        final boolean actual = participants.isDead(name);

        //then
        Assertions.assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("지목받은 참여자가 생존한 상태라면 false를 반환한다.")
    void isDead_false() {
        //given
        final Participants participants = new Participants("아마란스,짱구,보름");
        final Name name = new Name("아마란스");
        //when
        final boolean actual = participants.isDead(name);

        //then
        Assertions.assertThat(actual).isFalse();
    }
}
