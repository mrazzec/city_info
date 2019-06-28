package by.dorozhuk.backend.service;

import by.dorozhuk.Exception.DoesntExistServiceException;
import by.dorozhuk.backend.entity.City;
import by.dorozhuk.backend.entity.Info;

public interface CityService {

    City saveCity(City city) throws DoesntExistServiceException;

    void deleteCity(String name) throws DoesntExistServiceException;

    void updateCity(Info info, String cityInfo);

    Iterable<City> findAll();

    City findCityByName(String name);

}
