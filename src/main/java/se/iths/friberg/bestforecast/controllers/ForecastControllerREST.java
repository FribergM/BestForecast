package se.iths.friberg.bestforecast.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.friberg.bestforecast.models.GenericForecast;
import se.iths.friberg.bestforecast.services.ForecastService;

@RestController
@RequestMapping("/rs/best-forecast")
public class ForecastControllerREST{
    
    private final ForecastService service;
    
    public ForecastControllerREST(ForecastService service){
        this.service = service;
    }
    
    @GetMapping("/tomorrow")
    public GenericForecast bestForecastTomorrow(){
        return service.getBestForecast();
    }
}
