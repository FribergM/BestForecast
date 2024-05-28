
package se.iths.friberg.bestforecast.models.meteo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meteo {
    
    @JsonProperty("hourly")
    private Hourly hourly;
    
    public Hourly getHourly() {
        return hourly;
    }
    
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

}
