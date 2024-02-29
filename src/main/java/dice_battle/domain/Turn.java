package dice_battle.domain;

public class Turn {
    private static final int DEFAULT_VALUE = 1;
    private int value;

    public Turn() {
        this.value = DEFAULT_VALUE;
    }

    private Turn(final int value) {
        validateNotPositiveValue(value);
        this.value = value;
    }

    private void validateNotPositiveValue(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("턴 수는 1 이상이어야 합니다.");
        }
    }

    public Turn countTurn() {
        return new Turn(value + 1);
    }

}
