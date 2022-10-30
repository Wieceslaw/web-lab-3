package ru.ifmo.se.lab3.model;

import ru.ifmo.se.lab3.entities.Result;

import javax.persistence.EntityManager;
import java.util.List;

public interface Repository {
    Result createWithTransaction(Result result);
    List<Result> readAllWithTransaction();
    int deleteAllWithTransaction();
    Result createWithOutTransaction(EntityManager entityManager, Result result);
    List<Result> readAllWithOutTransaction(EntityManager entityManager);
    int deleteAllWithOutTransaction(EntityManager entityManager);
}
