package it.coderunner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.coderunner.dao.InvoiceDAO;
import it.coderunner.dao.PersonDAO;
import it.coderunner.model.Invoice;
import it.coderunner.model.Person;

public class SpringHibernateMain {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		PersonDAO personDAO = context.getBean(PersonDAO.class);
		InvoiceDAO invoiceDAO = context.getBean(InvoiceDAO.class);
		Person person = new Person();
		Invoice invoice = new Invoice();
		person.setName("Michał");
		person.setCountry("Poland");
		invoice.setInvoiceNumber("283219");
		invoice.setProvider("PayPa21l");
		invoice.setSum(29564);

		personDAO.save(person);
		invoiceDAO.save(invoice);
		personDAO.update(person);
	
		System.out.println("Person::" + person);
		System.out.println("Invoice::" + invoice);

		//personDAO.list().forEach(System.out::println);
		invoiceDAO.list().forEach(System.out::println);

		context.close();

	}
}