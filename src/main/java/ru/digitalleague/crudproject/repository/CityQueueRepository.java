package ru.digitalleague.crudproject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.digitalleague.crudproject.model.CityQueueModel;

public interface CityQueueRepository extends CrudRepository<CityQueueModel, Long> {
}
