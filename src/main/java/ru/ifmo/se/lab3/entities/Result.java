package ru.ifmo.se.lab3.entities;



import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "result")
@NamedQueries({
        @NamedQuery(name = "Result.findAll", query = "select r from Result r"),
        @NamedQuery(name = "Result.removeAll", query = "delete from Result")
})
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private long datetime;
    @Column(nullable = false)
    private long delay;
    @Column(nullable = false)
    private double x;
    @Column(nullable = false)
    private double y;
    @Column(nullable = false)
    private double r;
    @Column(nullable = false)
    private boolean result;

    public Result(long datetime, long delay, double x, double y, double r, boolean result) {
        this.datetime = datetime;
        this.delay = delay;
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

    public String getFormattedDate() {
        Instant dateTime = Instant.ofEpochSecond(datetime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss").withZone(ZoneOffset.UTC);
        return formatter.format(dateTime);
    }

    public String getFormattedDelay() {
        return delay + " ns";
    }

    public String getFormattedResult() {
        return result ? "hit" : "miss";
    }
}
