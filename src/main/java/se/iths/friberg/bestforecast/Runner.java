package se.iths.friberg.bestforecast;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.iths.friberg.bestforecast.service.ForecastConverter;

@Component
public class Runner implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception{
        String date1 = "2024-05-25T20:00:00Z";
        String date2 = "2024-05-25T00:00";
        
        date1 = ForecastConverter.normalizeTimestamp(date1);
        date2 = ForecastConverter.normalizeTimestamp(date2);
        
        System.out.println(date1);
        System.out.println(date2);
        
    }
}
