
package se.iths.friberg.bestforecast.models.met;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Details {

    @JsonProperty("air_pressure_at_sea_level")
    private Double airPressureAtSeaLevel;
    @JsonProperty("air_temperature")
    private Double airTemperature;
    @JsonProperty("cloud_area_fraction")
    private Double cloudAreaFraction;
    @JsonProperty("relative_humidity")
    private Double relativeHumidity;
    @JsonProperty("wind_from_direction")
    private Double windFromDirection;
    @JsonProperty("wind_speed")
    private Double windSpeed;
    
    public Double getAirPressureAtSeaLevel() {
        return airPressureAtSeaLevel;
    }
    
    public void setAirPressureAtSeaLevel(Double airPressureAtSeaLevel) {
        this.airPressureAtSeaLevel = airPressureAtSeaLevel;
    }
    
    public double getAirTemperature() {
        return airTemperature;
    }
    
    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }
    
    public double getCloudAreaFraction() {
        return cloudAreaFraction;
    }
    
    public void setCloudAreaFraction(double cloudAreaFraction) {
        this.cloudAreaFraction = cloudAreaFraction;
    }
    
    public double getRelativeHumidity() {
        return relativeHumidity;
    }
    
    public void setRelativeHumidity(double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }
    
    public double getWindFromDirection() {
        return windFromDirection;
    }
    
    public void setWindFromDirection(double windFromDirection) {
        this.windFromDirection = windFromDirection;
    }
    
    public double getWindSpeed() {
        return windSpeed;
    }
    
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

}
