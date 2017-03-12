package it.coderunner.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.coderunner.dao.InvoiceDAO;
import it.coderunner.model.Invoice;

@Repository
public class InvoiceDAOImpl implements InvoiceDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Invoice p) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> list() {
		Session session = this.sessionFactory.openSession();
		List<Invoice> invoiceList = session.createQuery("from Invoice").list();
		session.close();
		return invoiceList;
	}

}