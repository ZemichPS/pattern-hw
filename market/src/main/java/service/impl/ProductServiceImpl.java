package service.impl;

import dao.impl.ProductPersistenceRepository;
import model.Product;
import service.api.crud.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    private final ProductPersistenceRepository repository;
    private final EventManager eventManager;

    public ProductServiceImpl(ProductPersistenceRepository repository,
                              EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    @Override
    public Product save(Product product) {
        repository.create(product);
        //if(product.isSale()) eventManager.publish("NEW_PRODUCT_ON_SALE", product);
        return product;
    }

    @Override
    public Product update(Product entity) {
        return repository.update(entity);
    }

    @Override
    public Optional<Product> getById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(Product entity) {
        repository.delete(entity);
    }
}
