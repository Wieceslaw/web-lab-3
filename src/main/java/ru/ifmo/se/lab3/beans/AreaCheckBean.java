package ru.ifmo.se.lab3.beans;

import lombok.*;
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
    // datetime delay x y r result
    @ManagedProperty(value = "#{repository}")
    private Repository repository;
    private Double x;
    private Double y;
    private Double r;

    public void makeHit() {
        // start timer
        // get x, y, r - validated values
        // check if hit
        // stop timer & create point object
        // save point object to repository
        boolean hit = CheckService.check(x, y, r);
    }
}
