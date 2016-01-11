package Saksfifthavenue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sdk.HTMLProduct;
import sdk.ProductXMLCreator;
import sdk.auto.DailyWebsiteCrawler;

public class SFACrawler implements DailyWebsiteCrawler {

	public static final String MERCHANT_ID = "saksfifthavenue";
	public static final String MERCHANT_NAME = "saksfifthavenue";
	public static final String MERCHANT_URL = "http://www.saksfifthavenue.com/";

	public static void main(String[] args) throws Exception {

		List<String> allCollUrls = new SFACrawler()
				.getAllCollectionUrlsForSite(MERCHANT_URL);

		HTMLProduct override = new HTMLProduct();

		for (String collUrl : allCollUrls) {

			System.out.println("Crawling : " + collUrl);

			ProductXMLCreator xmlCreator = new ProductXMLCreator(collUrl,
					new SFACrawler(), new SFAParser(""), baseDir,
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
			Elements divs = doc.select("div#product-container")
					.select("div.product-text");
			for (Element div : divs) {

				Elements urlFromPage = div.select("a");
				String finalUrl = urlFromPage.attr("href");

				if (!StringUtils.isBlank(finalUrl)) {
					if (!finalUrl.startsWith("http")) 
					{
						finalUrl = MERCHANT_URL.concat(finalUrl);
						System.out.println("URL: " + finalUrl);
					}
					urlList.add(finalUrl);
				}
				System.out.println(finalUrl);
			}
		}
		return new ArrayList<String>(urlList);
	}

	@Override
	public List<String> getAllCollectionUrlsForSite(String url)
			throws Exception {
		List<String> list = new ArrayList<String>();
		//list.add("C:\\Code\\workspace\\crawler\\src\\macys\\macys_mc.html");
		list.add("http://www.saksfifthavenue.com/Handbags/shop/_/N-52jzot/Ne-6lvnb5?FOLDER%3C%3Efolder_id=2534374306622829&Ns=P_bestsellers_units%7C1%7C%7CP_brandname%7C%7CP_product_code");
		list.add("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?N=306418048+1553&sre=salemainm1s1l2");
		list.add("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?N=306623830+1553");
		list.add("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?N=306622828+1553&sre=salemainm1s1l5");
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
