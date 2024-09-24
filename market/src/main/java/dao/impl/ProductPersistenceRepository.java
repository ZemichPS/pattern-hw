package dao.impl;


import model.Product;

import java.util.UUID;

public class ProductPersistenceRepository extends AbstractPersistenceRepository<Product, UUID> {

    public ProductPersistenceRepository() {
        super(Product.class);
    }
}
