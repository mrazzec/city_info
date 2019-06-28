package by.dorozhuk.backend.service;

import by.dorozhuk.Exception.DoesntExistServiceException;
import by.dorozhuk.backend.entity.Info;

public interface InfoService {

    void saveInfo(Info info);

    Iterable<Info> findInfoByCityName(String name) throws DoesntExistServiceException;
}