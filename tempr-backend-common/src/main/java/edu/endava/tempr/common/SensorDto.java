package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 4/4/17.
 */
public class SensorDto extends AbstractDto{
    private Long chipId;

    public Long getChipId() {
        return chipId;
    }

    public void setChipId(Long chipId) {
        this.chipId = chipId;
    }

    @Override
    public String toString() {
        return String.format("SensorDTO{chipId=%1$d}",
                chipId);
    }
}
