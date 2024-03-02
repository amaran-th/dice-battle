package dice_battle.domain;

import java.util.Objects;

public class HealthPoint {

    private int value;

    public HealthPoint(final int value) {
        validateNegativeValue(value);
        this.value = value;
    }

    private void validateNegativeValue(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("체력은 0 이상이어야 합니다.");
        }
    }

    public HealthPoint sufferDamage(final int damage) {
        if (value <= damage) {
            return new HealthPoint(0);
        }
        return new HealthPoint(value - damage);
    }

    public boolean isPositive() {
        return value > 0;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HealthPoint that = (HealthPoint) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
