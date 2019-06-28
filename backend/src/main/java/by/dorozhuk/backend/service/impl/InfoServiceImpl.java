package by.dorozhuk.backend.service.impl;

import by.dorozhuk.Exception.DoesntExistServiceException;
import by.dorozhuk.backend.entity.City;
import by.dorozhuk.backend.entity.Info;
import by.dorozhuk.backend.repository.InfoRepository;
import by.dorozhuk.backend.service.CityService;
import by.dorozhuk.backend.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {

    @Autowired
    InfoRepository infoRepository;

    @Autowired
    CityService cityService;


    @Override
    public void saveInfo(Info info) {
        infoRepository.save(info);
    }

    @Override
    public Iterable<Info> findInfoByCityName(String name) throws DoesntExistServiceException {
        City city = cityService.findCityByName(name);
        if (city == null)
            throw new DoesntExistServiceException("City " + name + " doesnt exist");

        return city.getInfos();
    }
}
