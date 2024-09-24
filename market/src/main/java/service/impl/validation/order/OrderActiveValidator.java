package service.impl.validation.order;

import model.OrderDetails;
import model.Status;
import service.impl.validation.Validator;

public class OrderActiveValidator extends Validator<OrderDetails> {
    @Override
    public void validate(OrderDetails validated) {
        if (validated.getStatus().equals(Status.CANCELED)) throw new RuntimeException("OrderDetails status can't be canceled");
        checkNext(validated);
    }
}
