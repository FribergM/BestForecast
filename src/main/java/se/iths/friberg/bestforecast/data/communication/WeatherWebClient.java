package se.iths.friberg.bestforecast.data.communication;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import se.iths.friberg.bestforecast.data.met.Met;
import se.iths.friberg.bestforecast.data.meteo.Meteo;
import se.iths.friberg.bestforecast.data.smhi.Smhi;

import java.util.concurrent.CompletableFuture;

@Component
public class WeatherWebClient{
    
    private final WebClient client = WebClient.create();
    
    public CompletableFuture<Met> fetchMetForecast(){
        CompletableFuture<Met> metFuture = new CompletableFuture<>();
        client
                .get()
                .uri("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300")
                .retrieve()
                .bodyToMono(Met.class)
                .subscribe(
                        metFuture::complete,
                        metFuture::completeExceptionally);
        return metFuture;
    }
    
    public CompletableFuture<Smhi> fetchSmhiForecast(){
        CompletableFuture<Smhi> smhiFuture = new CompletableFuture<>();
        client
                .get()
                .uri("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json")
                .retrieve()
                .bodyToMono(Smhi.class)
                .subscribe(
                        smhiFuture::complete,
                        smhiFuture::completeExceptionally);
        return smhiFuture;
    }
    
    public CompletableFuture<Meteo> fetchMeteoForecast(){
        CompletableFuture<Meteo> meteoFuture = new CompletableFuture<>();
        client
                .get()
                .uri("https://api.open-meteo.com/v1/dwd-icon?latitude=59.312233&longitude=18.017975&hourly=temperature_2m,relative_humidity_2m,precipitation")
                .retrieve()
                .bodyToMono(Meteo.class)
                .subscribe(
                        meteoFuture::complete,
                        meteoFuture::completeExceptionally);
        return meteoFuture;
    }
}
