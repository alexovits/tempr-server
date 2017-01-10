package edu.endava.tempr.repository;

import edu.endava.tempr.model.ThermostatLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zsoltszabo on 05/01/2017.
 */
public interface ThermostatLogRepository extends JpaRepository<ThermostatLog,Long>{

}
