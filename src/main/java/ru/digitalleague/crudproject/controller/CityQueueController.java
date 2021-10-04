package ru.digitalleague.crudproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.crudproject.model.CityQueueModel;
import ru.digitalleague.crudproject.repository.CityQueueRepository;

import java.util.Optional;

@RestController
@Slf4j
public class CityQueueController {

    @Autowired
    CityQueueRepository cityQueueRepository;

    @PostMapping("/city-queue")
    public ResponseEntity<CityQueueModel> insert(@RequestBody CityQueueModel cityQueueModel) {
        log.info("Received message from postman: POST request to insert city queue model: " + cityQueueModel);

        try {
            cityQueueRepository.save(cityQueueModel);
            return new ResponseEntity<>(cityQueueModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/city-queue")
    public ResponseEntity<CityQueueModel> selectById(@RequestParam Long id) {
        log.info("Received message from postman: GET request to select city queue model with ID: " + id);

        Optional<CityQueueModel> cityQueueModel = cityQueueRepository.findById(id);

        return cityQueueModel.map(model -> new ResponseEntity<>(model, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/city-queue")
    public ResponseEntity<CityQueueModel> updateById(@RequestParam Long id, @RequestBody CityQueueModel cityQueueModel) {
        log.info("Received message from postman: PUT request to update city queue model with ID: " + id);

        Optional<CityQueueModel> oldCityQueueModel = cityQueueRepository.findById(id);

        if (oldCityQueueModel.isPresent()) {
            CityQueueModel newCityQueueModel = oldCityQueueModel.get();

            if (cityQueueModel.getName() != null && !cityQueueModel.getName().isEmpty()) {
                newCityQueueModel.setName(cityQueueModel.getName());
            }

            if (cityQueueModel.getQueue() != null && !cityQueueModel.getQueue().isEmpty()) {
                newCityQueueModel.setQueue(cityQueueModel.getQueue());
            }

            return new ResponseEntity<>(cityQueueRepository.save(newCityQueueModel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/city-queue")
    public ResponseEntity<HttpStatus> deleteById(@RequestParam Long id) {
        log.info("Received message from postman: PUT request to delete city queue model with ID: " + id);

        try {
            cityQueueRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
