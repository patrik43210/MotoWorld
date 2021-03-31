package motoworld.project.repository;

import motoworld.project.model.entities.UserBike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBikeRepository extends JpaRepository<UserBike,String> {
}
