package Saksfifthavenue;

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

public class SFACrawler implements DailyWebsiteCrawler {

	public static final String MERCHANT_ID = "saksfifthavenue";
	public static final String MERCHANT_NAME = "saksfifthavenue";
	public static final String MERCHANT_URL = "http://www.saksfifthavenue.com/";

	public static void main(String[] args) throws Exception {

		List<Entry<String, Integer>> allCollUrls = new SFACrawler()
				.getAllCollectionUrlsForSite(MERCHANT_URL);

		HTMLProduct override = new HTMLProduct();

		for (Entry e : allCollUrls) {

			String collUrl = (String) e.getKey();
			int category = (int) e.getValue();

			System.out.println("Crawling : " + collUrl);

			ProductXMLCreatorMultiThreaded xmlCreator = new ProductXMLCreatorMultiThreaded(collUrl,
					new SFACrawler(), new SFAParser("", category), baseDir,
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
	public List<Entry<String, Integer>> getAllCollectionUrlsForSite(String url)
			throws Exception {
		List<Entry<String, Integer>> list = new ArrayList<>();

		//sale evening dresses
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306422140+1553", HTMLProduct.CATEGORY_CLOTHING));
		//sale cocktail dresses
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306422146+1553", HTMLProduct.CATEGORY_CLOTHING));
		//sale day dresses
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306422153+1553", HTMLProduct.CATEGORY_CLOTHING));
		//sale workwear dresses
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306436538+1553", HTMLProduct.CATEGORY_CLOTHING));
		//sale party dresses
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306633759+1553", HTMLProduct.CATEGORY_CLOTHING));
		//sale mini dresses
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306440465+1553", HTMLProduct.CATEGORY_CLOTHING));
		
		//sale coats
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&N=306633841+1553", HTMLProduct.CATEGORY_CLOTHING));
		//sale jackets and vests
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&N=306436569+1553", HTMLProduct.CATEGORY_CLOTHING));
		
		//sale sandals shoes
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306624269+1553", HTMLProduct.CATEGORY_SHOES));
		//sale pumps & slingbacks shoes
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306624262+1553", HTMLProduct.CATEGORY_SHOES));
		//sale wedges shoes
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306624276+1553", HTMLProduct.CATEGORY_SHOES));
		//sale shoes boots
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306624250+1553", HTMLProduct.CATEGORY_SHOES));
		//sale shoes evening
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306624256+1553", HTMLProduct.CATEGORY_SHOES));
		//sale shoes exotics
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&Ns=P_bestsellers_units|1||P_brandname||P_product_code&N=306624257+1553", HTMLProduct.CATEGORY_SHOES));
		
		//sale bags
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&N=306622829+1553", HTMLProduct.CATEGORY_BAGS));
		
		//sale watches
		list.add(new SimpleEntry("http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Ns=P_bestsellers_units%7c1%7c%7cP_brandname%7c%7cP_product_code&N=306418148+1553", HTMLProduct.CATEGORY_WATCHES));
		
		
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
