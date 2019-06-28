package by.dorozhuk.backend.repository;

import by.dorozhuk.backend.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
    City findCityByName(String name);
}
