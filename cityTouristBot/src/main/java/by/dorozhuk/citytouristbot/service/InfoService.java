package by.dorozhuk.citytouristbot.service;

import by.dorozhuk.citytouristbot.model.City;
import by.dorozhuk.citytouristbot.model.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class InfoService {

    @Value("http://localhost:8080")
    private String backendServerUrl;

    public String saveInfo(String cityName, String info) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            restTemplate.postForEntity(backendServerUrl + "/city/addinfo/" + cityName,
                    new Info(info, new City(cityName)), Info.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            return "exception";
        }
        return "added";
    }

    public String findInfosByCity(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        Info[] infos = restTemplate.getForObject(backendServerUrl +
                "/info/cityname/" + cityName, Info[].class);
        String info = " ";
        if (infos.length != 0) {
            for (Info inf : infos) {
                info += inf.getInfo() + " ";
            }
        } else
            return "Empty info list";

        return info;
    }
}
