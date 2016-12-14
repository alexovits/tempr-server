package edu.endava.tempr.repository;

import edu.endava.tempr.model.Thermostat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
public interface ThermostatRepository extends JpaRepository<Thermostat, Long> {
    Thermostat findByToken(String token);
}
