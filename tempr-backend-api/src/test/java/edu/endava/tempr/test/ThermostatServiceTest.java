package edu.endava.tempr.test;

import edu.endava.tempr.api.exception.ThermostatAlreadyConfiguredException;
import edu.endava.tempr.api.exception.ThermostatNotFoundException;
import edu.endava.tempr.api.service.HeatingCircuitService;
import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.api.service.impl.ThermostatServiceBean;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.repository.ThermostatRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

/**
 * Created by zsoltszabo on 5/19/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class ThermostatServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(ThermostatServiceTest.class);

    @Mock
    ThermostatRepository thermostatRepository;

    @Mock
    SensorLogService sensorLogService;

    @Mock
    UserService userService;

    @Mock
    HeatingCircuitService heatingCircuitService;

    ThermostatService victimThermostatService;

    @Before
    public void setUp() { victimThermostatService = new ThermostatServiceBean(thermostatRepository, sensorLogService, userService, heatingCircuitService);}

    @Test
    public void testConfigure(){
        Thermostat testThermostat = new Thermostat();
        Thermostat resultThermostat = null;
        Exception exception = null;
        testThermostat.setConfigured(new Short((short) 0));
        testThermostat.setToken("token");
        Mockito.when(thermostatRepository.findByToken(Mockito.anyString())).thenReturn(testThermostat);
        Mockito.when(thermostatRepository.save(Mockito.any(Thermostat.class))).then(returnsFirstArg());
        try {
            resultThermostat = victimThermostatService.configureThermostat("token");
        } catch (ThermostatNotFoundException | ThermostatAlreadyConfiguredException e) {
            exception = e;
        }

        Assert.assertNull(exception);
        Assert.assertEquals("Attributes should differ", resultThermostat.getConfigured(), new Short((short) 1));
    }

    @Test
    public void testConfigureFail(){
        Thermostat testThermostat = new Thermostat();
        Thermostat resultThermostat = null;
        Exception exception = null;
        testThermostat.setConfigured(new Short((short) 1));
        testThermostat.setToken("token");
        Mockito.when(thermostatRepository.findByToken(Mockito.anyString())).thenReturn(testThermostat);
        Mockito.when(thermostatRepository.save(Mockito.any(Thermostat.class))).then(returnsFirstArg());
        try {
            resultThermostat = victimThermostatService.configureThermostat("token");
        } catch (ThermostatNotFoundException | ThermostatAlreadyConfiguredException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        Assert.assertNull(resultThermostat);
    }

    @Test
    public void testUnConfigure(){
        Thermostat testThermostat = new Thermostat();
        Thermostat resultThermostat = null;
        Exception exception = null;
        testThermostat.setConfigured(new Short((short) 1));
        testThermostat.setToken("token");
        Mockito.when(thermostatRepository.findByToken(Mockito.anyString())).thenReturn(testThermostat);
        Mockito.when(thermostatRepository.save(Mockito.any(Thermostat.class))).then(returnsFirstArg());
        try {
            resultThermostat = victimThermostatService.unConfigureThermostat("token");
        } catch (ThermostatNotFoundException | ThermostatAlreadyConfiguredException e) {
            exception = e;
        }

        Assert.assertNull(exception);
        Assert.assertEquals("Attributes should differ", resultThermostat.getConfigured(), new Short((short) 0));
    }

    @Test
    public void testUnConfigureFail(){
        Thermostat testThermostat = new Thermostat();
        Thermostat resultThermostat = null;
        Exception exception = null;
        testThermostat.setConfigured(new Short((short) 0));
        testThermostat.setToken("token");
        Mockito.when(thermostatRepository.findByToken(Mockito.anyString())).thenReturn(testThermostat);
        Mockito.when(thermostatRepository.save(Mockito.any(Thermostat.class))).then(returnsFirstArg());
        try {
            resultThermostat = victimThermostatService.unConfigureThermostat("token");
        } catch (ThermostatNotFoundException | ThermostatAlreadyConfiguredException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        Assert.assertNull(resultThermostat);
    }
}
