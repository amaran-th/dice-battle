package dice_battle.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankingTest {
    @Test
    @DisplayName("모든 참여자들에게 순위가 매겨졌다면 true를 반환한다.")
    void isAllParticipantsRanking_true() {
        //given
        final Ranking ranking = new Ranking(2);
        final Name second = new Name("아마란스");
        final Name first = new Name("보름");
        //when
        ranking.rank(second);
        ranking.rank(first);
        final boolean result = ranking.isAllParticipantsRanking();

        //then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("순위가 매겨지지 않은 참여자들이 존재한다면 false를 반환한다.")
    void isAllParticipantsRanking_false() {
        //given
        final Ranking ranking = new Ranking(3);
        final Name second = new Name("아마란스");
        final Name first = new Name("보름");
        //when
        ranking.rank(second);
        ranking.rank(first);
        final boolean result = ranking.isAllParticipantsRanking();

        //then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    @DisplayName("패배한 참여자를 랭킹 후순위에 추가한다.")
    void rank_success() {
        //given
        final Ranking ranking = new Ranking(3);
        final Name second = new Name("아마란스");
        final Name first = new Name("보름");
        //when
        ranking.rank(second);
        ranking.rank(first);

        //then
        Assertions.assertThat(ranking.getRanking())
                .usingRecursiveComparison()
                .isEqualTo(List.of(first, second));
    }
}
