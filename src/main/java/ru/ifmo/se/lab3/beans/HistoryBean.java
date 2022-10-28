package ru.ifmo.se.lab3.beans;

import ru.ifmo.se.lab3.model.models.Hit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="history")
@SessionScoped
public class HistoryBean implements Serializable {
    List<Hit> getHistory() {return null;}
    void clear() {}
}
