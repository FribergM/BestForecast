package se.iths.friberg.bestforecast.models;

public class GenericForecast{
    
    private String origin;
    private String time;
    private double temperature;
    private int humidity;
    
    public GenericForecast(String origin, String time, double temperature, int humidity){
        this.origin = origin;
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
    }
    
    public String getOrigin(){
        return origin;
    }
    
    public void setOrigin(String origin){
        this.origin = origin;
    }
    
    public String getTime(){
        return time;
    }
    
    public String formattedTime(){
        return time.substring(0,16).replace('T',' ');
    }
    
    public void setTime(String time){
        this.time = time;
    }
    
    public double getTemperature(){
        return temperature;
    }
    
    public void setTemperature(double temperature){
        this.temperature = temperature;
    }
    
    public int getHumidity(){
        return humidity;
    }
    
    public void setHumidity(int humidity){
        this.humidity = humidity;
    }
    
    @Override
    public String toString(){
        return "GenericForecast{" +
                "origin='" + origin + '\'' +
                ", time='" + time + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
    
    public String timeToString(){
        return "The best weather forecast for Liljeholmen at: "+ formattedTime();
    }
    public String providerToString(){
        return "Provider: " + origin;
    }
    public String tempToString(){
        return "Temperature: " + temperature + "°C";
    }
    public String humidityToString(){
        return "Humidity: " + humidity + "%";
    }
}
