package se.iths.friberg.bestforecast.service;

import se.iths.friberg.bestforecast.data.met.Details;
import se.iths.friberg.bestforecast.data.met.Met;
import se.iths.friberg.bestforecast.data.met.Timeseries;
import se.iths.friberg.bestforecast.data.meteo.Hourly;
import se.iths.friberg.bestforecast.data.meteo.Meteo;
import se.iths.friberg.bestforecast.data.smhi.Parameter;
import se.iths.friberg.bestforecast.data.smhi.Smhi;
import se.iths.friberg.bestforecast.data.smhi.TimeSeries;
import se.iths.friberg.bestforecast.model.GenericForecast;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ForecastConverter{
    
    private static final DateTimeFormatter formatterWithTimeZone = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
    private static final DateTimeFormatter formatterWithoutTimeZone = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    
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
        
        int loopDuration = hourlyForecasts.size();
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
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(timestamp, formatterWithTimeZone);
            return formatterWithTimeZone.format(zonedDateTime);
        }catch(DateTimeParseException e1){
            try{
                LocalDateTime localDateTime = LocalDateTime.parse(timestamp, formatterWithoutTimeZone);
                
                ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);
                return formatterWithTimeZone.format(zonedDateTime);
            }catch(DateTimeParseException e2){
                throw new IllegalArgumentException("Invalid timestamp format: " + timestamp);
            }
        }
    }
}
