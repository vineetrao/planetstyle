package Saksfifthavenue;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sdk.HTMLProduct;
import sdk.ProductParser;

public class SFAParser extends ProductParser {

	public SFAParser()
	{
		super();
	}
	public SFAParser(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public SFAParser(String url, int category) {
		super(url, category);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected List<String> getProductColorFromHTML(Document doc)
			throws Exception {

		List<String> list = new ArrayList<String>();
		Elements colors = doc.select("div.detail-column")
				.select("div.color").select("div.body").select("div.items").select("a");
		for (Element color : colors) 
		{
			list.add(color.attr("title").trim().toLowerCase());
			break;
		}
		return list;
	}

	@Override
	protected List<String> getProductBrandFromHTML(Document doc)
			throws Exception {

		List<String> list = new ArrayList<String>();
		Elements brand = doc.select("div.main-product").select("div.detail-column").select("h1.component").select("a");
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
		String detailsScript = doc.select("script").html();
		String imageFromScript = detailsScript.substring(detailsScript.indexOf("image_set\""), detailsScript.indexOf("main_image"));
		String image = imageFromScript.trim()
				.replaceAll("\\p{Z}", "").replace("image_set", "")
				.replaceAll(",", "").replaceAll("\"", "").replaceAll(":", "").trim();
		return "http://s7d9.scene7.com/is/image/".concat(image);
	}

	@Override
	protected String getProductDescriptionFromHTML(Document doc)
			throws Exception {
		Elements details = doc.select("div.details-section").select("div.accordion").select("div");
		for (Element description : details) {
			return description.text().trim();
		}
		return "";

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
		String detailsScript = doc.select("script").html();
		String priceFromScript = detailsScript.substring(detailsScript.indexOf("list_price\""), detailsScript.indexOf("sale_price"));
		String priceOfProduct = priceFromScript.trim()
					.replaceAll("\\p{Z}", "").replace("INR", "").replace("$", "").replace("list_price", "")
					.replace(".00", "").replaceAll(",", "").replaceAll("\"", "").trim().replaceAll(":", "").replaceAll(";", "").replaceAll("&#36", "");
		float j = Float.parseFloat(priceOfProduct);
		return j;			
	}

	@Override
	protected String getProductNameFromHTML(Document doc) throws Exception {
		String name = doc.select("div.main-product").select("div.detail-column").select("header").select("h2.short-description").text().trim();
		return name;
	}

	@Override
	protected float getProductDiscountedPriceFromHTML(Document doc)
			throws Exception {
		boolean outOfStock = isOutOfStock(doc);
		if (outOfStock) {
			return 0;
		} 
		String detailsScript = doc.select("script").html();
		String priceFromScript = detailsScript.substring(detailsScript.indexOf("sale_price\""), detailsScript.indexOf("on_sale"));
		String priceOfProduct = priceFromScript.trim()
					.replaceAll("\\p{Z}", "").replace("INR", "").replace("$", "").replace("sale_price", "")
					.replace(".00", "").replaceAll(",", "").replaceAll("\"", "").trim().replaceAll(":", "").replaceAll(";", "").replaceAll("&#36", "");
		float j = Float.parseFloat(priceOfProduct);
		return j;
	
	}

	public static void main(String[] args) throws Exception {

		ProductParser parser = new SFAParser


		("http://www.saksfifthavenue.com/main/ProductDetail.jsp?PRODUCT%3C%3Eprd_id=845524446434829");
			
		
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
