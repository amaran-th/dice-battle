package dice_battle.view;

import dice_battle.domain.Name;
import dice_battle.domain.Participant;
import dice_battle.domain.Participants;
import dice_battle.domain.Ranking;
import dice_battle.domain.Turn;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printGameRule() {
        System.out.println("""
                Dice Battle에 오신 것을 환영합니다.
                <규칙 설명>
                - 한 턴은 공격 '순서 결정', '공격' 두 동작으로 구성됩니다.
                - 공격 순서 결정 : 각자 주사위를 한 번 굴려 주사위 값이 높게 나온 순서대로 공격을 합니다.
                - 공격 : 공격은 다시 '공격 대상 선택', '공격 수행'으로 구성됩니다.
                    - 공격 대상 선택 : 참여자의 번호를 입력해 대상을 타겟팅할 수 있습니다.
                    - 공격 수행 : 주사위를 두 번 굴려 나온 값을 곱한 만큼 상대방에게 데미지를 줍니다.
                - 체력 : 각 참여자들은 게임 시작 시 100의 체력을 가지고 있습니다. 체력이 0이 되면 더 이상 게임에 참여할 수 없습니다.
                - 마지막 생존자가 나올 때까지 턴을 반복하며, 마지막으로 살아남은 참여자가 우승자가 됩니다.
                """);
    }

    public static void printTurnDescription(final Participants participants,
                                            final Map<Participant, Integer> speedPerParticipant) {
        System.out.println("=============== 1번째 턴 ===============");
        printHealthPointStatus(participants);
        System.out.println();
        printParticipantSpeed(participants, speedPerParticipant);
        printAttackOrder(speedPerParticipant);
    }

    public static void printHealthPointStatus(final Participants participants) {
        final String status = participants.getParticipants()
                .stream()
                .map(participant -> participant.getName().getValue() + ": " + participant.getHealthPoint()
                        .getValue())
                .collect(Collectors.joining(", "));

        System.out.println("> 체력 상태 - " + status);
    }

    public static void printParticipantSpeed(final Participants participants,
                                             final Map<Participant, Integer> speedPerParticipant) {
        final String participantSpeed = participants.getParticipants()
                .stream()
                .map(participant -> participant.getName().getValue() + ": " + speedPerParticipant.get(participant))
                .collect(Collectors.joining(", "));

        System.out.println("> 공격 순서 - " + participantSpeed);
    }

    public static void printAttackOrder(final Map<Participant, Integer> speedPerParticipant) {
        final String attackOrder = speedPerParticipant.keySet()
                .stream()
                .map(participant -> participant.getName().getValue())
                .collect(Collectors.joining(" -> "));

        System.out.println("> 이번 턴의 공격 순서 : " + attackOrder);
    }

    public static void printOrder(final String name) {
        System.out.println(String.format("> %s의 차례!", name));
    }

    public static void printDiceValue(final int value) {
        System.out.println(value);
    }

    public static void printDamage(final Participant participant, final int damage) {
        System.out.println("> '" + participant.getName().getValue() + "'에게 " + damage + "의 데미지!");
    }

    public static void printLoseMessage(final Participant participant) {
        System.out.println("> '" + participant.getName().getValue() + "'(이)가 패배하였습니다.");
    }

    public static void printGameResult(final Ranking ranking, final Turn turn) {
        System.out.println("=============== 배틀 결과 ===============");
        final List<Name> names = ranking.getRanking();
        for (int i = 1; i <= names.size(); i++) {
            System.out.println(i + "등 : " + names.get(i - 1));
        }
        System.out.println("최종 턴 수 : " + turn.getValue() + "턴");
    }

    public static void printError(final Exception exception) {
        System.out.println("[Error] " + exception.getMessage());
    }
}
