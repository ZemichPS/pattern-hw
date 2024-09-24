package service.impl.validation.order;

import model.OrderDetails;
import service.impl.validation.Validator;

import java.util.Objects;

public class OrderUserUuidNotNullValidator extends Validator<OrderDetails> {
    @Override
    public void validate(OrderDetails validated) {
        if (Objects.isNull(validated.getCustomer())) throw new RuntimeException("User uuid is null");
        checkNext(validated);
    }
}
