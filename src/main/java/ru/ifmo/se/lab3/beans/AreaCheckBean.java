package ru.ifmo.se.lab3.beans;

import lombok.*;
import ru.ifmo.se.lab3.entities.Result;
import ru.ifmo.se.lab3.model.Repository;
import ru.ifmo.se.lab3.services.CheckService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="areaCheck")
@SessionScoped
@Getter
@Setter
@NoArgsConstructor
public class AreaCheckBean implements Serializable {
    @ManagedProperty(value = "#{repository}")
    private Repository repository;
    private Double x;
    private Double y;
    private Double r;

    public void hit() {
        long startTime = System.nanoTime();
        Result result = new Result(
                System.currentTimeMillis() / 1000L,
                (System.nanoTime() - startTime),
                x,
                y,
                r,
                CheckService.check(x, y, r)
        );
        repository.createWithTransaction(result);
    }
}
