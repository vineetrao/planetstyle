package sdk;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Arrays;



public abstract class ProductParser {
	
	protected String url;
	protected int category = HTMLProduct.CATEGORY_UNKNOWN;
	
	public ProductParser()
	{
		
	}
	public ProductParser(String url){
		this.url = url;
	}
	
	public ProductParser(String url, int category){
		this.url = url;
		this.category = category;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getCategory() 
	{
		return category;
	}
	
	public void setCategory(int category) 
	{
		this.category = category;
	}

	public HTMLProduct getProductDetails() throws Exception{
		return prepareProductDetails(url, getHTMLDocument());
	}
	
	public Document getHTMLDocument() throws Exception{
		try{
			return Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:26.0) Gecko/20100101 Firefox/26.0").header("X-Forwarded-For", "180.149.241.48").timeout(20000).get();
		}
		catch(Exception e){
			Thread.sleep(1000);
			System.out.println("Retrying ...");
			try{
				return Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:26.0) Gecko/20100101 Firefox/26.0").header("X-Forwarded-For", "180.149.241.48").timeout(20000).get();
			}
			catch(Exception e1){
				Thread.sleep(1000);
				System.out.println("Retrying ...");
				return Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:26.0) Gecko/20100101 Firefox/26.0").header("X-Forwarded-For", "180.149.241.48").timeout(20000).get();
			}
		}
	}
	
	private HTMLProduct prepareProductDetails(String url, Document doc) throws Exception {
		
		if(isOutOfStock(doc))
			throw new Exception("Out of Stock : " + url);
		
		HTMLProduct prod = new HTMLProduct();
		prod.setUrl(getProductURLfromHTML(doc));
		prod.setName(getProductNameFromHTML(doc));
		prod.setDesc(getProductDescriptionFromHTML(doc));
		prod.setSrcUrlOfImage(getProductImagefromHTML(doc));
		prod.setPrice(getProductPriceFromHTML(doc));
		prod.setDiscountedPrice(getProductDiscountedPriceFromHTML(doc));
		prod.setColl(getProductCollNamefromHTML(doc));
		
		prod.setGender(getProductGenderFromHTML(doc));
		prod.setColor(getProductColorFromHTML(doc));
		prod.setStyle(getProductStyleFromHTML(doc));
		prod.setBrand(getProductBrandFromHTML(doc));
		prod.setCategory(new ArrayList<>(Arrays.asList(String.valueOf(getCategory()))));
//		prod.setCategory(getProductCategoryFromHTML(doc));
		
		return prod;
	}

	protected boolean isOutOfStock(Document doc) throws Exception{
		return false;
	}
	
	protected List<String> getProductCategoryFromHTML(Document doc) throws Exception {
		return ParserUtils.getCategoryFromFreeFlowingText(getProductNameFromHTML(doc), getProductCollNamefromHTML(doc));
	}

	protected abstract List<String> getProductBrandFromHTML(Document doc) throws Exception;

	protected List<String> getProductStyleFromHTML(Document doc) throws Exception {
		return ParserUtils.getStylesFromFreeFlowingText(getProductNameFromHTML(doc));
	}

	protected abstract List<String> getProductColorFromHTML(Document doc) throws Exception;

	protected abstract String getProductGenderFromHTML(Document doc) throws Exception;

	protected abstract String getProductCollNamefromHTML(Document doc) throws Exception;

	protected abstract float getProductDiscountedPriceFromHTML(Document doc) throws Exception;

	protected abstract float getProductPriceFromHTML(Document doc) throws Exception;

	protected abstract String getProductImagefromHTML(Document doc) throws Exception;

	protected abstract String getProductDescriptionFromHTML(Document doc) throws Exception;

	protected abstract String getProductNameFromHTML(Document doc) throws Exception;

	protected String getProductURLfromHTML(Document doc) throws Exception{
		
		Elements productMetas = doc.select("link");

		for (Element meta : productMetas) {
			if ("canonical".equalsIgnoreCase(meta.attr("rel"))) {
				if(!StringUtils.isBlank(meta.attr("href"))){
					String urlFromMeta = TextNode.createFromEncoded(meta.attr("href"), null).toString();
					return urlFromMeta;
//					return meta.attr("href");
				}
			}
		}
		
		productMetas = doc.select("meta");
		
		for (Element meta : productMetas) {
			if("og:url".equalsIgnoreCase(meta.attr("property"))){
				return meta.attr("content");
			}
		}

		return url;
	}
}
