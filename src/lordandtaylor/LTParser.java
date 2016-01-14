package lordandtaylor;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sdk.HTMLProduct;
import sdk.ProductParser;

public class LTParser extends ProductParser {

	public LTParser(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public LTParser(String url, int category) {
		super(url, category);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected List<String> getProductColorFromHTML(Document doc)
			throws Exception {

		List<String> list = new ArrayList<String>();
		String colorOfProduct="";
		Elements colors = doc.select("div.color_swatch_list")
				.select("ul.detail_color").select("li");
		for (Element li : colors) 
		{
			colorOfProduct = li.select("a").attr("title").trim().toLowerCase();
			break;
		}
		if (colors.isEmpty()) 
		{
			colors = doc.select("div.colors").select("div.colorvalue").select("span");
			colorOfProduct = colors.text().trim().toLowerCase();
		}
		if (colors.isEmpty()) 
		{
			colors = doc.select("div.colors").select("div.colorvalue").select("select").select("option");
			if (!colors.isEmpty()) 
			{
				for (Element e : colors)
				{
					colorOfProduct = e.text().trim().toLowerCase();
					break;
				}
			}
		}
		if (colors.isEmpty()) 
		{
			colors = doc.select("div.colors").select("div.colorvalue").select("span");
			colorOfProduct = colors.text().trim().toLowerCase();
		}
		
		list.add(colorOfProduct);
		return list;
	}

	@Override
	protected List<String> getProductBrandFromHTML(Document doc)
			throws Exception {

		List<String> list = new ArrayList<String>();
//		Elements brand = doc.select("div#brandLogo").select("img");
//		String brandOfproduct = brand.attr("alt").toLowerCase().trim();
//		list.add(brandOfproduct);
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
		Elements detailsDiv = doc.select("div[id^=entitledItem]");
		String details = detailsDiv.text(); 
		String imageSrc = details.substring(details.indexOf("ItemImage"));
		imageSrc = imageSrc.substring(imageSrc.indexOf("//"), imageSrc.indexOf("\",")); 
		String imageTag = "http:".concat(imageSrc);
		return imageTag.trim();
	}

	@Override
	protected String getProductDescriptionFromHTML(Document doc)
			throws Exception {
		Elements detailsElement = doc.select("div#detial_main_content").select("p");
		if (detailsElement.isEmpty()) 
		{
			detailsElement = doc.select("div.descriptionsContent").select("p.info");
		}
		return detailsElement.text().trim();
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
		Elements detailsDiv = doc.select("div[id^=entitledItem]");
		String details = detailsDiv.text(); 
		String priceText = details.substring(details.indexOf("listPrice"));
		priceText = priceText.substring(priceText.indexOf("$"), priceText.indexOf("\",")).replace("$", ""); 
		float j = Float.parseFloat(priceText);
		return j;			
	}

	@Override
	protected String getProductNameFromHTML(Document doc) throws Exception {
		Elements h2 = doc.select("div.detial_right").select("h2.detial"); 
		if (h2.isEmpty()) 
		{
			h2 = doc.select("div.descriptions").select("h2.title"); 
		}
		String name = h2.text().trim();
		return name;
	}

	@Override
	protected float getProductDiscountedPriceFromHTML(Document doc)
			throws Exception {
		boolean outOfStock = isOutOfStock(doc);
		if (outOfStock) {
			return 0;
		} 
		Elements detailsDiv = doc.select("div[id^=entitledItem]");
		String details = detailsDiv.text(); 
		String listPrice = details.substring(details.indexOf("listPrice"));
		listPrice = listPrice.substring(listPrice.indexOf("$"), listPrice.indexOf("\",")).replace("$", ""); 
		String offerPrice = details.substring(details.indexOf("offerPrice"));
		offerPrice = offerPrice.substring(offerPrice.indexOf("$"), offerPrice.indexOf("\",")).replace("$", ""); 

		float lp = Float.parseFloat(listPrice);
		float op = Float.parseFloat(offerPrice);
		
		if (op < lp)
			return op;	
		else 
			return 0;
		
	}

	public static void main(String[] args) throws Exception {

		ProductParser parser = new LTParser


//		("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/en/lord-and-taylor/brands/best-selling-handbags/coach-turnlock-tie-small-tote-in-refined-pebble-leather");
			
//		("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/en/lord-and-taylor/brands/best-selling-handbags/suzannah-leather-tote");
//		("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/en/lord-and-taylor/link-small-leather-shoulder-bag");
			
		("http://www.lordandtaylor.com/webapp/wcs/stores/servlet/ProductDisplay?urlRequestType=Base&catalogId=10102&categoryId=553225&productId=4268747&errorViewName=ProductDisplayErrorView&urlLangId=-1&langId=-1&top_category=17651&parent_category_rn=17651&storeId=10151");
		
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
