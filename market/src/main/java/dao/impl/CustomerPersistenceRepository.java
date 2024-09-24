package dao.impl;

import model.Customer;

import java.util.UUID;

public class CustomerPersistenceRepository extends AbstractPersistenceRepository<Customer, UUID> {

    public CustomerPersistenceRepository() {
        super(Customer.class);
    }
}
