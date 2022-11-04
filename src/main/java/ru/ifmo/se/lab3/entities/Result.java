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
        @NamedQuery(name = "Result.findAll", query = "select r from Result r order by r.datetime desc"),
        @NamedQuery(name = "Result.removeAll", query = "delete from Result")
})
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private long datetime;
    @Column(nullable = false)
    private long startDelay;
    @Column(nullable = false)
    private long endDelay;
    @Column(nullable = false)
    private double x;
    @Column(nullable = false)
    private double y;
    @Column(nullable = false)
    private double r;
    @Column(nullable = false)
    private boolean result;

    public Result(long datetime, long startDelay, double x, double y, double r, boolean result) {
        this.datetime = datetime;
        this.startDelay = startDelay;
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

    @PrePersist
    protected void onCreate() {
        endDelay = System.nanoTime();
    }

    public String getFormattedDate() {
        Instant dateTime = Instant.ofEpochSecond(datetime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneOffset.UTC);
        return formatter.format(dateTime);
    }

    public String getFormattedDelay() {
        return getDelay() / 1000 + " mcs";
    }

    public String getFormattedResult() {
        return result ? "hit" : "miss";
    }

    public long getDelay() {
        return endDelay - startDelay;
    }
}
