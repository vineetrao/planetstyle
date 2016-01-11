package sdk.auto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import sdk.HTMLProduct;
import sdk.ParserXMLUtils;
import sdk.ProductParser;
import sdk.ProductXMLCreator;

public class DailyProductXMLCreator extends ProductXMLCreator{

	protected List<String> allowedCats = new ArrayList<String>();
	protected StringBuffer report;
	
	public DailyProductXMLCreator(String collUrl, DailyWebsiteCrawler crawler, ProductParser parser, String baseDir, HTMLProduct override
			, List<String> allowedCats, StringBuffer report){
		
		super(collUrl, crawler, parser, baseDir, crawler.getMerchantId(), crawler.getMerchantName(), crawler.getMerchantURL(), override);
		this.allowedCats = allowedCats;
		this.report = report;
	}
	
	public void generateXML() throws Exception{
		
		List<HTMLProduct> prods = new ArrayList<HTMLProduct>();
		
		Map<String, String> failedURLs = new HashMap<String, String>(); 
		int count = 0;
		
		Set<String> uniqueCats = new TreeSet<String>();
		Set<String> uniqueColor = new TreeSet<String>();
		Set<String> uniqueColls = new TreeSet<String>();
		Set<String> uniqueStyles = new TreeSet<String>();
		Set<String> uniqueBrands = new TreeSet<String>();
		Set<String> uniqueGender = new TreeSet<String>();
		
		
		
		boolean isCrawled = false;
		int noOfProdProcessed=0;
		int noOfProdFailed = 0;
		List<String> prodUrls = crawler.getAllProductUrlsForCollection(collUrl); 
				
		for (String prodUrl : prodUrls) {
			isCrawled=true;
			count ++;
			report.append("<p>" + count + ": Parsing URL : " + prodUrl + "</p>\n");
			
			try{
				parser.setUrl(prodUrl);
				HTMLProduct prod = parser.getProductDetails();
				
				/*
				 * Set Coll and Gender as null here, as they will come from Override
				 */
				prod.setColl(null);
				prod.setGender(null);
				
				// Check if allowed Cats is fine
				checkAllowedCats(prod);
				
				overrideProductData(prod);
				validateProductData(prod);
				
				prods.add(prod);
				
				uniqueCats.addAll(prod.getCategory());
				uniqueColor.addAll(prod.getColor());
				uniqueColls.add(prod.getColl());
				uniqueStyles.addAll(prod.getStyle());
				uniqueBrands.addAll(prod.getBrand());
				uniqueGender.add(prod.getGender());
				noOfProdProcessed=noOfProdProcessed+1;
			}
			catch(Exception e){
				//e.printStackTrace();
				report.append("<p>" + "####### Failed: " + prodUrl + " : " + e.getMessage() + " ######" + "</p>\n");
				failedURLs.put(prodUrl, e.getMessage());
				noOfProdFailed=noOfProdFailed+1;
			}
			Thread.sleep(500);
		}
		CrawlerRunningAnalysis.logIntoAFile(merchantName, collUrl, isCrawled, prodUrls.size(), noOfProdProcessed, noOfProdFailed);
		
		
		Set<String> failedUrlsList = failedURLs.keySet();
		
		report.append("<p>" + "Total URLs: " + prodUrls.size() + "</p>\n");
		report.append("<p>" + "Total Failed URL : " + failedUrlsList.size() + "</p>\n");
		
		report.append("<p>" + "Unique cats: " + uniqueCats + "</p>\n");
		report.append("<p>" + "Unique color: " + uniqueColor + "</p>\n");
		report.append("<p>" + "Unique collections: " + uniqueColls + "</p>\n");
		report.append("<p>" + "Unique styles: " + uniqueStyles + "</p>\n");
		report.append("<p>" + "Unique brands: " + uniqueBrands + "</p>\n");
		report.append("<p>" + "Unique Gender: " + uniqueGender + "</p>\n");
		
		generateXML(prods);
	}

	private void checkAllowedCats(HTMLProduct prod) {
		
		if(allowedCats == null || allowedCats.size() <= 0)
			return;
		
		// Create a shallow copy of the list
		List<String> cats = new ArrayList<String>(prod.getCategory());
		
		for (String cat : cats) {
			boolean catFound = false;
			
			for (String allowedCat : allowedCats) {
				if(cat.equalsIgnoreCase(allowedCat))
					catFound = true;
			}
			
			if(!catFound){
				prod.getCategory().remove(cat);
				report.append("<p>" + "WARN: Removed unallwed category " + cat + " for URL : " + prod.getUrl() + "</p>\n");
			}
		}
	}

	@Override
	protected void generateXML(List<HTMLProduct> prods) throws Exception{
		
		String prodXML = ParserXMLUtils.getProductXML(prods, merchantId, merchantName, merchantUrl); 
		
		String fileName = override.getCategory().toString().replaceAll(", ", "_").replaceAll(" ", "-").replaceAll("\\[", "").replaceAll("\\]", "")
				+ "_" + merchantName + "_" + Math.abs(collUrl.hashCode()) + ".xml"; 
		
		String xmlFile = baseDir + File.separator + fileName;
		
		PrintStream out = new PrintStream(new FileOutputStream(xmlFile));
		out.print(prodXML);
		out.close();
		
		report.append("<p>" + "XML Saved : " + xmlFile + "</p>\n");
	}
}
