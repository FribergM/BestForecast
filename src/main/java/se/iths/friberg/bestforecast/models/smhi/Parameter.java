
package se.iths.friberg.bestforecast.models.smhi;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parameter {

    @JsonProperty("name")
    private String name;
    @JsonProperty("levelType")
    private String levelType;
    @JsonProperty("level")
    private Integer level;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("values")
    private List<Integer> values;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLevelType() {
        return levelType;
    }
    
    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

}
