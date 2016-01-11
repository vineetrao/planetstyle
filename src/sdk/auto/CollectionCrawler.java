package sdk.auto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;

import utils.ProcessorUtils;

public class CollectionCrawler {

	private static final Logger log = LogManager.getLogger(CollectionCrawler.class);

	public static int MAX_URLS_IN_CONFIG = 50;

	private Properties properties;
	private List<String> cats;
	private String coll;
	private String gender;

	private List<String> urls;
	protected List<String> allowedCats = new ArrayList<String>();

	StringBuffer report = new StringBuffer();

	private String allCrawlFileListFileName;
	private String destPathFileName;

	public String getAllCrawlFileListFileName() {
		return allCrawlFileListFileName;
	}

	public void setAllCrawlFileListFileName(String allCrawlFileListFileName) {
		this.allCrawlFileListFileName = allCrawlFileListFileName;
	}

	public String getDestPathFileName() {
		return destPathFileName;
	}

	public void setDestPathFileName(String destPathFileName) {
		this.destPathFileName = destPathFileName;
	}
	/**
	 * Read the main file in allCrawlFileListFileName and get the names for the file...
	 */
	public void crawl(){
		try{
			File newFile = new File(allCrawlFileListFileName);
			FileReader fileRdr = new FileReader(newFile);
			BufferedReader bufRdr = new BufferedReader(fileRdr);
			String line = null;
			while ((line = bufRdr.readLine()) != null) {
				try{
					log.info("Reading for the file - " + line.trim());
					crawlCollection(line.trim(), destPathFileName);
					log.info("Reading for the file done- " + line.trim());
				}catch(Exception e){
					log.info("Reading for the file cannot be done- " + line.trim());
					log.warn(e.getMessage(),e);
				}
			}
			log.info("All files read and scheduled for crawling");
			bufRdr.close();
			fileRdr.close();
		}catch(Exception e){
			log.warn(e.getMessage(),e);
		}
	}

	/**
	 * Returns the report of the xml generation
	 * 
	 * @param collConfigFile
	 * @param destXMLPath
	 * @return
	 * @throws Exception
	 */
	public String crawlCollection(String collConfigFile, String destXMLPath) throws Exception{

		report.append("<p>----------------------------------------------------</p>\n");
		report.append("<p></p>\n");
		report.append("<p>Crawling : " + collConfigFile + "</p>\n");
		// Read contents of File
		File fileName = new File(collConfigFile);
		InputStream inputStream = new FileInputStream(fileName);
		properties = new Properties();
		properties.load(inputStream);

		// Read values one by one
		cats = ProcessorUtils.convertCommaSeparatedListToStringCollection(properties.getProperty("category"));
		coll = properties.getProperty("collection");
		gender = properties.getProperty("gender");
		urls = ProcessorUtils.convertCommaSeparatedListToStringCollection(properties.getProperty("urlList"));
		allowedCats = ProcessorUtils.convertCommaSeparatedListToStringCollection(properties.getProperty("allowedCats"));

		validateValues();

		report.append("<p>Category : " + cats + " , collection : " + coll + " , gender : " + gender + "</p>\n");
		report.append("<p>Total URLs for Category : " + urls.size() + "</p>\n");
		report.append("<p>List of URLs : " + urls + "</p>\n");
		report.append("<p><br<br></p>\n");

		// Crawl each URL one by one
		for (String url : urls) {
			report.append("<p>**************************</p>\n");
			report.append("<p>Crawling : " + url + "</p>\n");
			long start = System.currentTimeMillis();
			sendToDifferentSpacesForAsyncCrwaling(url, destXMLPath,cats,gender,coll,allowedCats);
			long end = System.currentTimeMillis();
			long time = (end-start)/1000;
			report.append("<p>time in Seconds : " + time + "</p>\n");

			Thread.sleep(2000);
			report.append("<p><br<br></p>\n");
		}


		report.append("<p>--------------- COMPLETE ------------</p>\n");
		inputStream.close();
		return report.toString();
	}


	private void sendToDifferentSpacesForAsyncCrwaling(String url,String destPath,List<String> categories,String gender,String coll,List<String> allowedCats){
		Map<String, Object> hashTable = new Hashtable<String, Object>();
		hashTable.put("URL", url);
		hashTable.put("DEST_PATH",destPath);
		hashTable.put("CATS", categories);
		hashTable.put("GENDER", gender);
		hashTable.put("COLL", coll);
		hashTable.put("ALLOWED_CATS", allowedCats);
		//GET THE space on the basis of a ranom number divisible by 4

		Random random = new Random();
		int number = random.nextInt();
		int count=number%4;;
		//while(entrySetIter.hasNext()){
		//Entry<String, List<ProductUrlAndIdMap>> entry = entrySetIter.next();
		String spaceName = null;
		String queurName = null;
		if(count==0){
			count = count+1;
			spaceName="CRAWL1";
			queurName="CRAWL1";
		}
		else if(count==1){
			count = count+1;
			spaceName="CRAWL2";
			queurName="CRAWL2";
		}
		else if(count==2){
			count = count+1;
			spaceName="CRAWL3";
			queurName="CRAWL4";
		}
		else{
			count = 0;
			spaceName="CRAWL4";
			queurName="CRAWL4";
		}
		Space sp = SpaceFactory.getSpace(spaceName);
		sp.out(queurName, hashTable);
		log.info("Added the url " + url+" into the psace for processing");
	}


	private void validateValues() throws Exception{
		if(cats == null || cats.size() <= 0)
			throw new Exception("Category is Blank in properties file.");
		if(StringUtils.isBlank(coll))
			throw new Exception("Collection is Blank in properties file.");
		//if(StringUtils.isBlank(gender) && !(coll.eq))
			//throw new Exception("Gender is Blank in properties file.");
		if(urls == null || urls.size() <= 0)
			throw new Exception("URL List is Blank in properties file.");

		if(urls.size() > MAX_URLS_IN_CONFIG)
			throw new Exception("URL List is more than allowed size : " + urls.size());

	}

	public static void main(String[] args) throws Exception{

		CollectionCrawler crawler = new CollectionCrawler();
		String finalReport = crawler.crawlCollection(
				"/Users/mrigankshekhar/Desktop/work/oneTimeTasks/cfg/women/accessories/belt.properties", 
				"/Users/mrigankshekhar/Desktop/work/oneTimeTasks/data/prodXMLs/auto");

		System.out.println(finalReport);
	}
}
