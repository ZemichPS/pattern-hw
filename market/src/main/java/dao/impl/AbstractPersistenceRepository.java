package dao.impl;

import dao.api.CrudRepository;
import lombok.AccessLevel;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractPersistenceRepository<T, I extends Serializable> implements CrudRepository<T, I> {

    @Getter(AccessLevel.PACKAGE)
    private final SessionFactory sessionFactory;

    private final Class<T> entityClass;

    public AbstractPersistenceRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    public T create(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save entity.", e);
        }

    }

    @Override
    public T update(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update entity.", e);
        }
    }

    @Override
    public List<T> getAll() {
        try(Session session = sessionFactory.openSession()) {
            String hql = "from " + entityClass.getName();
            Query<T> query = session.createQuery(hql, entityClass);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all entities.", e);
        }
    }

    @Override
    public Optional<T> getById(I id) {
        try(Session session = sessionFactory.openSession()) {
            T result = session.get(entityClass, id);
            return Optional.ofNullable(result);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get entity by id.", e);
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete entity.", e);
        }
    }

}
