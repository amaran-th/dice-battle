package dice_battle.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Ranking {
    private final Stack<Name> names;
    private final int participantCount;

    public Ranking(final int participantCount) {
        names = new Stack<>();
        this.participantCount = participantCount;
    }

    public void rank(final Name name) {
        names.push(name);
    }

    public boolean isAllParticipantsRanking() {
        return names.size() == participantCount;
    }

    public boolean isAllParticipantsWithoutFirstRanking() {
        return names.size() == participantCount - 1;
    }

    public List<Name> getRanking() {
        final List<Name> rankingResult = new ArrayList<>();
        while (!names.isEmpty()) {
            rankingResult.add(names.pop());
        }
        return rankingResult;
    }
}
