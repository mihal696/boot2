package it.coderunner.dao;

import java.util.List;

import it.coderunner.model.Person;

public interface PersonDAO {

	void save(Person p);
	
	List<Person> list();
	
}
