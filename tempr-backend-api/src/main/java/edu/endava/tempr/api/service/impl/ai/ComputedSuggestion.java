package edu.endava.tempr.api.service.impl.ai;

/**
 * Created by zsoltszabo on 5/10/17.
 */
public class ComputedSuggestion {

    private int gaussWeightSum;
    private double temperature, tempSum;

    public ComputedSuggestion(){
        gaussWeightSum = 0;
        temperature = 0;
    }

    public void addLog(double temperature, int gaussWeight){
        this.tempSum += temperature;
        this.gaussWeightSum += gaussWeight;
        this.temperature = temperature/gaussWeightSum;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getGaussWeightSum() {
        return gaussWeightSum;
    }

    public double getTempSum() {
        return tempSum;
    }
}
