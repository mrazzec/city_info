package by.dorozhuk.backend.controller;

import by.dorozhuk.Exception.DoesntExistServiceException;
import by.dorozhuk.backend.service.CityService;
import by.dorozhuk.backend.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;

    @Autowired
    CityService cityService;

    @GetMapping("/cityname/{cityname}")
    public ResponseEntity findInfoByCityName(@PathVariable(name = "cityname") String cityname) {
        try {
            return ResponseEntity.ok(infoService.findInfoByCityName(cityname));
        } catch (DoesntExistServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
