package ru.digitalleague.crudproject.model;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name="taxi_drive_info")
public class TaxiDriveInfoModel {

    @Id
    @Column(name = "driver_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long driverId;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="level")
    private int level;

    @Column(name="car_model")
    private String carModel;

    @Column(name="create_dttm")
    private OffsetDateTime createDttm;

    public TaxiDriveInfoModel(String lastName, String firstName, int level, String carModel) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.level = level;
        this.carModel = carModel;
        this.createDttm = OffsetDateTime.now();
    }

    public TaxiDriveInfoModel() {

    }

}
