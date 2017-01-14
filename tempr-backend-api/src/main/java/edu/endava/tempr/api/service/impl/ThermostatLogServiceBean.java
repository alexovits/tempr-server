package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.ThermostatLogService;
import edu.endava.tempr.model.ThermostatLog;
import edu.endava.tempr.repository.ThermostatLogRepository;
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
        return thermostatLogRepository.findLatestLog().get(0);
    }

    @Override
    public List<ThermostatLog> getLastTenDays() {
        // Taking a sample date (Should be now()-10)
        String tenDaysBefore = "2017/01/04 16:42:08";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date startDate = new Date();
        try {
            // Converting it to a Date format
            startDate = dateFormat.parse(tenDaysBefore);
            // And back just to make sure eveything's fine
            String newDateString = dateFormat.format(startDate);
            LOG.info("Logs after date {}",newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return thermostatLogRepository.findAfterDate(startDate);
    }
}
