package sdk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;


public class ProductXMLCreator {

	protected String collUrl;
	protected WebsiteCrawler crawler;
	protected ProductParser parser;
	
	protected String baseDir;
	protected String merchantId;
	protected String merchantName;
	protected String merchantUrl;
	
	protected HTMLProduct override;
	
	public ProductXMLCreator(String collUrl, WebsiteCrawler crawler, ProductParser parser, String baseDir,
			String merchantId, String merchantName, String merchantUrl){
		this.collUrl = collUrl;
		this.crawler = crawler;
		this.parser = parser;
		this.baseDir = baseDir;
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.merchantUrl = merchantUrl;
	}
	
	public ProductXMLCreator(String collUrl, WebsiteCrawler crawler, ProductParser parser, String baseDir,
			String merchantId, String merchantName, String merchantUrl, HTMLProduct override){
		this.collUrl = collUrl;
		this.crawler = crawler;
		this.parser = parser;
		this.baseDir = baseDir;
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.merchantUrl = merchantUrl;
		this.override = override;
	}
	
	public void generateXML() throws Exception{
		
		List<HTMLProduct> prods = new ArrayList<HTMLProduct>();
		
		Map<String, String> failedURLs = new HashMap<String, String>(); 
		int count = 0;
		
		Set<String> uniqueTagComboUrls = new TreeSet<String>();
		Set<String> uniqueCats = new TreeSet<String>();
		Set<String> uniqueColor = new TreeSet<String>();
		Set<String> uniqueColls = new TreeSet<String>();
		Set<String> uniqueStyles = new TreeSet<String>();
		Set<String> uniqueBrands = new TreeSet<String>();
		Set<String> uniqueGender = new TreeSet<String>();
		
		List<String> prodUrls = crawler.getAllProductUrlsForCollection(collUrl); 
				
		for (String prodUrl : prodUrls) {
			
//			if (count > 5) break;
			count ++;
			System.out.println(count + ": Parsing URL : " + prodUrl);
			
			try{
				parser.setUrl(prodUrl);
				HTMLProduct prod = parser.getProductDetails();
				
				overrideProductData(prod);
				validateProductData(prod);
				
				prods.add(prod);
				
				uniqueCats.addAll(prod.getCategory());
				uniqueColor.addAll(prod.getColor());
				uniqueColls.add(prod.getColl());
				uniqueStyles.addAll(prod.getStyle());
				uniqueBrands.addAll(prod.getBrand());
				uniqueGender.add(prod.getGender());
				
				List<String> cats = prod.getCategory();
				for (String cat : cats)
					uniqueTagComboUrls.add("http://www.allmemoirs.com/trends/" + prod.getColl().toLowerCase() + "/" + cat.replaceAll(" ", "-"));
				
			}
			catch(Exception e){
				//e.printStackTrace();
				System.out.println("####### Failed: " + prodUrl + " : " + e.getMessage() + " ######");
				failedURLs.put(prodUrl, e.getMessage());
			}
			Thread.sleep(500);
		}
		
		System.out.println("-----------------------");
		Set<String> failedUrlsList = failedURLs.keySet();
		System.out.println("Total URLs: " + prodUrls.size());
		System.out.println("Total Failed URL : " + failedUrlsList.size());
		for (String failedURL : failedUrlsList) {
			System.out.println(failedURL + ": " + failedURLs.get(failedURL));
		}
		System.out.println("-----------------------");
		
		System.out.println("Unique cats: " + uniqueCats);
		System.out.println("Unique color: " + uniqueColor);
		System.out.println("Unique collections: " + uniqueColls);
		System.out.println("Unique styles: " + uniqueStyles);
		System.out.println("Unique brands: " + uniqueBrands);
		System.out.println("Unique Gender: " + uniqueGender);
		
		for (String uniqueTagComboUrl : uniqueTagComboUrls) {
			System.out.println("Tag Combo URL: " + uniqueTagComboUrl);
		}
		
		generateXML(prods);
	}

