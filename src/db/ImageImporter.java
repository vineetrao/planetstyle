package db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.HibernateUtil;

public class ImageImporter {

	public ImageImporter() {
		// TODO Auto-generated constructor stub
	}
	
	public static HashMap<String, String> getExistingImageUrls()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select url, image_url from product");
		List<Object[]> rows = query.list();
		System.out.println("Rows in DB: " + rows.size());
		for(Object[] row : rows){
			//System.out.println(row);
			String url = (String) row[0].toString();
			String imageUrl = (String) row[1].toString();
			map.put(url, imageUrl);
		}
		return map;
	}

}
