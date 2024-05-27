
package se.iths.friberg.bestforecast.data.meteo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hourly {

    @JsonProperty("time")
    private List<String> time;
    @JsonProperty("temperature_2m")
    private List<Double> temperature;
    @JsonProperty("relative_humidity_2m")
    private List<Integer> humidity;
    @JsonProperty("precipitation")
    private List<Double> precipitation;
    
    public List<String> getTime() {
        return time;
    }
    
    public void setTime(List<String> time) {
        this.time = time;
    }
    
    public List<Double> getTemperature() {
        return temperature;
    }
    
    public void setTemperature(List<Double> temperature) {
        this.temperature = temperature;
    }
    
    public List<Integer> getHumidity() {
        return humidity;
    }
    
    public void setHumidity(List<Integer> humidity) {
        this.humidity = humidity;
    }
    
    public List<Double> getPrecipitation() {
        return precipitation;
    }
    
    public void setPrecipitation(List<Double> precipitation) {
        this.precipitation = precipitation;
    }

}
