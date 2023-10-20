package one.digitalinnovation.gof.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.gof.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

}
