package ru.ifmo.se.lab3.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ifmo.se.lab3.entities.Result;
import ru.ifmo.se.lab3.model.Repository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="history")
@SessionScoped
@Getter
@Setter
@NoArgsConstructor
public class HistoryBean implements Serializable {
    @ManagedProperty(value = "#{repository}")
    private Repository repository;

    public List<Result> getHistory() {
        return repository.readAllWithTransaction();
    }

    public void clear() {
        repository.deleteAllWithTransaction();
    }
}
