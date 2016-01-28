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
import sdk.ProductXMLCreatorMultiThreaded;
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

			System.out.println("Crawling : " + collUrl + " -- Category -- " + category);
//			new BDCrawler().getAllProductUrlsForCollection(collUrl).size();

			ProductXMLCreatorMultiThreaded xmlCreator = new ProductXMLCreatorMultiThreaded(collUrl,
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

			int maxBodySize = 2048000;//2MB (default is 1MB) 0 for unlimited size
			Document doc = Jsoup.connect(newUrl).maxBodySize(maxBodySize).timeout(10000)
					.userAgent("Mozilla").get();
			
			Elements lis = doc.select("ul#thumbnails").select(
					"li");
			for (Element li : lis) {

				Elements urlFromPage = li.select("div.shortDescription").select("div.prodName")
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
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/sale/handbags/Sortby/BEST_SELLERS?id=5070&cm_sp=LEFTNAV_INT-_-sale-_-Header-Handbags;jsessionid=vqg4i4xJpywzeevXVfMgg2oD.MA100BLVNAV519_bloomies-navapp_replica_prod_cellB_ma100blvnav519_m01", HTMLProduct.CATEGORY_BAGS));	
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/sale/women/Womens_apparel_type,Sortby/Dresses,BEST_SELLERS?id=3985&cm_sp=LEFTNAV_INT-_-sale-_-Header-Women;jsessionid=vqg4i4xJpywzeevXVfMgg2oD.MA100BLVNAV519_bloomies-navapp_replica_prod_cellB_ma100blvnav519_m01", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/sale/women/Womens_apparel_type,Sortby/Coats,BEST_SELLERS?id=3985&cm_sp=LEFTNAV_INT-_-sale-_-Header-Women;jsessionid=vqg4i4xJpywzeevXVfMgg2oD.MA100BLVNAV519_bloomies-navapp_replica_prod_cellB_ma100blvnav519_m01", HTMLProduct.CATEGORY_CLOTHING));
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/sale/women/Womens_apparel_type,Sortby/Jackets,BEST_SELLERS?id=3985&cm_sp=LEFTNAV_INT-_-sale-_-Header-Women;jsessionid=vqg4i4xJpywzeevXVfMgg2oD.MA100BLVNAV519_bloomies-navapp_replica_prod_cellB_ma100blvnav519_m01", HTMLProduct.CATEGORY_CLOTHING));
			
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/sale/shoes/Shoe_style,Sortby/Flats%7CParty%20%26%20Evening%7CPumps%7CSandals,BEST_SELLERS?id=4841&cm_sp=NAVIGATION_INTL-_-TOP_NAV-_-3977-SALE-%26-CLEARANCE-Shoes", HTMLProduct.CATEGORY_SHOES));
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/sale/shoes/Shoe_style,Sortby/Booties%7CBoots,BEST_SELLERS?id=4841&cm_sp=NAVIGATION_INTL-_-TOP_NAV-_-3977-SALE-%26-CLEARANCE-Shoes", HTMLProduct.CATEGORY_SHOES));
		list.add(new SimpleEntry("http://www1.bloomingdales.com/shop/sale/jewelry-accessories/Jewelry_accessory_sale_type,Sortby/Watches,BEST_SELLERS?id=5303&cm_sp=browse_sale_jewelry-%2526-accessories_1-_-row1_imagemap_n-_-_watches", HTMLProduct.CATEGORY_WATCHES));
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
