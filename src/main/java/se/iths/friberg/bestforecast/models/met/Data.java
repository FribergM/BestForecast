
package se.iths.friberg.bestforecast.models.met;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    @JsonProperty("instant")
    private Instant instant;
    
    public Instant getInstant() {
        return instant;
    }
    
    public void setInstant(Instant instant) {
        this.instant = instant;
    }

}
