package edu.endava.tempr.api.service.impl.ai;

import edu.endava.tempr.api.service.SuggestionService;

import java.util.List;

/**
 * Created by zsoltszabo on 5/10/17.
 */
public class SuggestionServiceBean implements SuggestionService {

    private List<List<ComputedSuggestion>> computedHistogram;

    @Override
    public Double getSuggestionTemperature(int day, int hour) throws Exception {
        return null;
    }

    @Override
    public List<Double> getSuggestionTemperature(int day) throws Exception {
        return null;
    }

    private List<List<ComputedSuggestion>> evaluateHistogram(){
        return null;
    }
}
