package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.ThermostatLogService;
import edu.endava.tempr.model.ThermostatLog;
import edu.endava.tempr.repository.ThermostatLogRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zsoltszabo on 05/01/2017.
 */
@Service
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
        thermostatLog.setLogTimeStamp(new DateTime());
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
    public ThermostatLog getLatest(String token) {
        return thermostatLogRepository.findFirstByTokenOrderByLogTimeStampDesc(token);
    }

    @Override
    public List<ThermostatLog> getLastTenDays(String token) {
        // Fetch logs from for the last ten days
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
        DateTime afterDate = new DateTime().minusDays(10);
        LOG.info("Logs after date {}",dtf.print(afterDate));
        return thermostatLogRepository.findByTokenAndLogTimeStampGreaterThanOrderByLogTimeStampDesc(token, afterDate);
    }
}
