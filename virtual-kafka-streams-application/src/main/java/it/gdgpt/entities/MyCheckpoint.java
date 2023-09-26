package it.gdgpt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Table(name = "CHECKPOINTS")
@Entity
public class MyCheckpoint extends BaseEntity{
    private String index;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}