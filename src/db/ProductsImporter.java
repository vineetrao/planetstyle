/**
 * 
 */
package db;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import db.Product;
import utils.HibernateUtil;

/**
 * @author VineetR
 *
 */

public class ProductsImporter {

	/**
	 * 
	 */
	public ProductsImporter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		ArrayList<Product> products = new ProductXMLReader().getAllProducts();
		int i = 0;
		for (Product product : products) {
			product.setColor("");
			session.save(product);
			if (i++ % 50 == 0) 
			{
				//flush a batch of inserts and release memory:
		        session.flush();
		        session.clear();
			}
		}

		session.getTransaction().commit();
		session.close();
		System.out.println("--------DONE---------");
	}

}
