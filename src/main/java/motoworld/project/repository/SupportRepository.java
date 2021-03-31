package motoworld.project.repository;

import motoworld.project.model.entities.SupportMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository<SupportMessages,String> {
}
