package edu.endava.tempr.test;

import edu.endava.tempr.api.exception.SensorLogNotFoundException;
import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.api.service.impl.SensorLogServiceBean;
import edu.endava.tempr.model.SensorLog;
import edu.endava.tempr.repository.SensorLogRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltszabo on 5/19/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SensorLogServiceTest {

    private static Logger LOG = LoggerFactory.getLogger(SensorLogServiceTest.class);

    @Mock
    SensorLogRepository sensorLogRepository;

    SensorLogService victimSensorLogService;

    @Before
    public void setUp(){
        victimSensorLogService =  new SensorLogServiceBean(sensorLogRepository);
    }

    @Test
    public void testGetLatestLog() {
        Exception exception = null;
        SensorLog testSensorLog = new SensorLog();
        testSensorLog.setId(1L);
        SensorLog resultSensorLog = null;
        Mockito.when(sensorLogRepository.findFirstByHeatingCircuitIdOrderByLogTimeStampDesc(Mockito.any(Long.class))).thenReturn(testSensorLog);
        try {
            resultSensorLog = victimSensorLogService.getLatestLog(1L);
        } catch (SensorLogNotFoundException e) {
            exception = e;
        }
        Assert.assertNull(exception);
        Assert.assertEquals("Failure - expected attribute match", resultSensorLog.getId(), testSensorLog.getId());
    }

    @Test
    public void testGetLatestLogFail() {
        Exception exception = null;
        SensorLog resultSensorLog = null;
        Mockito.when(sensorLogRepository.findFirstByHeatingCircuitIdOrderByLogTimeStampDesc(Mockito.any(Long.class))).thenReturn(null);
        try {
            resultSensorLog = victimSensorLogService.getLatestLog(1L);
        } catch (SensorLogNotFoundException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        Assert.assertNull(resultSensorLog);
    }

    @Test
    public void testGetLatestTemperature(){
        Exception exception = null;
        SensorLog testSensorLog = new SensorLog();
        testSensorLog.setId(1L);
        testSensorLog.setTemperature(20);
        Integer resultTemperature = null;
        Mockito.when(sensorLogRepository.findFirstByHeatingCircuitIdOrderByLogTimeStampDesc(Mockito.any(Long.class))).thenReturn(testSensorLog);
        try {
            resultTemperature = victimSensorLogService.getLatestTemperature(1L);
        } catch (SensorLogNotFoundException e) {
            exception = e;
        }
        Assert.assertNull(exception);
        Assert.assertEquals("Failure - expected attribute match", resultTemperature, testSensorLog.getTemperature());
    }

    @Test
    public void testGetLatestTemperatureFail(){
        Exception exception = null;
        SensorLog testSensorLog = new SensorLog();
        testSensorLog.setId(1L);
        testSensorLog.setTemperature(20);
        Integer resultTemperature = null;
        Mockito.when(sensorLogRepository.findFirstByHeatingCircuitIdOrderByLogTimeStampDesc(Mockito.any(Long.class))).thenReturn(null);
        try {
            resultTemperature = victimSensorLogService.getLatestTemperature(1L);
        } catch (SensorLogNotFoundException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        Assert.assertNull(resultTemperature);
    }

    @Test
    public void testGetLogsSince(){
        Exception exception = null;
        List<SensorLog> testSensorLogList = new ArrayList<>();
        testSensorLogList.add(new SensorLog());
        testSensorLogList.add(new SensorLog());
        testSensorLogList.add(new SensorLog());
        testSensorLogList.add(new SensorLog());
        Mockito.when(sensorLogRepository.findByHeatingCircuitIdAndLogTimeStampGreaterThanOrderByLogTimeStampDesc(Mockito.any(Long.class),Mockito.any(LocalDateTime.class))).thenReturn(testSensorLogList);
        List<SensorLog> resultSensorLogList = null;
        try {
            resultSensorLogList = victimSensorLogService.getLogsSince(1L,LocalDateTime.now());
        } catch (SensorLogNotFoundException e) {
            exception = e;
        }

        Assert.assertNull(exception);
        Assert.assertEquals("Failure - expected attribute match", resultSensorLogList.size(), testSensorLogList.size());
    }

    @Test
    public void testGetLogsSinceFail(){
        Exception exception = null;
        List<SensorLog> testSensorLogList = new ArrayList<>();
        Mockito.when(sensorLogRepository.findByHeatingCircuitIdAndLogTimeStampGreaterThanOrderByLogTimeStampDesc(Mockito.any(Long.class),Mockito.any(LocalDateTime.class))).thenReturn(testSensorLogList);
        List<SensorLog> resultSensorLogList = null;
        try {
            resultSensorLogList = victimSensorLogService.getLogsSince(1L,LocalDateTime.now());
        } catch (SensorLogNotFoundException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        Assert.assertNull(resultSensorLogList);
    }

    @Test
    public void testLastWeeksLogs(){
        Exception exception = null;
        List<SensorLog> testSensorLogList = new ArrayList<>();
        testSensorLogList.add(new SensorLog());
        testSensorLogList.add(new SensorLog());
        testSensorLogList.add(new SensorLog());
        testSensorLogList.add(new SensorLog());
        Mockito.when(sensorLogRepository.findByHeatingCircuitIdAndLogTimeStampGreaterThanOrderByLogTimeStampDesc(Mockito.any(Long.class),Mockito.any(LocalDateTime.class))).thenReturn(testSensorLogList);
        List<SensorLog> resultSensorLogList = null;
        try {
            resultSensorLogList = victimSensorLogService.getLastWeeksLogs(1L);
        } catch (SensorLogNotFoundException e) {
            exception = e;
        }

        Assert.assertNull(exception);
        Assert.assertEquals("Failure - expected attribute match", resultSensorLogList.size(), testSensorLogList.size());
    }

    @Test
    public void testLastWeeksLogsFail(){
        Exception exception = null;
        List<SensorLog> testSensorLogList = new ArrayList<>();
        Mockito.when(sensorLogRepository.findByHeatingCircuitIdAndLogTimeStampGreaterThanOrderByLogTimeStampDesc(Mockito.any(Long.class),Mockito.any(LocalDateTime.class))).thenReturn(testSensorLogList);
        List<SensorLog> resultSensorLogList = null;
        try {
            resultSensorLogList = victimSensorLogService.getLastWeeksLogs(1L);
        } catch (SensorLogNotFoundException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        Assert.assertNull(resultSensorLogList);
    }

}
