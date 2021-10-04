package ru.digitalleague.crudproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.crudproject.model.CarModel;
import ru.digitalleague.crudproject.repository.CarRepository;

import java.util.Optional;

@RestController
@Slf4j
public class CarController {

    @Autowired
    CarRepository carRepository;

    @PostMapping("/car")
    public ResponseEntity<CarModel> insert(@RequestParam String model) {
        log.info("Received message from postman: POST request to insert car model: " + model);

        CarModel carModel = new CarModel(model);

        try {
            carRepository.save(carModel);
            return new ResponseEntity<>(carModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/car")
    public ResponseEntity<CarModel> selectById(@RequestParam Long id) {
        log.info("Received message from postman: GET request to select car model with ID: " + id);

        Optional<CarModel> carModel = carRepository.findById(id);

        return carModel.map(model -> new ResponseEntity<>(model, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/car")
    public ResponseEntity<CarModel> updateById(@RequestParam Long id, @RequestParam String model) {
        log.info("Received message from postman: PUT request to update car model with ID: " + id);

        Optional<CarModel> carModel = carRepository.findById(id);

        if (carModel.isPresent()) {
            CarModel newCarModel = carModel.get();
            newCarModel.setModel(model);
            return new ResponseEntity<>(carRepository.save(newCarModel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/car")
    public ResponseEntity<HttpStatus> deleteById(@RequestParam Long id) {
        log.info("Received message from postman: PUT request to delete car model with ID: " + id);

        try {
            carRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
