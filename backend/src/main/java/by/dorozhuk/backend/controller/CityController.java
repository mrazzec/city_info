package by.dorozhuk.backend.controller;

import by.dorozhuk.Exception.DoesntExistServiceException;
import by.dorozhuk.backend.entity.City;
import by.dorozhuk.backend.entity.Info;
import by.dorozhuk.backend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity findCities() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity saveCity(@RequestBody City city) {
        try {
            return ResponseEntity.ok(cityService.saveCity(city));
        } catch (DoesntExistServiceException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public void deleteCity(@RequestBody City city) {
        try {
            cityService.deleteCity(city.getName());
        } catch (DoesntExistServiceException e) {
        }
    }

    @PostMapping("/addinfo/{cityName}")
    public void updateCity(@RequestBody Info info, @PathVariable("cityName") String cityName) {
        cityService.updateCity(info, cityName);
    }

}
