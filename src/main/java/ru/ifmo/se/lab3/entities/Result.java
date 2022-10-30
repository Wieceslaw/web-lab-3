package ru.ifmo.se.lab3.entities;



import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "result")
@NamedQueries({
        @NamedQuery(name = "Result.findAll", query = "select r from Result r"),
        @NamedQuery(name = "Result.removeAll", query = "delete from Result")
})
public class Result implements Serializable {
    @Id
    private int id;
    private long datetime;
    private long delay;
    private double x;
    private double y;
    private double r;
    private boolean result;
}
