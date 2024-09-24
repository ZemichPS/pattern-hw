package service.impl.validation.order;

import model.OrderDetails;
import service.impl.validation.Validator;

public class OrderQuantityValidator extends Validator<OrderDetails> {
    @Override
    public void validate(OrderDetails validated) {
        if (validated.getProductCount() < 1) throw new RuntimeException("Product count must be greater than 0");
        this.checkNext(validated);
    }
}
