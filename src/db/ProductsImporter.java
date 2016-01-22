/**
 * 
 */
package db;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hibernate.SQLQuery;
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
		
		//singleThreadedImport();
		multiThreadedImport();
//		ArrayList<Product> products = new ProductXMLReader().getAllProducts();
//		System.out.println("Total Products Inserted: " + products.size());
	}
	
	public static void singleThreadedImport() 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		ArrayList<Product> products = new ProductXMLReader().getAllProducts();
		int i = 0;
		for (Product product : products) {
			product.setColor("");
			System.out.println(product.getUrl());
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
		System.out.println("--------Inserted Products:---------" + products.size());

	}
	
	private static int totalProducts = 0;
	public static void multiThreadedImport() 
	{
		File dataDir = new File(ProductXMLReader.baseDir);
		File[] retailerDirs = dataDir.listFiles();
		
		int threads = Runtime.getRuntime().availableProcessors();
		System.out.println("NUM THREADS: " + threads);
		ExecutorService service = Executors.newFixedThreadPool(threads);
		for (final File retailerDir : retailerDirs) {
	        Runnable runnable = new Runnable() {
	            public void run() {
	            	try 
	            	{
	            		ArrayList<Product> products = ProductXMLReader.getProductsForRetailer(retailerDir);
	            		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	            		Session session = sessionFactory.openSession();
	            		session.beginTransaction();

	            		int i = 0;
	            		for (Product product : products) {
	            			product.setColor("");
	            			session.saveOrUpdate(product);
	            			if (i++ % 50 == 0) 
	            			{
	            				//flush a batch of inserts and release memory:
	            		        session.flush();
	            		        session.clear();
	            			}
	            		}

	            		session.getTransaction().commit();
	            		session.close();
	            		System.out.println("----Inserted " + i + " Products----From file ----" + retailerDir.getAbsolutePath());
	            		totalProducts += products.size();
	            	}
	            	catch(Exception e){
	    				e.printStackTrace();
	    				System.out.println("####### Failed to insert : " + retailerDir.getAbsolutePath() + " : " + e.getMessage() + " ######");
	    			}
	            }
	        };
	        service.submit(runnable);
	    }
		service.shutdown();
	}
	
	public static HashSet<String> getExistingProductUrls()
	{
		HashSet<String> set = new HashSet<String>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select url from product");
		List<String> rows = query.list();
		System.out.println("Rows in DB: " + rows.size());
		for(String row : rows){
			//System.out.println(row);
			set.add(row);
		}
		return set;
	}

}
