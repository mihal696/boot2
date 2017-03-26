package it.coderunner.spring.data.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import it.coderunner.spring.data.model.City;

public interface CityRepository extends CrudRepository<City, Long>{
	
	Page<City> findAll(Pageable pageable);

	Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
			String country, Pageable pageable);

	City findByNameAndCountryAllIgnoringCase(String name, String country);
	
	List<City> findFirst10ByCountry(String country);
	
	@SuppressWarnings("unchecked")
	City save(City city);

}
