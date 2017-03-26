package it.coderunner.spring.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.coderunner.spring.data.dao.AddressRepository;
import it.coderunner.spring.data.model.Address;

@Service
public class AddressServiceImpl implements AddressService{

	
	
	private static final long serialVersionUID = 4869311632471504461L;
	@Resource
	private AddressRepository addressRepository;

	@Override
	public Address findByStreetAndNumberAllIgnoringCase(String street, String number) {
		return addressRepository.findByStreetAndNumberAllIgnoringCase(street, number) ;
	}

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public List<Address> findFirst10ByStreet(String street) {
		return addressRepository.findFirst10ByStreet(street);
	}

	@Override
	public Page<Address> findAll(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

}
