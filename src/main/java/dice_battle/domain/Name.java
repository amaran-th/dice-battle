package dice_battle.domain;

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
}
