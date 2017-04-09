package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.SimulatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zsoltszabo on 4/9/17.
 */
@Service
public class SimulatorServiceBean implements SimulatorService {

    private static final Logger LOG = LoggerFactory.getLogger(SimulatorService.class);
    Map<Long,SimulatedHeatingCircuit> heatingCircuitList;

    public SimulatorServiceBean(){
        heatingCircuitList = new HashMap<>();
        loadMapWithStaticValues();
    }

    public void loadMapWithStaticValues(){
        addHeatingCircuit(1233);
        addHeatingCircuit(4354);
        addHeatingCircuit(3645);
        addHeatingCircuit(4233);
    }

    @Override
    public void addHeatingCircuit(long chipId) {
        if(!heatingCircuitList.containsKey(chipId)) {
            heatingCircuitList.put(chipId, new SimulatedHeatingCircuit());
            LOG.info("Inserting chip {}", chipId);
        }
    }

    @Override
    public void logTemperature(int temperature, long chipId) {
        if(!heatingCircuitList.containsKey(chipId)) {
            heatingCircuitList.get(chipId).setTemperature(temperature);
            LOG.info("Set temp for chip {}",chipId);
        }
    }

    @Override
    public void setSuggestedTemperature(int temperature, long chipId) {
        if(!heatingCircuitList.containsKey(chipId)) {
            heatingCircuitList.get(chipId).setSuggestedTemperature(temperature);
            LOG.info("Set suggested temp for chip {}",chipId);
        }
    }

    @Override
    public void setDesiredTemperature(int temperature, long chipId) {
        if(!heatingCircuitList.containsKey(chipId)) {
            heatingCircuitList.get(chipId).setDesiredTemperature(temperature);
            LOG.info("Set desired temp for chip {}",chipId);
        }
    }

    public class SimulatedHeatingCircuit{
        private int temperature, suggestedTemperature, desiredTemperature;

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public int getSuggestedTemperature() {
            return suggestedTemperature;
        }

        public void setSuggestedTemperature(int suggestedTemperature) {
            this.suggestedTemperature = suggestedTemperature;
        }

        public int getDesiredTemperature() {
            return desiredTemperature;
        }

        public void setDesiredTemperature(int desiredTemperature) {
            this.desiredTemperature = desiredTemperature;
        }
    }
}
