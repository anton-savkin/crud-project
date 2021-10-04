package ru.digitalleague.crudproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.digitalleague.crudproject.model.TaxiDriveInfoModel;

public interface TaxiDriveInfoRepository extends CrudRepository<TaxiDriveInfoModel, Long> {}
