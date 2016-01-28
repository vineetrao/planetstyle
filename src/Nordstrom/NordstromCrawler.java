package Nordstrom;

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

import sdk.HTMLProduct;
import sdk.ProductXMLCreator;
import sdk.ProductXMLCreatorMultiThreaded;
import sdk.auto.DailyWebsiteCrawler;

public class NordstromCrawler implements DailyWebsiteCrawler {

	public static final String MERCHANT_ID = "nordstrom";
	public static final String MERCHANT_NAME = "nordstrom";
	public static final String MERCHANT_URL = "http://shop.nordstrom.com";

	public static void main(String[] args) throws Exception {

		List<Entry<String, Integer>> allCollUrls = new NordstromCrawler()
				.getAllCollectionUrlsForSite(MERCHANT_URL);

		HTMLProduct override = new HTMLProduct();

		for (Entry e : allCollUrls) {

			String collUrl = (String) e.getKey();
			int category = (int) e.getValue();

			System.out.println("Crawling : " + collUrl);

			ProductXMLCreatorMultiThreaded xmlCreator = new ProductXMLCreatorMultiThreaded(collUrl,
					new NordstromCrawler(), new NordstromParser("", category), baseDir,
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
			int maxBodySize = 2048000;//2MB (default is 1MB) 0 for unlimited size
			Document doc = Jsoup.connect(newUrl).maxBodySize(maxBodySize).timeout(10000)
					.userAgent("Mozilla").get();

			Elements divs = doc.select("div.fashion-results")
					.select("div.info");
			for (Element div : divs) {

				Elements urlFromPage = div.select("a");
				String finalUrl = urlFromPage.attr("href");

				if (!StringUtils.isBlank(finalUrl)) {
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
	
		//sale dresses cocktail
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-clothing-dresses?origin=leftnav#category=b60136820%7Cf6000379&type=category&currency=USD&defaultsize3=&size=&width=&color=&price=&brand=&stores=&instoreavailability=false&lastfilter=filtercategory_1&sizeFinderId=2&resultsmode=&segmentId=0&page=1", HTMLProduct.CATEGORY_CLOTHING));
		//sale dresses day
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-clothing-dresses?origin=leftnav#category=b60136820%7Cf6009171&type=category&currency=USD&defaultsize3=&size=&width=&color=&price=&brand=&stores=&instoreavailability=false&lastfilter=filtercategory_1&sizeFinderId=2&resultsmode=&segmentId=0&page=1&partial=1&pagesize=100&contextualsortcategoryid=0", HTMLProduct.CATEGORY_CLOTHING));
		//sale dresses formal
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-clothing-dresses?origin=leftnav#category=b60136820%7Cf6000380&type=category&currency=USD&defaultsize3=&size=&width=&color=&price=&brand=&stores=&instoreavailability=false&lastfilter=filtercategory_1&sizeFinderId=2&resultsmode=&segmentId=0&page=1&partial=1&pagesize=100&contextualsortcategoryid=0", HTMLProduct.CATEGORY_CLOTHING));
		//sale dresses little black dress
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-clothing-dresses?origin=leftnav#category=b60136820%7Cf60131682&type=category&currency=USD&defaultsize3=&size=&width=&color=&price=&brand=&stores=&instoreavailability=false&lastfilter=filtercategory_1&sizeFinderId=2&resultsmode=&segmentId=0&page=1&partial=1&pagesize=100&contextualsortcategoryid=0", HTMLProduct.CATEGORY_CLOTHING));
		//sale coats and jackets
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-outerwear-jackets?origin=leftnav", HTMLProduct.CATEGORY_CLOTHING));
		
		//sale shoes boots and booties
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-boots-and-booties?origin=leftnav", HTMLProduct.CATEGORY_SHOES));
		//sale shoes evening
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-evening-shoes?origin=leftnav", HTMLProduct.CATEGORY_SHOES));
		//sale shoes heels
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/womens-sale-heels?origin=leftnav", HTMLProduct.CATEGORY_SHOES));
		//sale shoes pumps
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-pumps?origin=leftnav", HTMLProduct.CATEGORY_SHOES));
		//sale shoes sandals
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-sandals?origin=leftnav", HTMLProduct.CATEGORY_SHOES));
		//sale shoes wedges
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-wedges?origin=leftnav", HTMLProduct.CATEGORY_SHOES));
		
		//sale handbags and wallets
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-wallets?origin=leftnav", HTMLProduct.CATEGORY_BAGS));
		
		//sale watches
		list.add(new SimpleEntry("http://shop.nordstrom.com/c/sale-womens-watches?origin=leftnav", HTMLProduct.CATEGORY_WATCHES));
		
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
