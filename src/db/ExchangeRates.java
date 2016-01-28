package db;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import utils.HibernateUtil;

public class ExchangeRates {

	public static String rates_url = "http://www1.macys.com/shop/international/priceData.js";
	
	public ExchangeRates() {
		// TODO Auto-generated constructor stub
	}

	public static void updateRate() throws Exception
	{
		
		URL url = new URL( rates_url );
		InputStreamReader reader = new InputStreamReader( url.openStream() );
		
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
		ExchangeRates e = new ExchangeRates();
		engine.getBindings(ScriptContext.GLOBAL_SCOPE).put("MACYS", e.new MACYS());
		engine.eval( reader );
		
		MACYS m = (MACYS) engine.get("MACYS");
        
        Map indiaMap = (Map) m.IntlShipping.currencyMap.get("INR");
        if (indiaMap != null) 
        {
        	Double rate = (Double) indiaMap.get("exchangeRate");
        	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		session.beginTransaction();
    		SQLQuery query = session.createSQLQuery("update ExchangeRates set rate =:rate where country_code = 'INR'");
    		query.setDouble("rate", rate);
    		int modifications = query.executeUpdate();
    		session.getTransaction().commit();
    		session.close();
    		System.out.println("Updated Rate to : " + rate);
        }
	}
	
	public class MACYS
	{
		public INTLSHIPPING IntlShipping = new INTLSHIPPING();
		public MACYS(){}
		public void namespace(String ns) 
		{
			System.out.println();
		}
		
	}
	
	public class INTLSHIPPING 
	{
		public Map<String, Map<String, String>> countryMap = new HashMap<String, Map<String, String>>();
		public Map<String, Map<String, String>> currencyMap = new HashMap<String, Map<String, String>>();
		public INTLSHIPPING() {}
		
	}
}
