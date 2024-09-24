package service.impl;

import dao.impl.OrderPersistenceRepository;
import infrastructure.annotation.Log;
import model.OrderDetails;
import service.api.crud.OrderDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderPersistenceRepository repository;

    public OrderDetailsServiceImpl(OrderPersistenceRepository repository) {
        this.repository = repository;
    }

    @Override
    @Log
    public OrderDetails save(OrderDetails orderDetails) {
        return repository.create(orderDetails);
    }

    @Override
    public OrderDetails update(OrderDetails entity) {
        return repository.update(entity);
    }

    @Override
    public Optional<OrderDetails> getById(UUID id) {
        return repository.getById(id);
    }

    @Override
    @Log
    public List<OrderDetails> getAll() {
        return repository.getAll();
    }

    @Override
    @Log
    public void delete(OrderDetails entity) {
        repository.delete(entity);
    }
}
