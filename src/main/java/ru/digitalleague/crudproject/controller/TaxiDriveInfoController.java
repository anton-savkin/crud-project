package ru.digitalleague.crudproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.crudproject.model.TaxiDriveInfoModel;
import ru.digitalleague.crudproject.repository.TaxiDriveInfoRepository;

import java.util.Optional;

@RestController
@Slf4j
public class TaxiDriveInfoController {

    @Autowired
    TaxiDriveInfoRepository taxiDriveInfoRepository;

//    @PostMapping("/taxi-drive-info")
//    public ResponseEntity<TaxiDriveInfoModel> insert(@RequestParam String lastName, @RequestParam String firstName, @RequestParam int level, @RequestParam String carModel) {
//        TaxiDriveInfoModel taxiDriveInfoModel = new TaxiDriveInfoModel(lastName, firstName, level, carModel);
//
//        log.info("Received message from postman: POST request to insert taxi drive info model: " + taxiDriveInfoModel);
//
//        try {
//            taxiDriveInfoRepository.save(taxiDriveInfoModel);
//            return new ResponseEntity<>(taxiDriveInfoModel, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/taxi-drive-info")
    public ResponseEntity<TaxiDriveInfoModel> insert(@RequestBody TaxiDriveInfoModel taxiDriveInfoModel) {
        TaxiDriveInfoModel newTaxiDriveInfoModel = new TaxiDriveInfoModel(
                taxiDriveInfoModel.getLastName(),
                taxiDriveInfoModel.getFirstName(),
                taxiDriveInfoModel.getLevel(),
                taxiDriveInfoModel.getCarModel());

        log.info("Received message from postman: POST request to insert taxi drive info model: " + newTaxiDriveInfoModel);

        try {
            taxiDriveInfoRepository.save(newTaxiDriveInfoModel);
            return new ResponseEntity<>(newTaxiDriveInfoModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/taxi-drive-info")
    public ResponseEntity<TaxiDriveInfoModel> selectById(@RequestParam Long id) {
        log.info("Received message from postman: GET request to select taxi drive info model with ID: " + id);

        Optional<TaxiDriveInfoModel> taxiDriveInfoModel = taxiDriveInfoRepository.findById(id);

        return taxiDriveInfoModel.map(model -> new ResponseEntity<>(model, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/taxi-drive-info")
    public ResponseEntity<TaxiDriveInfoModel> updateById(@RequestParam Long id, @RequestBody TaxiDriveInfoModel taxiDriveInfoModel) {
        log.info("Received message from postman: PUT request to update taxi drive info model with ID: " + id);

        Optional<TaxiDriveInfoModel> oldTaxiDriveInfoModel = taxiDriveInfoRepository.findById(id);

        if (oldTaxiDriveInfoModel.isPresent()) {
            TaxiDriveInfoModel newTaxiDriveInfoModel = oldTaxiDriveInfoModel.get();

            if (taxiDriveInfoModel.getLastName() != null && !taxiDriveInfoModel.getLastName().isEmpty()) {
                newTaxiDriveInfoModel.setLastName(taxiDriveInfoModel.getLastName());
            }

            if (taxiDriveInfoModel.getFirstName() != null && !taxiDriveInfoModel.getFirstName().isEmpty()) {
                newTaxiDriveInfoModel.setFirstName(taxiDriveInfoModel.getFirstName());
            }

            if (taxiDriveInfoModel.getLevel() != 0) {
                newTaxiDriveInfoModel.setLevel(taxiDriveInfoModel.getLevel());
            }

            if (taxiDriveInfoModel.getCarModel() != null && !taxiDriveInfoModel.getCarModel().isEmpty()) {
                newTaxiDriveInfoModel.setCarModel(taxiDriveInfoModel.getCarModel());
            }

            return new ResponseEntity<>(taxiDriveInfoRepository.save(newTaxiDriveInfoModel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/taxi-drive-info")
    public ResponseEntity<HttpStatus> deleteById(@RequestParam Long id) {
        log.info("Received message from postman: PUT request to delete taxi drive info model with ID: " + id);

        try {
            taxiDriveInfoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
