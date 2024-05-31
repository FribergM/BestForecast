package se.iths.friberg.bestforecast.services;

import se.iths.friberg.bestforecast.models.met.Details;
import se.iths.friberg.bestforecast.models.met.Met;
import se.iths.friberg.bestforecast.models.met.Timeseries;
import se.iths.friberg.bestforecast.models.meteo.Hourly;
import se.iths.friberg.bestforecast.models.meteo.Meteo;
import se.iths.friberg.bestforecast.models.smhi.Parameter;
import se.iths.friberg.bestforecast.models.smhi.Smhi;
import se.iths.friberg.bestforecast.models.smhi.TimeSeries;
import se.iths.friberg.bestforecast.models.GenericForecast;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ForecastConverter{
    
    public static final DateTimeFormatter FORMAT_WITH_TIMEZONE = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
    public static final DateTimeFormatter FORMAT_WITHOUT_TIMEZONE = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    
    public static List<GenericForecast> convertForecast(Met met){
        List<GenericForecast> forecasts = new ArrayList<>();
        
        String origin = "MET(Norway)";
        
        List<Timeseries> hourlyForecasts = met.getProperties().getTimeseries();
        
        for(Timeseries hourlyForecast : hourlyForecasts){
            
            Details details = hourlyForecast.getData().getInstant().getDetails();
            
            String timestamp = hourlyForecast.getTime();
            timestamp = normalizeTimestamp(timestamp);
            double temp = details.getAirTemperature();
            int humidity = (int) details.getRelativeHumidity();
            
            forecasts.add(new GenericForecast(origin, timestamp, temp, humidity));
        }
        return forecasts;
    }
    public static List<GenericForecast> convertForecast(Smhi smhi){
        List<GenericForecast> forecasts = new ArrayList<>();
        
        String origin = "SMHI";
        
        List<TimeSeries> hourlyForecasts = smhi.getTimeSeries();
        
        for(TimeSeries hourlyForecast : hourlyForecasts){
            String timestamp = hourlyForecast.getValidTime();
            timestamp = normalizeTimestamp(timestamp);
            double temp = 0;
            int humidity = 0;
            
            List<Parameter> details = hourlyForecast.getParameters();
            for(Parameter p : details){
                if(p.getName().equals("t")){
                    temp = p.getValues().getFirst();
                }else if(p.getName().equals("r")){
                    humidity = p.getValues().getFirst();
                }
            }
            forecasts.add(new GenericForecast(origin, timestamp, temp, humidity));
        }
        return forecasts;
    }
    public static List<GenericForecast> convertForecast(Meteo meteo){
        List<GenericForecast> forecasts = new ArrayList<>();
        
        String origin = "Open-Meteo(Germany)";
        Hourly hourlyForecasts = meteo.getHourly();
        
        int loopDuration = hourlyForecasts.getTemperature().size();
        for(int i=0;i<loopDuration;i++){
            String timestamp = hourlyForecasts.getTime().get(i);
            timestamp = normalizeTimestamp(timestamp);
            double temp = hourlyForecasts.getTemperature().get(i);
            int humidity = hourlyForecasts.getHumidity().get(i);
            
            forecasts.add(new GenericForecast(origin, timestamp,temp,humidity));
        }
        return forecasts;
    }
    
    public static String normalizeTimestamp(String timestamp){
        try{
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(timestamp, FORMAT_WITH_TIMEZONE);
            return FORMAT_WITH_TIMEZONE.format(zonedDateTime);
        }catch(DateTimeParseException e1){
            try{
                LocalDateTime localDateTime = LocalDateTime.parse(timestamp, FORMAT_WITHOUT_TIMEZONE);
                
                ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);
                return FORMAT_WITH_TIMEZONE.format(zonedDateTime);
            }catch(DateTimeParseException e2){
                throw new IllegalArgumentException("Invalid timestamp format: " + timestamp);
            }
        }
    }
}
