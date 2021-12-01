package com.egg.patitas.red.repository;

import com.egg.patitas.red.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone,Integer> {

@Modifying
@Query("UPDATE Zones z SET z.enabled=true WHERE z.id=id")
    void enabled(@Param("id") Integer id);


@Modifying
@Query("UPDATE Zones z SET z.enabled=true WHERE z.id=id")
void disable(@Param("id") Integer id);
}
