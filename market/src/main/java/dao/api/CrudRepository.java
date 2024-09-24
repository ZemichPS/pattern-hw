package dao.api;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, I extends Serializable> {
    T create(T entity);

    T update(T entity);

    List<T> getAll();

    Optional<T> getById(I id);

    void delete(T entity);

}
