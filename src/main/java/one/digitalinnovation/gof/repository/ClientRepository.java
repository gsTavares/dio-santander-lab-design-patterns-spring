package one.digitalinnovation.gof.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.gof.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
