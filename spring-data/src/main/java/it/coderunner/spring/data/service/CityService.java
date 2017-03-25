package it.coderunner.spring.data.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.coderunner.spring.data.model.City;

public interface CityService extends Serializable{

	City findByNameAndCountryAllIgnoringCase(String name, String country);

	City save(City city);
	
	List<City> findFirst10ByCountry(String country);
	
	Page<City> findAll(Pageable pageable);

}
