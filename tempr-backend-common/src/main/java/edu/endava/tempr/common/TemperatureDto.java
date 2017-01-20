package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 20/01/2017.
 */
public class TemperatureDto extends AbstractDto {
    private int temp;

    public TemperatureDto(){
        //EMPTY
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
