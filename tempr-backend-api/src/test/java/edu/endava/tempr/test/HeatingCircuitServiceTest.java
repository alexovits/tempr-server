package edu.endava.tempr.test;

import ch.qos.logback.core.net.AbstractSSLSocketAppender;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import edu.endava.tempr.api.exception.HeatingCircuitNotFoundException;
import edu.endava.tempr.api.service.HeatingCircuitService;
import edu.endava.tempr.api.service.SensorService;
import edu.endava.tempr.api.service.SuggestionService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.impl.HeatingCircuitServiceBean;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import edu.endava.tempr.repository.HeatingCircuitRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by zsoltszabo on 5/18/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class HeatingCircuitServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private HeatingCircuitRepository heatingCircuitRepository;

    @Mock
    private ThermostatService thermostatService;

    @Mock
    private SensorService sensorService;

    @Mock
    private SuggestionService suggestionService;

    private HeatingCircuitService victimHeatingCircuitService;

    @Before
    public void setUp() { victimHeatingCircuitService = new HeatingCircuitServiceBean(heatingCircuitRepository, thermostatService, sensorService, suggestionService); }

    @Test
    public void trueSensorBelongTest(){
        HeatingCircuit testHeatingCircuit = Mockito.mock(HeatingCircuit.class);
        Thermostat testThermostat = Mockito.mock(Thermostat.class);
        User testUser = Mockito.mock(User.class);
        String testUserName = "ellotesso";
        Mockito.when(testHeatingCircuit.getThermostat()).thenReturn(testThermostat);
        Mockito.when(testThermostat.getUser()).thenReturn(testUser);
        Mockito.when(testUser.getUsername()).thenReturn(testUserName);
        String userName = "limpbiscuit4eva";
        boolean result = victimHeatingCircuitService.sensorBelongsToUser(testUserName, testHeatingCircuit);
        Assert.assertEquals(result, true);
    }

    @Test
    public void falseSensorBelongTest(){
        HeatingCircuit testHeatingCircuit = Mockito.mock(HeatingCircuit.class);
        Thermostat testThermostat = Mockito.mock(Thermostat.class);
        User testUser = Mockito.mock(User.class);
        Mockito.when(testHeatingCircuit.getThermostat()).thenReturn(testThermostat);
        Mockito.when(testThermostat.getUser()).thenReturn(testUser);
        Mockito.when(testUser.getUsername()).thenReturn("ellotesso");
        boolean result = victimHeatingCircuitService.sensorBelongsToUser("user", testHeatingCircuit);
        Assert.assertEquals(result, false);
    }

    @Test
    public void testFindOneWithValidChipId(){
        HeatingCircuit heatingCircuit = new HeatingCircuit();
        Exception exception = null;
        HeatingCircuit returnHeatingCircuit = null;
        heatingCircuit.setId(1L);
        heatingCircuit.setName("Room-1");
        Mockito.when(heatingCircuitRepository.findBySensorChipId(Mockito.any(Long.class))).thenReturn(heatingCircuit);
        try {
            returnHeatingCircuit = victimHeatingCircuitService.findByChipId(123456);
        } catch (HeatingCircuitNotFoundException e) {
            exception = e;
        }

        Assert.assertNull(exception);
        Assert.assertEquals(returnHeatingCircuit.getId(), heatingCircuit.getId());
        Assert.assertEquals(returnHeatingCircuit.getName(), heatingCircuit.getName());
    }

    @Test
    public void testFindOneWithInvalidChipId(){
        HeatingCircuit heatingCircuit = new HeatingCircuit();
        Exception exception = null;
        HeatingCircuit returnHeatingCircuit = null;
        heatingCircuit.setId(1L);
        heatingCircuit.setName("Room-1");
        Mockito.when(heatingCircuitRepository.findBySensorChipId(1L)).thenReturn(null);
        try {
            returnHeatingCircuit = victimHeatingCircuitService.findByChipId(1L);
        } catch (HeatingCircuitNotFoundException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        Assert.assertNull(returnHeatingCircuit);
    }

    @Test
    public void testFindOneWithInvalidHeatingCircuitId(){
        HeatingCircuit heatingCircuit = new HeatingCircuit();
        Exception exception = null;
        HeatingCircuit returnHeatingCircuit = null;
        heatingCircuit.setId(1L);
        heatingCircuit.setName("Room-1");
        Mockito.when(heatingCircuitRepository.findOne(1L)).thenReturn(null);
        try {
            returnHeatingCircuit = victimHeatingCircuitService.findOne(1L);
        } catch (HeatingCircuitNotFoundException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        Assert.assertNull(returnHeatingCircuit);
    }

    @Test
    public void testFindOneWithValidHeatingCircuitId(){
        HeatingCircuit heatingCircuit = new HeatingCircuit();
        Exception exception = null;
        HeatingCircuit returnHeatingCircuit = null;
        heatingCircuit.setId(1L);
        heatingCircuit.setName("Room-1");
        Mockito.when(heatingCircuitRepository.findOne(1L)).thenReturn(heatingCircuit);
        try {
            returnHeatingCircuit = victimHeatingCircuitService.findOne(1L);
        } catch (HeatingCircuitNotFoundException e) {
            exception = e;
        }

        Assert.assertNull(exception);
        Assert.assertEquals(returnHeatingCircuit.getId(), heatingCircuit.getId());
        Assert.assertEquals(returnHeatingCircuit.getName(), heatingCircuit.getName());
    }

}
