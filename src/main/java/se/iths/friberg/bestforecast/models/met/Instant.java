
package se.iths.friberg.bestforecast.models.met;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Instant {

    @JsonProperty("details")
    private Details details;
    
    public Details getDetails() {
        return details;
    }
    
    public void setDetails(Details details) {
        this.details = details;
    }

}
