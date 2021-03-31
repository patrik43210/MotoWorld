package motoworld.project.repository;

import motoworld.project.model.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place,String> {

    Optional<Place> findByPostcode(String postcode);
    Optional<Place> findByLocation(String location);
}
