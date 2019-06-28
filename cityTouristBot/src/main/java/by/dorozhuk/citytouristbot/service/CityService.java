package by.dorozhuk.citytouristbot.service;

import by.dorozhuk.citytouristbot.model.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CityService {

    @Value("http://localhost:8080/city")
    private String backendServerUrl;

    public String getCities() {
        RestTemplate restTemplate = new RestTemplate();
        City[] cities = restTemplate.getForObject(backendServerUrl + "/cities", City[].class);
        String cityNames = " ";
        if (cities.length != 0) {
            for (City city : cities) {
                cityNames += city.getName() + " ";
            }
        } else
            return "Empty city list";

        return cityNames;
    }

    public String saveCity(String name) {
        RestTemplate restTemplate = new RestTemplate();
        City city;
        try{
            city = restTemplate.postForEntity(backendServerUrl + "/save", new City(name), City.class).getBody();
        } catch (HttpClientErrorException e) {
            return "exception";
        }
        return city.getName() + " saved";
    }

    public String deleteCity(String name) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            restTemplate.postForEntity(backendServerUrl + "/delete", new City(name), City.class).getBody();
        } catch (HttpClientErrorException e) {
            return "exception";
        }
        return "deleted";
    }
}
