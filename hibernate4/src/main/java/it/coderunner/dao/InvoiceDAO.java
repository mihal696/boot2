package it.coderunner.dao;

import java.util.List;

import it.coderunner.model.Invoice;

public interface InvoiceDAO {

	void save(Invoice p);

	List<Invoice> list();

}