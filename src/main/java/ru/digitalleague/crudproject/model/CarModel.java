package ru.digitalleague.crudproject.model;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name="car")
public class CarModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name="createdttm")
    private OffsetDateTime createDttm;

    public CarModel(String model) {
        this.model = model;
        this.createDttm = OffsetDateTime.now();
    }

    public CarModel() {

    }
}
