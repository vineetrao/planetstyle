package macys;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sdk.HTMLProduct;
import sdk.ProductParser;

public class MacysParser extends ProductParser {

	public MacysParser(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	public MacysParser(String url, int category) {
		super(url, category);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<String> getProductColorFromHTML(Document doc)
			throws Exception {

		List<String> list = new ArrayList<String>();
		Elements colors = doc.select("div.colors")
				.select("span.productColor");
		String colorOfProduct = colors.text().trim().toLowerCase();
		list.add(colorOfProduct);
		return list;
	}

	@Override
	protected List<String> getProductBrandFromHTML(Document doc)
			throws Exception {

		List<String> list = new ArrayList<String>();
		Elements brand = doc.select("div#brandLogo").select("img");
		String brandOfproduct = brand.attr("alt").toLowerCase().trim();
		list.add(brandOfproduct);
		return list;
	}

	@Override
	protected String getProductGenderFromHTML(Document doc) throws Exception {
		return "";
	}

	@Override
	protected String getProductCollNamefromHTML(Document doc) throws Exception {
		return "";
	}

	@Override
	protected String getProductImagefromHTML(Document doc) throws Exception {
		Elements image = doc.select("div#imageSocial").select("div.main-view-holder")
				.select("img");
		String imageTag = image.attr("src").trim();
		return imageTag;
	}

	@Override
	protected String getProductDescriptionFromHTML(Document doc)
			throws Exception {
		return doc.select("div#longDescription").text().trim();

	}

	@Override
	protected boolean isOutOfStock(Document doc)
			throws Exception {
/*		Elements notInStock = doc.select("div#availability").select(
				"span.a-size-medium");

		String notAvailable = notInStock.text().replace(".", "").trim();
		
		if (notAvailable
				.equalsIgnoreCase("Sign up to be notified when this item becomes available")
				|| notAvailable.toLowerCase().contains("Currently unavailable".toLowerCase())) {
			return true;
		} else {
	*/		return false;
	//	}
	}

	@Override
	protected float getProductPriceFromHTML(Document doc) throws Exception {
		boolean outOfStock = isOutOfStock(doc);
		if (outOfStock) {
			return 0;
		} 
		Elements priceDiv = doc.select("div#productDescription").select("div.standardProdPricingGroup");
		Elements productPrices = priceDiv.select("span");
		for (Element price : productPrices) {
			String priceOfProduct = price.text().trim()
					.replaceAll("\\p{Z}", "").replace("USD", "").replace("Orig.", "").replace("$", "")
					.replace(".00", "").replaceAll(",", "").trim();
			float j = Float.parseFloat(priceOfProduct);
			return j;			
		}
		return 0;
	}

	@Override
	protected String getProductNameFromHTML(Document doc) throws Exception {
		String name = doc.select("h1#productTitle").text().trim();
		return name;
	}

	@Override
	protected float getProductDiscountedPriceFromHTML(Document doc)
			throws Exception {
		boolean outOfStock = isOutOfStock(doc);
		if (outOfStock) {
			return 0;
		} 
		Elements priceDiv = doc.select("div#productDescription").select("div.standardProdPricingGroup");
		if (!priceDiv.select("span.priceSale").isEmpty()) {
			Elements productPrice = priceDiv.select("span.priceSale");
			String priceOfProduct = productPrice.text().trim()
					.replaceAll("\\p{Z}", "").replace("Now", "").replace("$", "").replace("USD", "")
					.replace(".00", "").replaceAll(",", "").trim();
			float j = Float.parseFloat(priceOfProduct);
			return j;
		} else {
			return 0;
		}

	}

	public static void main(String[] args) throws Exception {

		ProductParser parser = new MacysParser


		("http://www1.macys.com/shop/product/michael-michael-kors-selma-medium-satchel?ID=2438399&CategoryID=27726#fn=sp%3D1%26spc%3D417%26slotId%3D2");
			
		
		HTMLProduct prod = parser.getProductDetails();
		System.out.println(prod.isOutOfStock());
		System.out.println(prod.getName());
		System.out.println(prod.getPrice());
		System.out.println(prod.getDiscountedPrice());
		System.out.println(prod.getDesc());
		System.out.println(prod.getBrand());
		System.out.println(prod.getColor());
		System.out.println(prod.getColl());
		System.out.println(prod.getGender());
		System.out.println(prod.getSrcUrlOfImage());
		System.out.println(prod.getUrl());

	}

}
