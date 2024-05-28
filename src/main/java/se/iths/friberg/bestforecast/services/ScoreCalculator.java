package se.iths.friberg.bestforecast.services;

import se.iths.friberg.bestforecast.models.GenericForecast;

public class ScoreCalculator{
    
    private static final int OPTIMAL_TEMP = 23;
    private static final int OPTIMAL_HUMIDITY = 60;
    
    public static double calculateForecastScore(GenericForecast forecast){
        double bestScore = 10000;
        double score;
        
        score = tempScorePenalty(bestScore,forecast);
        score = humidityScorePenalty(score,forecast);
        
        return score;
    }
    
    private static double tempScorePenalty(double score, GenericForecast forecast){
        // Returns a score based how close the forecast is to the "optimal" temperature.
        // The score gets worse multiplicatively based on how many degrees away it is,
        
        double multiplier = 1.0;
        int forecastTemp = (int) forecast.getTemperature();
        
        while(forecastTemp != OPTIMAL_TEMP){
            
            if(forecastTemp < OPTIMAL_TEMP){
                score-=500*multiplier;
                forecastTemp++;
                multiplier+=0.25;
            }else{
                score-=500*multiplier;
                forecastTemp--;
                multiplier+=0.15;
            }
        }
        return score;
    }
    private static double humidityScorePenalty(double score, GenericForecast forecast){
        // Returns a score based how close the forecast is to the "optimal" humidity.
        // The score gets worse multiplicatively based on how many percentage units away it is,
        
        double multiplier = 1.0;
        int forecastHumidity = forecast.getHumidity();
        
        while(forecastHumidity != OPTIMAL_HUMIDITY){
            
            if(forecastHumidity < OPTIMAL_HUMIDITY){
                score-=25*multiplier;
                forecastHumidity++;
                multiplier+=0.25;
            }else{
                score-=25*multiplier;
                forecastHumidity--;
                multiplier+=0.25;
            }
        }
        return score;
    }
}
