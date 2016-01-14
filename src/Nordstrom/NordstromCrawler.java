package Nordstrom;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sdk.HTMLProduct;
import sdk.ProductXMLCreator;
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

			ProductXMLCreator xmlCreator = new ProductXMLCreator(collUrl,
					new NordstromCrawler(), new NordstromParser(""), baseDir,
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
		//list.add("C:\\Code\\workspace\\crawler\\src\\macys\\macys_mc.html");
	//	list.add("http://shop.nordstrom.com/c/womens-handbags?origin=leftnav");
	//	list.add("http://shop.nordstrom.com/c/sale-designer");
	//	list.add("http://shop.nordstrom.com/c/all-mens-sale?origin=leftnav");
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
