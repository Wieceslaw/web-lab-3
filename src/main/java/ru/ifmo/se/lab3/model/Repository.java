package ru.ifmo.se.lab3.model;

import ru.ifmo.se.lab3.model.models.Hit;

public interface Repository {
    void save(Hit hit);
    void clear();
    void getAll();
}
