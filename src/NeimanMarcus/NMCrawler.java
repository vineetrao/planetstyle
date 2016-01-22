package NeimanMarcus;

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
import sdk.auto.DailyWebsiteCrawler;

public class NMCrawler implements DailyWebsiteCrawler {

	public static final String MERCHANT_ID = "neimanmarcus";
	public static final String MERCHANT_NAME = "neimanmarcus";
	public static final String MERCHANT_URL = "http://www.neimanmarcus.com";

	public static void main(String[] args) throws Exception {

		List<Entry<String, Integer>> allCollUrls = new NMCrawler()
				.getAllCollectionUrlsForSite(MERCHANT_URL);

		HTMLProduct override = new HTMLProduct();

		for (Entry e : allCollUrls) {

			String collUrl = (String) e.getKey();
			int category = (int) e.getValue();

			System.out.println("Crawling : " + collUrl);
			new NMCrawler().getAllProductUrlsForCollection(collUrl).size();

			ProductXMLCreator xmlCreator = new ProductXMLCreator(collUrl,
					new NMCrawler(), new NMParser("", category), baseDir,
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
					.userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:26.0) Gecko/20100101 Firefox/26.0").get();
			
			Elements lis = doc.select("div.category-page").select("ul.category-items").select(
					"li");
			for (Element li : lis) {

				Elements urlFromPage = li.select("div.product-image-frame")
						.select("a");
				String finalUrl = urlFromPage.attr("href");

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
		//list.add("C:\\Code\\workspace\\crawler\\src\\macys\\macys_mc.html");
		list.add(new SimpleEntry("http://www.neimanmarcus.com/en-in/Sale/Sale/Handbags/cat46520737_cat980731_cat980731/c.cat", HTMLProduct.CATEGORY_BAGS));

//		list.add("http://www.neimanmarcus.com/en-in/All-Handbags/cat46860739_cat42110769_cat13030735/c.cat");
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
