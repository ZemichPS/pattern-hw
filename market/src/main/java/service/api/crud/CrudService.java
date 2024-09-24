package service.api.crud;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, I> {

    T save(T entity);

    T update(T entity);

    Optional<T> getById(I id);

    List<T> getAll();

    void delete(T entity);

}
