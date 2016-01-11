package NeimanMarcus;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sdk.HTMLProduct;
import sdk.ProductParser;

public class NMParser extends ProductParser {

	public NMParser(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<String> getProductColorFromHTML(Document doc)
			throws Exception {

		List<String> list = new ArrayList<String>();
		Elements colors = doc.select("div#color-selection")
				.select("select#color-selector").select("option");
		int i = 1; 
		for (Element color : colors) 
		{
			if (i++ > 1) 
			{
				list.add(color.text().trim().toLowerCase());
			}
		}
		return list;
	}

	@Override
	protected List<String> getProductBrandFromHTML(Document doc)
			throws Exception {

		List<String> list = new ArrayList<String>();
		Elements brand = doc.select("section#brand-title").select("a");
		String brandOfproduct = brand.text().toLowerCase().trim();
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
		String image = doc.select("div#product-image").select("img").attr("src").trim();
		return image;
	}

	@Override
	protected String getProductDescriptionFromHTML(Document doc)
			throws Exception {
		Elements description = doc.select("section#details-and-care").select("div.accordion-content");
		return description.text().trim();

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
		Elements priceSection = doc.select("section#price");
		// is product discounted?
		Elements priceSpan = priceSection.select("span.regular-price");
		if (priceSpan.size()==0) 
		{
			priceSpan = priceSection.select("span");
		}
		for (Element price : priceSpan) {
			String priceOfProduct = price.text().trim()
					.replaceAll("\\p{Z}", "").replace("INR", "").replace("Was:", "")
					.replace(".00", "").replaceAll(",", "").trim();
			float j = Float.parseFloat(priceOfProduct);
			return j;			
		}
		return 0;
	}

	@Override
	protected String getProductNameFromHTML(Document doc) throws Exception {
		String name = doc.select("section#product-title").select("h1").text().trim();
		return name;
	}

	@Override
	protected float getProductDiscountedPriceFromHTML(Document doc)
			throws Exception {
		boolean outOfStock = isOutOfStock(doc);
		if (outOfStock) {
			return 0;
		} 
		Elements priceSection = doc.select("section#price");
		// is product discounted?
		Elements priceSpan = priceSection.select("span.sale-price");

		if (!priceSpan.isEmpty()) {
			String priceOfProduct = priceSpan.text().trim()
					.replaceAll("\\p{Z}", "").replace("INR", "").replace("Now:", "")
					.replace(".00", "").replaceAll(",", "").trim();
			float j = Float.parseFloat(priceOfProduct);
			return j;
		} else {
			return 0;
		}

	}

	public static void main(String[] args) throws Exception {

		ProductParser parser = new NMParser


		("http://shop.nordstrom.com/s/kate-spade-new-york-fairmount-square-monday-crossbody-bag/4149872?origin=category");
			
		
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
