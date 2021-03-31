package motoworld.project.repository;

import motoworld.project.model.entities.LogEntity;
import motoworld.project.model.entities.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {

    @Query("SELECT l.motorcycle FROM LogEntity AS l " +
            "group by l.motorcycle.id " +
            "order by COUNT(l.motorcycle.id) desc")
    List<Motorcycle> findMostPopular();
}
