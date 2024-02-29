package dice_battle.domain;

public class HealthPoint {
    private static final int DEFAULT_HEALTH_POINT_VALUE = 100;

    private int value;

    public HealthPoint() {
        this.value = DEFAULT_HEALTH_POINT_VALUE;
    }

    private HealthPoint(final int value) {
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

}
