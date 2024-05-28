package se.iths.friberg.bestforecast;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.iths.friberg.bestforecast.models.GenericForecast;
import se.iths.friberg.bestforecast.services.ForecastService;

import java.util.List;

public class Runner implements CommandLineRunner{
    
    ForecastService service;
    
    public Runner(ForecastService service){
        this.service = service;
    }
    
    @Override
    public void run(String... args) throws Exception{
        long start = System.currentTimeMillis();
        GenericForecast bestForecast = service.getBestForecast();
        List<GenericForecast> forecasts = service.tomorrowsForecasts();
        
        System.out.println(bestForecast+"\n");
        for(GenericForecast f : forecasts){
            System.out.println(f);
        }
        long end = System.currentTimeMillis();
        System.out.println("Milliseconds: "+(end-start));
        
    }
}
