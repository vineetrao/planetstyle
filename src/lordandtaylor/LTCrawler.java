package lordandtaylor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Saksfifthavenue.SFACrawler;
import Saksfifthavenue.SFAParser;
import macys.MacysCrawler;
import sdk.HTMLProduct;
import sdk.ProductXMLCreator;
import sdk.ProductXMLCreatorMultiThreaded;
import sdk.auto.DailyWebsiteCrawler;

public class LTCrawler implements DailyWebsiteCrawler {

	public static final String MERCHANT_ID = "lordandtaylor";
	public static final String MERCHANT_NAME = "lordandtaylor";
	public static final String MERCHANT_URL = "http://www.lordandtaylor.com";
	private HashMap<String, String[]> brandAndNames = new HashMap<String, String[]>();

	public static void main(String[] args) throws Exception {

		List<Entry<String, Integer>> allCollUrls = new LTCrawler()
				.getAllCollectionUrlsForSite(MERCHANT_URL);

		HTMLProduct override = new HTMLProduct();

		for (Entry<String, Integer> e  : allCollUrls) {

			String collUrl = (String) e.getKey();
			int category = (int) e.getValue();
			System.out.println("Crawling : " + collUrl);
			
			ProductXMLCreatorMultiThreaded xmlCreator = new ProductXMLCreatorMultiThreaded(collUrl,
					new LTCrawler(), new LTParser("", category), baseDir,
					MERCHANT_ID, MERCHANT_NAME, MERCHANT_URL, override);
			
			xmlCreator.generateXML();
		}
	}
	
	public void setBrandAndName(String url, String brand, String name)
	{
		String[] s = {brand, name};
		brandAndNames.put(url, s);
	}
	
	public String getBrand(String url) 
	{
		return brandAndNames.get(url)[0];
	}

	public String getName(String url) 
	{
		return brandAndNames.get(url)[1];
	}

	@Override
	public List<String> getAllProductUrlsForCollection(String url)
			throws Exception {
		Set<String> urlList = new TreeSet<String>();

		String newUrl;
		for (int i = 1; i <= 1; i++) {
			if (i == 1) {
				newUrl = url;
			} else {
				newUrl = url + "?p=" + i;
			}

			int maxBodySize = 4096000;//4MB (default is 1MB) 0 for unlimited size
			Document doc = Jsoup.connect(newUrl).maxBodySize(maxBodySize).timeout(30000)
					.userAgent("Mozilla").get();
			Elements lis = doc.select("div#ProductsList").select("ul#totproductsList").select(
					"li");
			for (Element li : lis) {
				String script = li.select("script.catEntryDisplayUrlScript").html();
				String finalUrl = script.substring(script.indexOf("http"), script.indexOf("\")"));

				if (!StringUtils.isBlank(finalUrl)) 
				{
					if (!finalUrl.startsWith("http")) 
					{
						finalUrl = MERCHANT_URL.concat(finalUrl);
						urlList.add(finalUrl);
					}
					urlList.add(finalUrl);
					String brand = li.select("a.tit").text().toLowerCase().trim();
					String name = li.select("div.info").select("a").text().toLowerCase().trim();
					setBrandAndName(finalUrl, brand, name);
					//System.out.println("Brand and Name : " + brand + ", " + name);
				}
				System.out.println(finalUrl);
			}
		}

		System.out.println("Product Count : " + urlList.size());
		return new ArrayList<String>(urlList);
	}

	@Override
	public List<Entry<String, Integer>> getAllCollectionUrlsForSite(String url)
			throws Exception {
		
		List<Entry<String, Integer>> list = new ArrayList<>();
		
		//clearance dresses
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=170162&sType=SimpleSearch&searchType=&top_category=13659&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=170161&sType=SimpleSearch&searchType=&top_category=13659&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=434195&sType=SimpleSearch&searchType=&top_category=13659&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=170160&sType=SimpleSearch&searchType=&top_category=13659&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=170159&sType=SimpleSearch&searchType=&top_category=13659&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_CLOTHING));
		
		//sale dresses
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=ads_f6_ntk_cs%3AY&langId=-1&urlRequestType=Base&showResultsPage=true&categoryId=170154&sType=1000&searchType=&top_category=13659&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=SALE&filterTerm=&metaData=&catalogId=10102&pageView=image&urlLangId=-1&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_CLOTHING));

		//clearance coats
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=181709&sType=SimpleSearch&searchType=&top_category=13659&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_CLOTHING));
		//sale coats
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=ads_f6_ntk_cs%3AY&langId=-1&urlRequestType=Base&showResultsPage=true&categoryId=181709&sType=1000&searchType=&top_category=13659&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=SALE&filterTerm=&metaData=&catalogId=10102&pageView=image&urlLangId=-1&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_CLOTHING));

		//clearance jackets and blazers
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=451173&sType=SimpleSearch&searchType=&top_category=13659&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_CLOTHING));

		//clearance shoes pumps
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=14443&sType=SimpleSearch&searchType=&top_category=13658&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_SHOES));
		//sale shoes pumps
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=ads_f6_ntk_cs%3AY&langId=-1&urlRequestType=Base&showResultsPage=true&categoryId=14443&sType=1000&searchType=&top_category=13658&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=SALE&filterTerm=&metaData=&catalogId=10102&pageView=image&urlLangId=-1&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_SHOES));
		
		//sale shoes evening
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=ads_f6_ntk_cs%3AY&langId=-1&urlRequestType=Base&showResultsPage=true&categoryId=54154&sType=1000&searchType=&top_category=13658&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=SALE&filterTerm=&metaData=&catalogId=10102&pageView=image&urlLangId=-1&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_SHOES));

		//clearance handbags
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=13653&sType=SimpleSearch&searchType=&top_category=13653&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_BAGS));

		//sale handbags
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=ads_f6_ntk_cs%3AY&langId=-1&urlRequestType=Base&showResultsPage=true&categoryId=13653&sType=1000&searchType=&top_category=13653&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=SALE&filterTerm=&metaData=&catalogId=10102&pageView=image&urlLangId=-1&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_BAGS));

		//clearance watches
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=xf_ads_f8%3AY+&langId=&urlRequestType=Base&showResultsPage=true&categoryId=14174&sType=SimpleSearch&searchType=&top_category=13655&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=CLEARANCE&filterTerm=&metaData=&catalogId=10102&pageView=&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_WATCHES));
		//sale watches
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=ads_f6_ntk_cs%3AY&langId=-1&urlRequestType=Base&showResultsPage=true&categoryId=14174&sType=1000&searchType=&top_category=13655&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=SALE&filterTerm=&metaData=&catalogId=10102&pageView=image&urlLangId=-1&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_WATCHES));

		return list;
	}

	@Override
	public String getMerchantId() {
		return MERCHANT_ID;
	}

	@Override
	public String getMerchantName() {
		return MERCHANT_NAME;
	}

	@Override
	public String getMerchantURL() {
		return MERCHANT_URL;
	}

}
