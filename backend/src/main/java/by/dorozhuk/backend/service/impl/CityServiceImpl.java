package by.dorozhuk.backend.service.impl;

import by.dorozhuk.Exception.DoesntExistServiceException;
import by.dorozhuk.backend.entity.City;
import by.dorozhuk.backend.entity.Info;
import by.dorozhuk.backend.repository.CityRepository;
import by.dorozhuk.backend.service.CityService;
import by.dorozhuk.backend.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    InfoService infoService;

    @Override
    public City saveCity(City city) throws DoesntExistServiceException {
        if (findCityByName(city.getName()) != null)
            throw new DoesntExistServiceException("City " + city + " already exist");
        city.setInfos(new ArrayList<>());
        return cityRepository.save(city);
    }

    @Override
    public void deleteCity(String name) throws DoesntExistServiceException {
        City city = findCityByName(name);
        if (city == null)
            throw new DoesntExistServiceException("City " + name + " doesnt exist");

        cityRepository.delete(city);
    }

    @Override
    public void updateCity(Info info, String cityName) {
        City city = findCityByName(cityName);

        infoService.saveInfo(info);
        List<Info> infos = city.getInfos();
        infos.add(info);
        city.setInfos(infos);
        cityRepository.save(city);
    }

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findCityByName(String name) {
        return cityRepository.findCityByName(name);
    }

}
