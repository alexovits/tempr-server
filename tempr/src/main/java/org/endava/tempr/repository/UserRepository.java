package org.endava.tempr.repository;

import org.endava.tempr.model.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntry, Long>{
    List<UserEntry> findByuserId(long userId);

}
