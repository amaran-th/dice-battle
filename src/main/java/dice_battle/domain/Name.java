package dice_battle.domain;

import java.util.Objects;

public class Name {

    private String value;

    public Name(final String value) {
        validateBlank(value);
        this.value = value;
    }

    private void validateBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름 값은 공백이 될 수 없습니다.");
        }
    }

    public String getValue() {
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
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
