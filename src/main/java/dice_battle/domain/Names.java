package dice_battle.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Names {
    private final List<Name> names;

    public Names(final String names) {
        this.names = Arrays.stream(names.split(","))
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public int calculateSize() {
        return names.size();
    }

    public boolean existDuplicate() {
        return new HashSet<>(names).size() != names.size();
    }

    public List<Name> getNames() {
        return names;
    }
}
