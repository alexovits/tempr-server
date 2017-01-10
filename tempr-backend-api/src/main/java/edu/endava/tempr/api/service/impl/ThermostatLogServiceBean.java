package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.ThermostatLogService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.model.ThermostatLog;
import edu.endava.tempr.repository.ThermostatLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zsoltszabo on 05/01/2017.
 */
public class ThermostatLogServiceBean implements ThermostatLogService {

    @Autowired
    ThermostatLogRepository thermostatLogRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ThermostatLogService.class);

    @Override
    public ThermostatLog findOne(Long id) {
        LOG.info("Looking for thermostatLog with id: '{}'",id);
        return thermostatLogRepository.findOne(id);
    }

    @Override
    public ThermostatLog create(ThermostatLog thermostatLog) {
        return thermostatLogRepository.save(thermostatLog);
    }

    @Override
    public void delete(Long id) {
        LOG.info("Deleting thermostatLog with id: '{}'", id);
        thermostatLogRepository.delete(id);
    }

    @Override
    public ThermostatLog update(ThermostatLog thermostatLog) {
        ThermostatLog thermostatLogToUpdate = thermostatLogRepository.findOne(thermostatLog.getId());
        if(thermostatLogToUpdate == null){
            LOG.info("ThermostatLog with id: {} was not found",thermostatLog.getId());
            return null;
        }
        LOG.info("Updating thermostatLog with id: {}", thermostatLog.getId());
        return thermostatLogRepository.save(thermostatLog);
    }

    @Override
    public ThermostatLog getLatest() {
        return null;
    }
}
