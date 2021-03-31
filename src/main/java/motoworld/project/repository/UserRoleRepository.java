package motoworld.project.repository;

import motoworld.project.model.entities.UserRole;
import motoworld.project.model.entities.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {

    Optional<UserRole> findByRole(RoleEnum role);
}
