package ru.digitalleague.crudproject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.digitalleague.crudproject.model.CarModel;

public interface CarRepository extends CrudRepository<CarModel, Long> {
}
