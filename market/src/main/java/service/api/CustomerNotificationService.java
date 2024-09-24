package service.api;

import model.OrderDetails;

public interface CustomerNotificationService {
    void notify(OrderDetails orderDetails);
}
