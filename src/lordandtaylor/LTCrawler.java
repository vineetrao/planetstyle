package lordandtaylor;

import java.util.ArrayList;
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

import macys.MacysCrawler;
import sdk.HTMLProduct;
import sdk.ProductXMLCreator;
import sdk.auto.DailyWebsiteCrawler;

public class LTCrawler implements DailyWebsiteCrawler {

	public static final String MERCHANT_ID = "lordandtaylor";
	public static final String MERCHANT_NAME = "lordandtaylor";
	public static final String MERCHANT_URL = "http://www.lordandtaylor.com";

	public static void main(String[] args) throws Exception {

		List<Entry<String, Integer>> allCollUrls = new LTCrawler()
				.getAllCollectionUrlsForSite(MERCHANT_URL);

		HTMLProduct override = new HTMLProduct();

		for (Entry<String, Integer> e  : allCollUrls) {

			String collUrl = (String) e.getKey();
			int category = (int) e.getValue();
			System.out.println("Crawling : " + collUrl);
			
			ProductXMLCreator xmlCreator = new ProductXMLCreator(collUrl,
					new LTCrawler(), new LTParser("", category), baseDir,
					MERCHANT_ID, MERCHANT_NAME, MERCHANT_URL, override);

			xmlCreator.generateXML();
		}
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

			Document doc = Jsoup.connect(newUrl).timeout(10000)
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
					}
					urlList.add(finalUrl);
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
		//list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/en/lord-and-taylor/search/handbags/best-selling-handbags", HTMLProduct.CATEGORY_BAGS));
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/en/lord-and-taylor/search/womens-apparel/womens-coats?sre=MHP_MOD3_L1_PROMO_WMNS", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/en/SearchDisplay?sType=SimpleSearch&catalogId=10102&facet=xf_ads_f8:Y&categoryId=170154&storeId=10151&facetLabel=CLEARANCE&sre=MHP_MOD4_L1_PROMO_WMNS", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/SearchDisplay?facet=ads_f6_ntk_cs%3AY&langId=-1&urlRequestType=Base&showResultsPage=true&categoryId=14174&sType=SimpleSearch&searchType=1000&top_category=13655&searchTermScope=&minPrice=&resultCatEntryType=&facetLabel=SALE&filterTerm=&metaData=&catalogId=10102&pageView=image&urlLangId=&searchTerm=&storeId=10151&beginIndex=0&maxPrice=&pageSize=&manufacturer=", HTMLProduct.CATEGORY_WATCHES));
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
