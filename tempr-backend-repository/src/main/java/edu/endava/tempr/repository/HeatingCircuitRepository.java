package edu.endava.tempr.repository;

import edu.endava.tempr.model.HeatingCircuit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zsoltszabo on 4/8/17.
 */
@Repository
public interface HeatingCircuitRepository extends JpaRepository<HeatingCircuit, Long> {
}
