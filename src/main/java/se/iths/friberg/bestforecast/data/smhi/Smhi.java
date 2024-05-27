
package se.iths.friberg.bestforecast.data.smhi;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "timeSeries"
//})
public class Smhi {

    @JsonProperty("timeSeries")
    private List<TimeSeries> timeSeries;

    @JsonProperty("timeSeries")
    public List<TimeSeries> getTimeSeries() {
        return timeSeries;
    }

//    @JsonProperty("timeSeries")
    public void setTimeSeries(List<TimeSeries> timeSeries) {
        this.timeSeries = timeSeries;
    }

}
