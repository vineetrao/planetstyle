/**
 * 
 */
package db;

import java.util.ArrayList;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import sdk.ParserXMLUtils;

/**
 * @author VineetR 
 * 
 * This reads all crawled product XML files.
 * 
 */

public class ProductXMLReader {

	public static final String baseDir = "C:\\Code\\workspace\\data\\";

	/**
	 * 
	 */

	public ProductXMLReader() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			File dataDir = new File(baseDir);
			File[] retailerDirs = dataDir.listFiles();
			for (File retailerDir : retailerDirs) {
				File[] productFiles = retailerDir.listFiles();
				for (File f : productFiles) {
					System.out.println("File: " + f.getName());
					Document doc = docBuilder.parse(f);
					doc.getDocumentElement().normalize();
					Node partner = doc.getElementsByTagName("partner").item(0);
					String retailer = partner.getAttributes().getNamedItem("name").getNodeValue();
					NodeList listOfProducts = doc.getElementsByTagName("product");
					
					for (int i = 0; i < listOfProducts.getLength(); i++) {

						Product p = new Product();
						Element productNode = (Element) listOfProducts.item(i);
					
						Element basic = (Element) (productNode.getElementsByTagName("basic")).item(0);
						p.setInsertOrder(i);
						p.setName(((Element) basic.getElementsByTagName("name").item(0)).getTextContent());
						p.setDescription(((Element) basic.getElementsByTagName("description").item(0)).getTextContent());
						p.setUrl(ParserXMLUtils.cleanHTML(((Element) basic.getElementsByTagName("url").item(0)).getTextContent()));
						p.setPrice(Float.parseFloat(((Element) basic.getElementsByTagName("price").item(0)).getTextContent()));
						p.setDiscountPrice(Float.parseFloat(((Element) basic.getElementsByTagName("discountPrice").item(0)).getTextContent()));
						p.setCategory(((Element) basic.getElementsByTagName("category").item(0)).getTextContent());
						p.setRetailer(retailer);
						p.setImageUrl(ParserXMLUtils.cleanHTML(((Element) basic.getElementsByTagName("imageUrl").item(0)).getTextContent()));
						p.setIsOutOfStock(false);

						Element specific = (Element) productNode.getElementsByTagName("specific").item(0);
						p.setBrand(((Element) specific.getElementsByTagName("brand").item(0)).getTextContent());
						p.setColor(((Element) specific.getElementsByTagName("color").item(0)).getTextContent());

						products.add(p);
					}
				}
				//break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Total Products : " + products.size());
		return products;
	}

	public static ArrayList<Product> getProductsFromXML(File f) {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
					System.out.println("File: " + f.getName());
					Document doc = docBuilder.parse(f);
					doc.getDocumentElement().normalize();
					Node partner = doc.getElementsByTagName("partner").item(0);
					String retailer = partner.getAttributes().getNamedItem("name").getNodeValue();
					NodeList listOfProducts = doc.getElementsByTagName("product");
					
					for (int i = 0; i < listOfProducts.getLength(); i++) {

						Product p = new Product();
						Element productNode = (Element) listOfProducts.item(i);
					
						Element basic = (Element) (productNode.getElementsByTagName("basic")).item(0);
						p.setInsertOrder(i);
						p.setName(((Element) basic.getElementsByTagName("name").item(0)).getTextContent());
						p.setDescription(((Element) basic.getElementsByTagName("description").item(0)).getTextContent());
						p.setUrl(ParserXMLUtils.cleanHTML(((Element) basic.getElementsByTagName("url").item(0)).getTextContent()));
						p.setPrice(Float.parseFloat(((Element) basic.getElementsByTagName("price").item(0)).getTextContent()));
						p.setDiscountPrice(Float.parseFloat(((Element) basic.getElementsByTagName("discountPrice").item(0)).getTextContent()));
						p.setCategory(((Element) basic.getElementsByTagName("category").item(0)).getTextContent());
						p.setRetailer(retailer);
						p.setImageUrl(ParserXMLUtils.cleanHTML(((Element) basic.getElementsByTagName("imageUrl").item(0)).getTextContent()));
						p.setIsOutOfStock(false);

						Element specific = (Element) productNode.getElementsByTagName("specific").item(0);
						p.setBrand(((Element) specific.getElementsByTagName("brand").item(0)).getTextContent());
						p.setColor(((Element) specific.getElementsByTagName("color").item(0)).getTextContent());

						products.add(p);
					}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Total Products : " + products.size());
		return products;
	}
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ProductXMLReader().getAllProducts();
	}

}
