package crawler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Nordstrom.NordstromCrawler;
import Nordstrom.NordstromParser;
import Saksfifthavenue.SFACrawler;
import Saksfifthavenue.SFAParser;
import bloomingdales.BDCrawler;
import bloomingdales.BDParser;
import lordandtaylor.LTCrawler;
import lordandtaylor.LTParser;
import macys.MacysCrawler;
import macys.MacysParser;
import sdk.HTMLProduct;
import sdk.ProductParser;
import sdk.ProductXMLCreatorMultiThreaded;
import sdk.auto.DailyWebsiteCrawler;

public class ProductsCrawler {
	

	public static String[] merchants = {"bloomingdales", "lordandtaylor", "macys", "nordstrom", "saksfifthavenue"};

	public static final Map<String, DailyWebsiteCrawler > crawlers = new HashMap<String, DailyWebsiteCrawler>(){{
        put("bloomingdales", new BDCrawler());
        put("lordandtaylor", new LTCrawler());
        put("macys",new MacysCrawler());
        put("nordstrom", new NordstromCrawler());
        put("saksfifthavenue", new SFACrawler());
	}};
	
	public static final Map<String, ProductParser > parsers = new HashMap<String, ProductParser>(){{
        put("bloomingdales", new BDParser());
        put("lordandtaylor", new LTParser());
        put("macys",new MacysParser());
        put("nordstrom", new NordstromParser());
        put("saksfifthavenue", new SFAParser());
	}};
	
	public ProductsCrawler() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws Exception {
		for (String merchant : crawlers.keySet()) 
		{
			DailyWebsiteCrawler crawler = crawlers.get(merchant);
			ProductParser parser = parsers.get(merchant);
			List<Entry<String, Integer>> allCollUrls = crawler
					.getAllCollectionUrlsForSite(crawler.getMerchantURL());
			HTMLProduct override = new HTMLProduct();
			for (Entry e : allCollUrls) {

				String collUrl = (String) e.getKey();
				int category = (int) e.getValue();
				System.out.println("Crawling : " + collUrl);

				ProductParser p = parser.getClass().newInstance();
				p.setCategory(category);
				ProductXMLCreatorMultiThreaded xmlCreator = new ProductXMLCreatorMultiThreaded(collUrl,
						crawler.getClass().newInstance(), p, DailyWebsiteCrawler.baseDir,
						crawler.getMerchantId(), crawler.getMerchantName(), crawler.getMerchantURL(), override);

				xmlCreator.generateXML();
			}
		}
	}
}
