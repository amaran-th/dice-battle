package dice_battle;

import static dice_battle.utils.Repeater.repeatIfError;

import dice_battle.domain.Name;
import dice_battle.domain.Participants;
import dice_battle.domain.Referee;
import dice_battle.domain.Turn;
import dice_battle.utils.Dice;
import dice_battle.view.InputView;
import dice_battle.view.OutputView;
import java.util.List;
import java.util.Map;

public class DiceBattleGame {
    private Turn turn;
    private Referee referee;

    public DiceBattleGame() {
        this.turn = new Turn();
    }

    public void run() {
        OutputView.printGameRule();
        final Participants participants = repeatIfError(this::inputParticipants, OutputView::printError);
        referee = new Referee(participants);
        runGameTurns();
        OutputView.printGameResult(referee.getRanking(), turn);
    }

    private Participants inputParticipants() {
        return new Participants(InputView.inputParticipantNames());
    }

    private void runGameTurns() {
        while (!referee.isAllParticipantsRanking()) {
            final List<Name> names = findAttackOrder();
            runAttackTime(names);
            if (!referee.isAllParticipantsRanking()) {
                turn = turn.countTurn();
            }
        }
    }

    private List<Name> findAttackOrder() {
        final Map<Name, Integer> participantSpeed = referee.getParticipantSpeed();
        OutputView.printTurnDescription(turn, referee.getParticipants(), referee.findSurvivorNames(),
                participantSpeed);
        final List<Name> names = referee.getSortedParticipantNames(participantSpeed);
        OutputView.printAttackOrder(names);
        return names;
    }

    private void runAttackTime(List<Name> names) {
        for (final Name attacker : names) {
            if (referee.isDead(attacker)) {
                continue;
            }
            OutputView.printOrder(attacker);
            final Name target = repeatIfError(() -> inputTargetName(attacker), OutputView::printError);

            final int totalDamage = calculateTotalDamage();

            OutputView.printDamage(target, totalDamage);
            referee.attackParticipant(attacker, target, totalDamage);
            if (referee.isDead(target)) {
                OutputView.printLoseMessage(target);
            }

            OutputView.printHealthPointStatus(referee.getParticipants());
        }
    }

    private Name inputTargetName(final Name attacker) {
        final Name target = new Name(InputView.inputAttackTarget());
        referee.validateTarget(attacker, target);
        return target;
    }

    private int calculateTotalDamage() {
        InputView.inputEnter1();
        final int firstRoll = Dice.roll();
        OutputView.printDiceValue(firstRoll);
        InputView.inputEnter2();
        final int secondRoll = Dice.roll();
        OutputView.printDiceValue(secondRoll);

        return firstRoll * secondRoll;
    }
}