	protected void validateProductData(HTMLProduct prod) throws Exception{
		
		if(StringUtils.isBlank(prod.getName()))
			throw new Exception("Prod Name is Blank");
		
		if(StringUtils.isBlank(prod.getUrl()))
			throw new Exception("Prod Url is Blank");
		
		if(StringUtils.isBlank(prod.getSrcUrlOfImage()))
			throw new Exception("Prod Image is Blank");
		
//		if(StringUtils.isBlank(prod.getColl()))
//			throw new Exception("Prod Coll is Blank");
		
		if(prod.getPrice() <= 0)
			throw new Exception("Prod Price is Blank");
		
		if(prod.getDiscountedPrice() < 0)
			throw new Exception("Discounted Price is Negative");
		
		if(prod.getPrice() < prod.getDiscountedPrice())
			throw new Exception("Prod Price is less than discounted price");
		
//		if(prod.getCategory() == null || prod.getCategory().size() <= 0)
//			throw new Exception("Prod Category is Blank");
	}
	
	protected void overrideProductData(HTMLProduct prod) {
		
		if(override != null){
			
			if(!StringUtils.isBlank(override.getColl()))
				prod.setColl(override.getColl());
			
			if(override.getCategory() != null && override.getCategory().size() > 0){
				
				Set<String> cats = new TreeSet<String>();
				cats.addAll(override.getCategory());
				cats.addAll(prod.getCategory());
				
				prod.setCategory(new ArrayList<String>(cats));
			}
			
			if(override.getStyle() != null && override.getStyle().size() > 0){
				
				Set<String> styles = new TreeSet<String>();
				styles.addAll(override.getStyle());
				styles.addAll(prod.getStyle());
				
				prod.setStyle(new ArrayList<String>(styles));
			}
			
			if(override.getColor() != null && override.getColor().size() > 0){
				
				Set<String> colors = new TreeSet<String>();
				colors.addAll(override.getColor());
				colors.addAll(prod.getColor());
				
				prod.setColor(new ArrayList<String>(colors));
			}
			
			if(override.getBrand() != null && override.getBrand().size() > 0){
				
				Set<String> brands = new TreeSet<String>();
				brands.addAll(override.getBrand());
				brands.addAll(prod.getBrand());
				
				prod.setBrand(new ArrayList<String>(brands));
			}
			
			if(!StringUtils.isBlank(override.getGender()))
				prod.setGender(override.getGender());
			
			if(override.isGiftable())
				prod.setGiftable(true);
			
			if(override.getRelationship() != null && override.getRelationship().size() > 0){
				
				Set<String> relations = new TreeSet<String>();
				relations.addAll(override.getRelationship());
				relations.addAll(prod.getRelationship());
				
				prod.setRelationship(new ArrayList<String>(relations));
			}
			
			if(override.getOcassion() != null && override.getOcassion().size() > 0){
				
				Set<String> ocassions = new TreeSet<String>();
				ocassions.addAll(override.getOcassion());
				ocassions.addAll(prod.getOcassion());
				
				prod.setOcassion(new ArrayList<String>(ocassions));
			}
			
			if(override.getInterest() != null && override.getInterest().size() > 0){
				
				Set<String> interests = new TreeSet<String>();
				interests.addAll(override.getInterest());
				interests.addAll(prod.getInterest());
				
				prod.setInterest(new ArrayList<String>(interests));
			}
			
			if(override.getMood() != null && override.getMood().size() > 0){
				
				Set<String> moods = new TreeSet<String>();
				moods.addAll(override.getMood());
				moods.addAll(prod.getMood());
				
				prod.setMood(new ArrayList<String>(moods));
			}
			
			if(!StringUtils.isBlank(override.getAgeRange()))
				prod.setAgeRange(override.getAgeRange());
		}
	}

	protected void generateXML(List<HTMLProduct> prods) throws Exception{
		
		String prodXML = ParserXMLUtils.getProductXML(prods, merchantId, merchantName, merchantUrl); 
		
		String outputDir = baseDir + File.separator + merchantName ; 
		File f = new File(outputDir);
		if (!f.exists()) 
		{
			f.mkdir();
			System.out.println("Output Directory Created - " + f.toString());
		}
		String xmlFile = outputDir + File.separator + merchantName + "_" + Math.abs(collUrl.hashCode()) + ".xml";
		
		PrintStream out = new PrintStream(new FileOutputStream(xmlFile));
		out.print(prodXML);
		out.close();
		
		System.out.println("XML Saved : " + xmlFile);
	}
	
}
