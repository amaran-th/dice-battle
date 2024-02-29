package dice_battle.domain;

import java.util.Stack;

public class Ranking {
    private final Stack<Name> names;

    public Ranking() {
        names = new Stack<>();
    }

    public Stack<Name> getNames() {
        return names;
    }
}
