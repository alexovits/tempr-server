package edu.endava.tempr.repository;

import edu.endava.tempr.model.SensorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zsoltszabo on 4/8/17.
 */
@Repository
public interface SensorLogRepository extends JpaRepository<SensorLog, Long> {
    SensorLog findFirstByHeatingCircuitOrderByLogTimeStampDesc(Long heatingCricuitId);
   // List<SensorLog> findByHeatingCircuitAndSensorLogTimeStampGreaterThanOrderByLogTimeStampDesc(Long heatingCircuitId, LocalDateTime fromDate);
}
