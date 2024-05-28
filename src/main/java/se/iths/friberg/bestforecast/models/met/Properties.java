
package se.iths.friberg.bestforecast.models.met;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Properties {

    @JsonProperty("timeseries")
    private List<Timeseries> timeseries;
    
    public List<Timeseries> getTimeseries() {
        return timeseries;
    }
    
    public void setTimeseries(List<Timeseries> timeseries) {
        this.timeseries = timeseries;
    }

}
