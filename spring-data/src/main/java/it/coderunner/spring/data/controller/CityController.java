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

import it.coderunner.spring.data.model.City;
import it.coderunner.spring.data.service.CityService;

@Controller
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping("/get/city/{name}/{country}")
	public @ResponseBody ResponseEntity<String> getByNameAndCountry(@PathVariable String name,
			@PathVariable String country) {
		City city = cityService.findByNameAndCountryAllIgnoringCase(name, country);
		return city != null ? new ResponseEntity<String>("GET Response : " + city, HttpStatus.OK)
				: new ResponseEntity<String>("No city found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/save/city/{name}/{country}")
	public @ResponseBody ResponseEntity<String> saveCity(@PathVariable String name, @PathVariable String country) {
		City city = cityService.save(new City(name, country));
		return city != null ? new ResponseEntity<String>("GET Response : " + city, HttpStatus.OK)
				: new ResponseEntity<String>("Problem with saving", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/get/top10/{country}")
	public @ResponseBody ResponseEntity<String> getTop10(@PathVariable String country) {
		List<City> cities = cityService.findFirst10ByCountry(country);
		cities.stream().forEach(System.out::println);
		return cities != null && !cities.isEmpty()
				? new ResponseEntity<String>("GET Response : " + cities, HttpStatus.OK)
				: new ResponseEntity<String>("No city found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/get/all/{page}/{size}")
	public @ResponseBody Page<City> getTop10(@PathVariable Integer page, @PathVariable Integer size) {
		return cityService.findAll(new PageRequest(page, size));
	}

}
