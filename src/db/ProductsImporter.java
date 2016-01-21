/**
 * 
 */
package db;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	
	private static int totalProducts = 0;
	public static void multiThreadedImport() 
	{
		ArrayList<File> files = new ArrayList<File>();
		File dataDir = new File(ProductXMLReader.baseDir);
		File[] retailerDirs = dataDir.listFiles();
		for (File retailerDir : retailerDirs) {
			File[] productFiles = retailerDir.listFiles();
			for (File f : productFiles) {
				files.add(f);
			}
		}
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		int threads = Runtime.getRuntime().availableProcessors();
		System.out.println("NUM THREADS: " + threads);
		ExecutorService service = Executors.newFixedThreadPool(threads);
		for (final File f: files) {
	        Runnable runnable = new Runnable() {
	            public void run() {
	            	try 
	            	{
	            		ArrayList<Product> products = ProductXMLReader.getProductsFromXML(f);
	            		Session session = sessionFactory.openSession();
	            		session.beginTransaction();

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
	            		System.out.println("----Inserted " + i + " Products----From file ----" + f.getAbsolutePath());
	            		totalProducts += products.size();
	            	}
	            	catch(Exception e){
	    				//e.printStackTrace();
	    				System.out.println("####### Failed to insert: " + f.getAbsolutePath() + " : " + e.getMessage() + " ######");
	    			}
	            }
	        };
	        service.submit(runnable);
	    }
		service.shutdown();
	}

}
