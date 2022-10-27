package de.hamburgchimps.wtfjpatransactions.entity;

import de.hamburgchimps.wtfjpatransactions.type.TacoType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Taco {
    @Id
    @GeneratedValue
    private Long id;

    public Taco() {}

    public Taco(TacoType type) {
        this.type = type;
    }

    @Enumerated
    private TacoType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TacoType getType() {
        return type;
    }

    public void setType(TacoType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
