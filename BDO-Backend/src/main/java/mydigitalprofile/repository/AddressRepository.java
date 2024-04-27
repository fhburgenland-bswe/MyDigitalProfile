package mydigitalprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
