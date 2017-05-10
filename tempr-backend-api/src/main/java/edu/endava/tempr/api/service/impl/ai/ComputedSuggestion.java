package edu.endava.tempr.api.service.impl.ai;

/**
 * Created by zsoltszabo on 5/10/17.
 */
public class ComputedSuggestion {

    private double temperature, tempSum, gaussWeightSum;

    public ComputedSuggestion(){
        gaussWeightSum = 0;
        temperature = 0;
    }

    public void addLog(double temperature, double gaussWeight){
        this.tempSum += gaussWeight*temperature;
        this.gaussWeightSum += gaussWeight;
        this.temperature = tempSum/gaussWeightSum;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getGaussWeightSum() {
        return gaussWeightSum;
    }

    public double getTempSum() {
        return tempSum;
    }

    @Override
    public String toString() {
        return String.format("ComputedSuggestion{temp=%1$.2f, tempSum=%2$.2f, gaussWeight=%3$.2f}",temperature,tempSum,gaussWeightSum);
    }
}
