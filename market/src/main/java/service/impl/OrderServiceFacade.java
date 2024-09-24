package service.impl;

import model.OrderDetails;
import service.api.CustomerNotificationService;
import service.api.crud.OrderDetailsService;
import service.impl.validation.Validator;

import java.util.List;
import java.util.UUID;

public class OrderServiceFacade {

    private final OrderDetailsService orderDetailsService;
    private final EventManager eventManager;
    private final CustomerNotificationService customerNotificationService;
    private final Validator<OrderDetails> validator;

    public OrderServiceFacade(
            OrderDetailsService orderDetailsService,
            EventManager eventManager,
            CustomerNotificationService customerNotificationService,
            Validator<OrderDetails> validator) {
        this.orderDetailsService = orderDetailsService;
        this.eventManager = eventManager;
        this.customerNotificationService = customerNotificationService;
        this.validator = validator;
    }

    public OrderDetails createAndNotify(OrderDetails orderDetails) {
        validator.validate(orderDetails);
        orderDetailsService.save(orderDetails);
        customerNotificationService.notify(orderDetails);
        eventManager.publish("OrderCreated", orderDetails);
        return orderDetails;
    }

    public void cancelAndNotify(OrderDetails orderDetails) {
        orderDetailsService.delete(orderDetails);
        customerNotificationService.notify(orderDetails);
        eventManager.publish(orderDetails.getStatus().name(), orderDetails);
    }

    public void updateAndNotify(OrderDetails orderDetails) {
        orderDetailsService.delete(orderDetails);
        UUID orderUuid = orderDetails.getUuid();
        customerNotificationService.notify(orderDetails);
        eventManager.publish(orderDetails.getStatus().name(), orderDetails);
    }

    public List<OrderDetails> getAll() {
        return orderDetailsService.getAll();
    }

    public void delete(OrderDetails orderDetails) {
        orderDetailsService.delete(orderDetails);
    }

}
