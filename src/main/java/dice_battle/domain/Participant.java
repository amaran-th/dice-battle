package dice_battle.domain;

import java.util.Objects;

public class Participant {
    private static final int DEFAULT_HEALTH_POINT_VALUE = 100;

    private final Name name;
    private HealthPoint healthPoint;

    public Participant(final Name name) {
        this.name = name;
        this.healthPoint = new HealthPoint(DEFAULT_HEALTH_POINT_VALUE);
    }

    public void sufferDamage(final int damage) {
        this.healthPoint = healthPoint.sufferDamage(damage);
    }

    public boolean isAlive() {
        return this.healthPoint.isPositive();
    }

    public Name getName() {
        return name;
    }

    public HealthPoint getHealthPoint() {
        return healthPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
