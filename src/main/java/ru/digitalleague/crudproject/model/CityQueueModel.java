package ru.digitalleague.crudproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="city_queue")
public class CityQueueModel {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cityId;

    @Column(name = "name")
    private String name;

    @Column(name="queue")
    private String queue;
}
