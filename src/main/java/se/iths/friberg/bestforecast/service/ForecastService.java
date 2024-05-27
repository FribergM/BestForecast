package se.iths.friberg.bestforecast.service;

import org.springframework.stereotype.Service;
import se.iths.friberg.bestforecast.model.GenericForecast;

import java.util.List;


@Service
public class ForecastService{
    
    private List<GenericForecast> metForecasts;
    private List<GenericForecast> smhiForecasts;
    private List<GenericForecast> meteoForecasts;
}
