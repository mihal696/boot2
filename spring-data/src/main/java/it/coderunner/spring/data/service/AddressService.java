package it.coderunner.spring.data.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.coderunner.spring.data.model.Address;

public interface AddressService extends Serializable {

	Address findByStreetAndNumberAllIgnoringCase(String street, String number);

	Address save(Address address);

	List<Address> findFirst10ByStreet(String street);

	List<Address> findByNumberGreaterThan(String number);

	List<Address> findByStreetStartingWith(String street);

	List<Address> findByStreetNotLike(String street);

	Page<Address> findAll(Pageable pageable);

}
