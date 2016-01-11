package Saksfifthavenue;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sdk.HTMLProduct;
import sdk.ProductSynchronizer;

public class SFASynchroniser extends ProductSynchronizer {

	public SFASynchroniser(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		ProductSynchronizer synchronizer = new SFASynchroniser

		// ("http://www.amazon.in/Behringer-XENYX-X1204USB-12-Channel-Mixer/dp/B0039PPW60/ref=sr_1_15");
		// ("http://www.amazon.in/Alesis-iO-Mix-4-Channel-Interface/dp/B00F3F70A0/ref=sr_1_25");
		// ("http://www.amazon.in/Ibanez-PF15ECE--Strings-Right-Handed-Fretboard/dp/B00LJV8Y0W/ref=lp_4581268031_1_10");
		// ("http://www.amazon.in/Black-Coffee-Formal-Shirt-11110001341626/dp/B00NFGH9NW/ref=sr_1_15");
		// ("http://www.amazon.in/Unisopent-Designs-Button-T-Shirt-Black_Large/dp/B00MGR1FI6/ref=sr_1_1");
		// ("http://www.amazon.in/Unisopent-Designs-Hooded-T-Shirt-Brown_Large/dp/B00MGR49L6/ref=sr_1_4");
		("http://www.amazon.in/Nikon-Nikkor-Prime-Digital-Camera/dp/B001S2PPT0/ref=lp_1389197031_1_2");
		// ("http://www.amazon.in/UNIVERSAL-MOBILE-MACRO-ANGLE-CAMERA/dp/B00P6DLOSS/ref=lp_1389197031_1_14");
		HTMLProduct prod = synchronizer.getProductDetails();
		System.out.println(prod.getName());
		System.out.println(prod.getPrice());
		System.out.println(prod.isOutOfStock());
		System.out.println(prod.getDiscountedPrice());

	}

	@Override
	protected boolean isProductOutOfStockFromHTML(Document doc)
			throws Exception {
		Elements notInStock = doc.select("div#availability").select(
				"span.a-size-medium");

		String notAvailable = notInStock.text().replace(".", "").trim();
		
		if (notAvailable
				.equalsIgnoreCase("Sign up to be notified when this item becomes available")
				|| notAvailable.toLowerCase().contains("Currently unavailable".toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected int getProductPriceFromHTML(Document doc) throws Exception {
		boolean outOfStock = isProductOutOfStockFromHTML(doc);
		if (outOfStock) {
			return 0;
		} else if (doc.select("td.a-span12").size() > 1) {
			Elements trs = doc.select("div#price").select("table.a-lineitem")
					.select("tbody").select("tr");
			Element productPrice = trs.select("td.a-span12").get(0);
			String priceOfProduct = productPrice.text().trim()
					.replaceAll("\\p{Z}", "").replace("Rs.", "")
					.replace(".00", "").replaceAll(",", "").trim();
			int j = Integer.parseInt(priceOfProduct);
			return j;
		} else {
			Elements trs = doc.select("div#price").select("table.a-lineitem")
					.select("tbody").select("tr");
			Elements productPrice = trs.select("td.a-span12").select(
					"span#priceblock_ourprice");
			String priceOfProduct = productPrice.text().trim()
					.replaceAll("\\p{Z}", "").replace("Rs.", "")
					.replace(".00", "").replaceAll(",", "").trim();
			int j = Integer.parseInt(priceOfProduct);
			return j;
		}
	}

	@Override
	protected String getProductNameFromHTML(Document doc) throws Exception {
		String name = doc.select("h1").select("span").text();
		return name;
	}

	@Override
	protected int getProductDiscountedPriceFromHTML(Document doc)
			throws Exception {
		boolean outOfStock = isProductOutOfStockFromHTML(doc);
		if (outOfStock) {
			return 0;
		} else if (doc.select("td.a-span12").size() > 1) {
			Elements productPrice = doc.select("span#priceblock_ourprice");
			String priceOfProduct = productPrice.text().trim()
					.replaceAll("\\p{Z}", "").replace("Rs.", "")
					.replace(".00", "").replaceAll(",", "").trim();
			if(StringUtils.isBlank(priceOfProduct)){
				productPrice = doc.select("span#priceblock_saleprice");
				priceOfProduct = productPrice.text().trim()
						.replaceAll("\\p{Z}", "").replace("Rs.", "")
						.replace(".00", "").replaceAll(",", "").trim();
			}
			if(StringUtils.isBlank(priceOfProduct))
				return 0;
			
			int j = Integer.parseInt(priceOfProduct);
			return j;
		} else {
			return 0;
		}

	}
}
