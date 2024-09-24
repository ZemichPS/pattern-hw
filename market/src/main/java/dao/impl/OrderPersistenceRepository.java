package dao.impl;


import model.OrderDetails;

import java.util.UUID;


public class OrderPersistenceRepository extends AbstractPersistenceRepository<OrderDetails, UUID> {

    public OrderPersistenceRepository() {
        super(OrderDetails.class);
    }

}
