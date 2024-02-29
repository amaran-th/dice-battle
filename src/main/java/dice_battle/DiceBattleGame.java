package dice_battle;

import static dice_battle.utils.Repeater.repeatIfError;

import dice_battle.domain.Participants;
import dice_battle.domain.Turn;
import dice_battle.view.InputView;
import dice_battle.view.OutputView;

public class DiceBattleGame {
    private final Turn turn;

    public DiceBattleGame() {
        this.turn = new Turn();
    }

    public void run() {
        OutputView.printGameRule();
        final Participants participants = new Participants(
                repeatIfError(InputView::inputParticipantNames, OutputView::printError));
    }
}
