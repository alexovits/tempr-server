package edu.endava.tempr.api.service;

import edu.endava.tempr.api.exception.OutOfHistogramRangeException;
import edu.endava.tempr.api.exception.SensorLogNotFoundException;
import edu.endava.tempr.api.service.impl.ai.ComputedSuggestion;

import java.util.List;

/**
 * Created by zsoltszabo on 5/10/17.
 */
public interface SuggestionService {
    Double getSuggestionTemperature(int day, int hour, long heatingCircuitId) throws OutOfHistogramRangeException, SensorLogNotFoundException;
    List<ComputedSuggestion> getSuggestionTemperature(int day, long heatingCircuitId) throws OutOfHistogramRangeException, SensorLogNotFoundException;
}
