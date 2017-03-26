package it.coderunner.spring.data.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import it.coderunner.spring.data.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{
	
	Page<Address> findAll(Pageable pageable);

	Page<Address> findByStreetContainingAndNumberContainingAllIgnoringCase(String street,
			String number, Pageable pageable);

	Address findByStreetAndNumberAllIgnoringCase(String street, String number);
	
	List<Address> findFirst10ByStreet(String street);
	
	@SuppressWarnings("unchecked")
	Address save(Address address);

}
