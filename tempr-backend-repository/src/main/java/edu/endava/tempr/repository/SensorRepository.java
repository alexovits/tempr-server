package edu.endava.tempr.repository;

import edu.endava.tempr.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zsoltszabo on 4/8/17.
 */
@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findByChipId(Long chipId);
}
