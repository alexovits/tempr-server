package edu.endava.tempr.api.service.impl.ai;

import com.google.common.collect.ImmutableList;
import edu.endava.tempr.api.exception.OutOfHistogramRangeException;
import edu.endava.tempr.api.exception.SensorLogNotFoundException;
import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.api.service.SuggestionService;
import edu.endava.tempr.model.SensorLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by zsoltszabo on 5/10/17.
 */
@Service
public class SuggestionServiceBean implements SuggestionService {

    // Calculated weights with Gaussian kernel function: wi = e^-(alpha*i/sigma^2)
    // alpha = 3
    // sigma = 2
    private static final ImmutableList<Double> GAUSSIAN_WEIGHT = ImmutableList.of(1.0, 0.47, 0.22, 0.11);
    private static final Logger LOG = LoggerFactory.getLogger(SuggestionServiceBean.class);
    private static final int WEEKS = 4;
    private static final int WEEK_DAYS = 7;
    private static final int DAY_SEGMENTS = 24;

    private List<List<ComputedSuggestion>> computedHistogram;

    @Autowired
    private SensorLogService sensorLogService;

    @Override
    public Double getSuggestionTemperature(int day, int segment, long heatingCircuitId) throws OutOfHistogramRangeException, SensorLogNotFoundException {
        if(day<0 || day>=WEEK_DAYS || segment<0 || segment>=DAY_SEGMENTS){
            throw new OutOfHistogramRangeException(String.format("Parameters out of range (0<day<%1$d, 0<segment<%2$d)",WEEK_DAYS,DAY_SEGMENTS));
        }
        computedHistogram = evaluateHistogram(heatingCircuitId);
        return computedHistogram.get(day).get(segment).getTemperature();
    }

    @Override
    public List<ComputedSuggestion> getSuggestionTemperature(int day, long heatingCircuitId) throws OutOfHistogramRangeException, SensorLogNotFoundException {
        if(day<0 || day>=WEEK_DAYS){
            throw new OutOfHistogramRangeException(String.format("Parameters out of range (0<day<%1$d, 0<segment<%2$d)",WEEK_DAYS,DAY_SEGMENTS));
        }
        computedHistogram = evaluateHistogram(heatingCircuitId);
        return computedHistogram.get(day);
    }


    private List<List<ComputedSuggestion>> evaluateHistogram(Long heatingCircuitId) throws SensorLogNotFoundException {
        List<List<ComputedSuggestion>> computedHistogram = initializeHistogram();
        // Fetch sensors from database
        LocalDateTime fromDate = LocalDateTime.now().minusWeeks(WEEKS);
        final long x = new Date().getTime();
        List<SensorLog> sensorLogs = sensorLogService.getLogsSince(heatingCircuitId, fromDate);
        LOG.info("Query took {} ms", new Date().getTime()-x);
        // Iterate and calculate the suggested temperatures
        int prevDayOfYear = sensorLogs.get(0).getLogTimeStamp().getDayOfYear();

        Iterator<Double> weightIterator = GAUSSIAN_WEIGHT.iterator();
        double weight = weightIterator.next();

        int dayAccumulator = 0;
        for(SensorLog sensorLog : sensorLogs){
            // If a stepped to another day
            int delta = prevDayOfYear - sensorLog.getLogTimeStamp().getDayOfYear();
            if(delta > 0){
                // May happen that a day was entirely skipped so instead of 1 step it takes DELTA
                dayAccumulator += delta;
            }
            // When a week of data has been processed switch to the next Gaussian weight
            // Mostly == 7 but in extreme cases it might skip more than a day thus leading to bigger difference
            if(dayAccumulator >= 7){
                dayAccumulator = 0;
                if(weightIterator.hasNext()) weight = weightIterator.next();
            }
            // Add new log to the respective weekday and segment (hour)
            int segmentIndex = sensorLog.getLogTimeStamp().getHour();
            int dayIndex = sensorLog.getLogTimeStamp().getDayOfWeek().getValue()-1;
            computedHistogram.get(dayIndex).get(segmentIndex).addLog(sensorLog.getTemperature(), weight);
            prevDayOfYear = sensorLog.getLogTimeStamp().getDayOfYear();
        }
        return computedHistogram;
    }

    public List<List<ComputedSuggestion>> initializeHistogram(){
        List<List<ComputedSuggestion>> computedHistogram = new ArrayList(WEEK_DAYS);

        IntStream.range(0, WEEK_DAYS).forEachOrdered(day -> {
            List<ComputedSuggestion> dayElement = new ArrayList<ComputedSuggestion>(DAY_SEGMENTS);
            IntStream.range(0, DAY_SEGMENTS).forEachOrdered(segment -> {
                dayElement.add(new ComputedSuggestion());
            });
            computedHistogram.add(dayElement);
        });

        return computedHistogram;
    }
}
