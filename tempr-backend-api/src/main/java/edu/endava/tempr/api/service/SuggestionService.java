package edu.endava.tempr.api.service;

import java.util.List;

/**
 * Created by zsoltszabo on 5/10/17.
 */
public interface SuggestionService {
    Double getSuggestionTemperature(int day, int hour) throws Exception;
    List<Double> getSuggestionTemperature(int day) throws Exception;
}
