package edu.endava.tempr.repository;

import edu.endava.tempr.model.ThermostatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by zsoltszabo on 05/01/2017.
 */
public interface ThermostatLogRepository extends JpaRepository<ThermostatLog,Long>{
    @Query("FROM ThermostatLog ORDER BY logTimeStamp DESC")
    List<ThermostatLog> findLatestLog();

    @Query("FROM ThermostatLog WHERE logTimeStamp > :startTime")
    List<ThermostatLog> findAfterDate(@Param("startTime") Date startTime);
}
