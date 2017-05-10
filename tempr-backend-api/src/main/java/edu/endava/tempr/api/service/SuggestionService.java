package edu.endava.tempr.api.service;

import edu.endava.tempr.api.service.impl.ai.ComputedSuggestion;

import java.util.List;

/**
 * Created by zsoltszabo on 5/10/17.
 */
public interface SuggestionService {
    Double getSuggestionTemperature(int day, int hour) throws Exception;
    List<ComputedSuggestion> getSuggestionTemperature(int day) throws Exception;
    void setShit(Long id) throws Exception;
}
