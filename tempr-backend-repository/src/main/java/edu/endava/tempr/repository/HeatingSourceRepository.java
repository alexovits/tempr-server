package edu.endava.tempr.repository;

import edu.endava.tempr.model.HeatingSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zsoltszabo on 4/8/17.
 */
@Repository
public interface HeatingSourceRepository extends JpaRepository<HeatingSource, Long> {
}
