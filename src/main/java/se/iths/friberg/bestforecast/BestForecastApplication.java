package se.iths.friberg.bestforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BestForecastApplication{
    
    public static void main(String[] args){
        SpringApplication.run(BestForecastApplication.class, args);
    }
    
    
    //TODO
    // - Create forecast converter classes implementing the same interface
    // - Create generic forecast class
}
