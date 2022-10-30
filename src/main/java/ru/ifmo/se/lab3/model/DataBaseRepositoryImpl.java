package ru.ifmo.se.lab3.model;

import ru.ifmo.se.lab3.entities.Result;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.List;
import java.util.function.Function;


@ManagedBean(name = "repository")
@ApplicationScoped
public class DataBaseRepositoryImpl implements Repository {
    private final EntityManagerFactory factory;

    public DataBaseRepositoryImpl() {
        factory = Persistence.createEntityManagerFactory("lab3");
    }

    public Result createWithOutTransaction(EntityManager entityManager, Result result) {
        entityManager.persist(result);
        return result;
    }

    public Result createWithTransaction(Result result) {
        return executeWithTransaction(entityManager -> {
            createWithOutTransaction(entityManager, result);
            return result;
        });
    }

    public List<Result> readAllWithOutTransaction(EntityManager entityManager) {
        return entityManager.createNamedQuery("Result.findAll", Result.class).getResultList();
    }

    public List<Result> readAllWithTransaction() {
        return executeWithTransaction(this::readAllWithOutTransaction);
    }

    public int deleteAllWithOutTransaction(EntityManager entityManager) {
        return entityManager.createNamedQuery("Result.removeAll").executeUpdate();
    }

    public int deleteAllWithTransaction() {
        return executeWithTransaction(this::deleteAllWithOutTransaction);
    }

    public <T> T executeWithTransaction(Function<EntityManager, T> function) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction tx = null;
        T result;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            result = function.apply(entityManager);
            tx.commit();
            return result;
        }
        catch (RuntimeException e) {
            if ( tx != null && tx.isActive() ) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            entityManager.close();
        }
    }
}
