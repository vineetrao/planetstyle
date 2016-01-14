package bloomingdales;

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

public class BDCrawler implements DailyWebsiteCrawler {

	public static final String MERCHANT_ID = "bloomingdales";
	public static final String MERCHANT_NAME = "bloomingdales";
	public static final String MERCHANT_URL = "http://www1.bloomingdales.com";

	public static void main(String[] args) throws Exception {

		List<Entry<String, Integer>> allCollUrls = new BDCrawler()
				.getAllCollectionUrlsForSite(MERCHANT_URL);

		HTMLProduct override = new HTMLProduct();

		for (Entry e  : allCollUrls) {

			String collUrl = (String) e.getKey();
			int category = (int) e.getValue();

			System.out.println("Crawling : " + collUrl);
			new BDCrawler().getAllProductUrlsForCollection(collUrl).size();

			ProductXMLCreator xmlCreator = new ProductXMLCreator(collUrl,
					new BDCrawler(), new BDParser("", category), baseDir,
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
			Elements lis = doc.select("ul#thumbnails").select(
					"li");
			for (Element li : lis) {

				Elements urlFromPage = li.select("div.shortDescription")
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
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/sale/handbags?id=5070&cm_sp=categorysplash_sale_sale_1-_-row4_image_n-_-handbags", HTMLProduct.CATEGORY_BAGS));
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/womens-apparel/coats/Special_offers/Sales%20%26%20Offers?id=1001520&cm_sp=n_n_homepage_1-_-row1_imagemap_n-_-_mm2coatssave40to50womenintl", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/shoes/the-showroom/Special_offers/Sales%20%26%20Offers?id=1004464&cm_sp=n_n_homepage_1-_-row2_imagemap_n-_-_mm3entershowroomshoes", HTMLProduct.CATEGORY_SHOES));
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
