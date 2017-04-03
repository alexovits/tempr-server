package edu.endava.tempr.repository;

import edu.endava.tempr.model.ThermostatLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zsoltszabo on 05/01/2017.
 */
public interface ThermostatLogRepository extends JpaRepository<ThermostatLog,Long>{
    ThermostatLog findFirstByTokenOrderByLogTimeStampDesc(String token);
    List<ThermostatLog> findByTokenAndLogTimeStampGreaterThanOrderByLogTimeStampDesc(String token, LocalDateTime fromDate);
}
