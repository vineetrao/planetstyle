package macys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javassist.compiler.Javac;
import sdk.HTMLProduct;
import sdk.ProductXMLCreator;
import sdk.auto.DailyWebsiteCrawler;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public class MacysCrawler implements DailyWebsiteCrawler {

	public static final String MERCHANT_ID = "macys";
	public static final String MERCHANT_NAME = "macys";
	public static final String MERCHANT_URL = "http://www1.macys.com";

	public static void main(String[] args) throws Exception {

		List<Entry<String, Integer>> allCollUrls = new MacysCrawler()
				.getAllCollectionUrlsForSite(MERCHANT_URL);

		HTMLProduct override = new HTMLProduct();

		for (Entry e : allCollUrls) {

			String collUrl = (String) e.getKey();
			int category = (int) e.getValue();
			System.out.println("Crawling : " + collUrl);

			ProductXMLCreator xmlCreator = new ProductXMLCreator(collUrl,
					new MacysCrawler(), new MacysParser("", category), baseDir,
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

				Elements urlFromPage = li.select("div.innerWrapper")
						.select("div.fullColorOverlayOff").select("a");
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
		list.add(new SimpleEntry("http://www1.macys.com/shop/handbags-accessories/michael-michael-kors?id=27726&edge=hybrid&cm_sp=c2_1111INT_subsplash_women-women%27s-brands-michael-michael-kors-_-row4-_-icon_handbags&intnl=true", HTMLProduct.CATEGORY_BAGS));
		list.add(new SimpleEntry("http://www1.macys.com/shop/womens-clothing/womens-coats?id=269&edge=hybrid&cm_re=2016.01.11-_-HOMEPAGE_INCLUDE_1-_-CATEGORY+--+5125+--+269%3Awomen&intnl=true", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www1.macys.com/shop/handbags-accessories/sale-clearance?id=28273&edge=hybrid&cm_re=2016.01.11-_-HOMEPAGE_INCLUDE_1-_-CATEGORY+--+5125+--+28273%3Ahandbags&intnl=true", HTMLProduct.CATEGORY_BAGS));
		list.add(new SimpleEntry("http://www1.macys.com/shop/shoes/sale-clearance?id=13604&edge=hybrid&cm_re=2016.01.11-_-HOMEPAGE_INCLUDE_1-_-CATEGORY+--+5125+--+13604%3Ashoes&intnl=true", HTMLProduct.CATEGORY_SHOES));
		list.add(new SimpleEntry("http://www1.macys.com/shop/jewelry-watches/watch-sale/Special_offers,Pageindex,Productsperpage/Clearance%2FCloseout,1,40?id=28067&edge=hybrid&cm_sp=c2_1111INT_catsplash_jewelry-%26-watches-watches-watches-_-row7-_-shop-all_shop-watch-clearance&panel=jewelry+%26+watches-watches-watches_7_product-pool%3A%20shop%20watch%20clearance&panel=undefined_7_product-pool:%20shop%20watch%20clearance", HTMLProduct.CATEGORY_WATCHES));
		list.add(new SimpleEntry("http://www1.macys.com/shop/womens-clothing/dresses?id=5449&edge=hybrid&cm_sp=c2_1111INT_catsplash_women-_-row3-_-icon_dresses&intnl=true", HTMLProduct.CATEGORY_CLOTHING));
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
