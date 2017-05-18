package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 4/4/17.
 */
public class HeatingSourceDto extends AbstractDto{
    @Override
    public String toString() {
        return String.format("HeatingSourceDTO{id=%1$d}",
                getId());
    }
}
