package se.iths.friberg.bestforecast.services;

import org.springframework.stereotype.Service;
import se.iths.friberg.bestforecast.data.WeatherRESTClient;
import se.iths.friberg.bestforecast.models.met.Met;
import se.iths.friberg.bestforecast.models.meteo.Meteo;
import se.iths.friberg.bestforecast.models.smhi.Smhi;
import se.iths.friberg.bestforecast.exceptions.ForecastDataException;
import se.iths.friberg.bestforecast.models.GenericForecast;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static se.iths.friberg.bestforecast.services.ScoreCalculator.calculateForecastScore;

@Service
public class ForecastService{
    
    private final WeatherRESTClient client;
    
    public ForecastService(WeatherRESTClient client){
        this.client = client;
    }
    
    public GenericForecast getBestForecast(){
        List<GenericForecast> tomorrowsForecasts = tomorrowsForecasts();
        
        if(tomorrowsForecasts.size() < 3){
            throw new ForecastDataException("Error. Failed to get all forecasts for tomorrow.");
        }
        
        double metScore = calculateForecastScore(tomorrowsForecasts.get(0));
        double smhiScore = calculateForecastScore(tomorrowsForecasts.get(1));
        double meteoScore = calculateForecastScore(tomorrowsForecasts.get(2));
        
        double highestScore = Math.max(metScore,Math.max(smhiScore,meteoScore));
        
        if(highestScore == metScore){
            return tomorrowsForecasts.get(0);
        }else if(highestScore == smhiScore){
            return tomorrowsForecasts.get(1);
        }else{
            return tomorrowsForecasts.get(2);
        }
    }
    
    private List<GenericForecast> tomorrowsForecasts(){
        List<GenericForecast> allForeCasts = getAllForecasts();

        LocalDateTime tomorrowDate = LocalDateTime.now().plusDays(1);
        
        ZonedDateTime formattedTomorrowDate = tomorrowDate.atZone(ZoneOffset.UTC);
        String tomorrow = ForecastConverter.FORMAT_WITH_TIMEZONE.format(formattedTomorrowDate);
        tomorrow = tomorrow.substring(0,13); //Makes it only contain date + the hour.

        List<GenericForecast> forecastsTomorrow = new ArrayList<>();

        for(GenericForecast forecast : allForeCasts){
            if(forecast.getTime().contains(tomorrow)){
                forecastsTomorrow.add(forecast);
            }
        }
        return forecastsTomorrow;

    }
    
    private List<GenericForecast> getAllForecasts(){
        CompletableFuture<Met> metFuture = client.fetchMetForecast();
        CompletableFuture<Smhi> smhiFuture = client.fetchSmhiForecast();
        CompletableFuture<Meteo> meteoFuture = client.fetchMeteoForecast();
        
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(metFuture,smhiFuture,meteoFuture);
        
        try{
            allFutures.get(10, TimeUnit.SECONDS);
        }catch(InterruptedException | ExecutionException | TimeoutException e){
            throw new ForecastDataException("Error while fetching forecasts: "+ e.getMessage());
        }
        
        Met met;
        Smhi smhi;
        Meteo meteo;
        
        try{
            met = metFuture.get();
            smhi = smhiFuture.get();
            meteo = meteoFuture.get();
            
            if(met == null || smhi == null || meteo == null){
                throw new ForecastDataException("[Error] Forecasts data is null");
            }
            
        }catch(InterruptedException | ExecutionException e){
            throw new ForecastDataException("Error while getting future data: "+ e.getMessage());
        }
        
        List<GenericForecast> forecasts = new ArrayList<>();
        forecasts.addAll(ForecastConverter.convertForecast(met));
        forecasts.addAll(ForecastConverter.convertForecast(smhi));
        forecasts.addAll(ForecastConverter.convertForecast(meteo));
        
        return forecasts;
    }
}
