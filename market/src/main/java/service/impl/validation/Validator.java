package service.impl.validation;

import java.util.Objects;

public abstract class Validator<T> {

    private Validator<T> nextValidator;

    public Validator<T> linkWith(Validator<T> nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    public abstract void validate(T validated);

    public void checkNext(T validated) {
        if (Objects.nonNull(nextValidator)) nextValidator.validate(validated);
    }
}
