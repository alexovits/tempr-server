package edu.endava.tempr.repository;

import edu.endava.tempr.model.Thermostat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
@Repository
public interface ThermostatRepository extends JpaRepository<Thermostat, Long> {
    Thermostat findByToken(String token);
    Thermostat findByUserId(Long userId);
}
