package it.coderunner.spring.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import it.coderunner.spring.data.model.Address;
import it.coderunner.spring.data.service.AddressService;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("/get/address/{street}/{number}")
	public @ResponseBody ResponseEntity<String> getByNameAndCountry(@PathVariable String street,
			@PathVariable String number) {
		Address address = addressService.findByStreetAndNumberAllIgnoringCase(street, number);
		return address != null ? new ResponseEntity<String>("GET Response : " + address, HttpStatus.OK)
				: new ResponseEntity<String>("No address found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/save/address/{street}/{number}")
	public @ResponseBody ResponseEntity<String> saveAddress(@PathVariable String street, @PathVariable String number) {
		Address address = addressService.save(new Address(street, number));
		return address != null ? new ResponseEntity<String>("GET Response : " + address, HttpStatus.OK)
				: new ResponseEntity<String>("Problem with saving", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/get/top10street/{street}")
	public @ResponseBody ResponseEntity<String> getTop10(@PathVariable String street) {
		List<Address> addresses = addressService.findFirst10ByStreet(street);
		addresses.stream().forEach(System.out::println);
		return addresses != null && !addresses.isEmpty()
				? new ResponseEntity<String>("GET Response : " + addresses, HttpStatus.OK)
				: new ResponseEntity<String>("No address found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/get/alladdress/{page2}/{size2}")
	public @ResponseBody Page<Address> getTop10(@PathVariable Integer page2, @PathVariable Integer size2) {
		return addressService.findAll(new PageRequest(page2, size2));
	}

}