package sdk;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class ProductSynchronizer {
	
	protected String url;
	
	public ProductSynchronizer(String url){
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HTMLProduct getProductDetails() throws Exception{
		return prepareProductDetails(url, getHTMLDocument());
	}
	
	public Document getHTMLDocument() throws Exception{
		
		return Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-IN; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").
				header("Accept-Language", "en-in").
				header("X-Forwarded-For", "180.149.241.48").
				timeout(10000).get();
	}
	
	private HTMLProduct prepareProductDetails(String url, Document doc) throws Exception 
	{	
		HTMLProduct prod = new HTMLProduct();
		prod.setUrl(getProductURLfromHTML(doc));
		prod.setName(getProductNameFromHTML(doc));
		prod.setPrice(getProductPriceFromHTML(doc));
		prod.setDiscountedPrice(getProductDiscountedPriceFromHTML(doc));
		prod.setOutOfStock(isProductOutOfStockFromHTML(doc));
		
		return prod;
	}

	protected abstract boolean isProductOutOfStockFromHTML(Document doc) throws Exception;
	
	protected abstract int getProductDiscountedPriceFromHTML(Document doc) throws Exception;

	protected abstract int getProductPriceFromHTML(Document doc) throws Exception;

	protected abstract String getProductNameFromHTML(Document doc) throws Exception;

	protected String getProductURLfromHTML(Document doc) throws Exception{
		
		Elements productMetas = doc.select("link");

		for (Element meta : productMetas) {
			if ("canonical".equalsIgnoreCase(meta.attr("rel"))) {
				if(!StringUtils.isBlank(meta.attr("href")))
					return meta.attr("href");
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
