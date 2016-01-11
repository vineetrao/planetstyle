package sdk;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import utils.ProcessorUtils;

public class ProductXMLValidator {

	public static final String baseDir = "/Users/mrigankshekhar/Desktop/work/oneTimeTasks/data/prodXMLs";
	
	public static final String folderName = "eindianaugust"; 
	
	public static void main(String[] args){
		
		File folder = new File(baseDir + "/" + folderName);
		
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			
			if (file.isFile() && file.getName().endsWith(".xml")) {
		    	try{
			    	int count = 0;
			    	Set<String> uniqueCats = new TreeSet<String>();
					Set<String> uniqueColor = new TreeSet<String>();
					Set<String> uniqueColls = new TreeSet<String>();
					Set<String> uniqueStyles = new TreeSet<String>();
					Set<String> uniqueBrands = new TreeSet<String>();
					Set<String> uniqueGender = new TreeSet<String>();
					
			    	SAXBuilder builder = new SAXBuilder();
			    	
			    	Document document = (Document) builder.build(file);
					Element rootNode = document.getRootElement();
					
					String merchantId =rootNode.getAttributeValue("id");
					String merchantName =rootNode.getAttributeValue("name");
					String merchantUrl = rootNode.getAttributeValue("url");
					
					Element productsElement = rootNode.getChild("products");
					List list = productsElement.getChildren("product");
					
					for (int i = 0; i < list.size(); i++) {
						count++;
						
						Element prodNote = (Element) list.get(i);
						Element basic = prodNote.getChild("basic");
						Element specific = prodNote.getChild("specific");
						Element gift = prodNote.getChild("gift");
	
	
						String collectionName = basic.getChildText("collectionName");
						if(!StringUtils.isBlank(collectionName))
							uniqueColls.addAll(ProcessorUtils.convertCommaSeparatedListToStringCollection(collectionName));
						
						String category = basic.getChildText("category");
						if(!StringUtils.isBlank(category))
							uniqueCats.addAll(ProcessorUtils.convertCommaSeparatedListToStringCollection(category));
						
						String brand = specific.getChildText("brand");
						if(!StringUtils.isBlank(brand))
							uniqueBrands.addAll(ProcessorUtils.convertCommaSeparatedListToStringCollection(brand));
						
						String color = specific.getChildText("color");
						if(!StringUtils.isBlank(color))
							uniqueColor.addAll(ProcessorUtils.convertCommaSeparatedListToStringCollection(color));
						
						String style = specific.getChildText("style");
						if(!StringUtils.isBlank(style))
							uniqueStyles.addAll(ProcessorUtils.convertCommaSeparatedListToStringCollection(style));
						
						String gender = gift.getChildText("gender");
						if(!StringUtils.isBlank(gender))
							uniqueGender.addAll(ProcessorUtils.convertCommaSeparatedListToStringCollection(gender));
					}
					
					System.out.println("-----------------------");
					System.out.println("FileName : " + file.getName());
					System.out.println("Merchant Id : " + merchantId);
					System.out.println("Merchant Name : " + merchantName);
					System.out.println("Merchant URL : " + merchantUrl);
					System.out.println("Total URLs: " + count);
					System.out.println("Unique cats: " + uniqueCats);
					System.out.println("Unique color: " + uniqueColor);
					System.out.println("Unique collections: " + uniqueColls);
					System.out.println("Unique styles: " + uniqueStyles);
					System.out.println("Unique brands: " + uniqueBrands);
					System.out.println("Unique Gender: " + uniqueGender);
		    	}
		    	catch(Exception e){
		    		System.out.println("##### ERROR IN : " + file.getName());
		    		e.printStackTrace();
		    	}
		    }
		}
	}
}
