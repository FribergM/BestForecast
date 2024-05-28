package se.iths.friberg.bestforecast.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.friberg.bestforecast.models.GenericForecast;
import se.iths.friberg.bestforecast.services.ForecastService;

@Controller
@RequestMapping("/best-forecast")
public class ForecastController{
    
    private final ForecastService service;
    
    public ForecastController(ForecastService service){
        this.service = service;
    }
    
    @GetMapping("/tomorrow")
    public String bestForecastTomorrow(Model model){
        GenericForecast bestForecast = service.getBestForecast();
        model.addAttribute("bestForecast", bestForecast);
        
        return "forecast-tomorrow";
    }
    
}
