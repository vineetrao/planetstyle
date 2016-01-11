package sdk.auto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CrawlerRunningAnalysis {

	private static final Logger log = LogManager.getLogger(CrawlerRunningAnalysis.class);

	public static synchronized void logIntoAFile(String merchantName,String url,boolean didGetOneProduct,int sizeOfProdUrlsInThis,int noOfProductsPutIntoXML,int noOfProductsFailed){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateToday = sdf.format(new Date(System.currentTimeMillis()));
		String fileName = "/opt/adminapp/crawlerResult/cr_"+dateToday+".csv";
		String fileNameFailurUrls = "/opt/adminapp/crawlerResult/crFailedCollections_"+dateToday+".csv";
		File resultFile = new File(fileName);
		File resultFailureFile = new File(fileNameFailurUrls);
		if(!resultFile.exists()){
			OutputStream output = null;
			try {
				output = new FileOutputStream(fileName);
				PrintWriter q1 = new PrintWriter(output, true);
				q1.println("SHOPNAME=Total Collection Url Crawled|Total Collection Url Failed|Size Of Product URLS| No of Products Put in XML| No of Products Failed");
				q1.close();
				output.flush();
				output.close();
			}catch(Exception e){
				log.warn(e.getMessage(),e);
			}
		}
		if(!didGetOneProduct){
			if(!resultFile.exists()){
				OutputStream output = null;
				try {
					output = new FileOutputStream(resultFailureFile);
					PrintWriter q1 = new PrintWriter(output, true);
					q1.println("SHOPNAME=Failed URL");
					q1.close();
					output.flush();
					output.close();
				}catch(Exception e){
					log.warn(e.getMessage(),e);
				}
			}
		}

		if(didGetOneProduct){
			try{
				Properties daProps = new Properties();
				InputStream inputStream=new FileInputStream(fileName); 
				daProps.load(inputStream);
				String value = daProps.getProperty(merchantName.toUpperCase());
				if(value==null){
					value ="1|0|"+sizeOfProdUrlsInThis+"|"+noOfProductsPutIntoXML+"|"+noOfProductsFailed;
				}
				else{
					String[] split = value.split("\\|");
					int totalCollUrlCount = Integer.parseInt(split[0])+1;
					int totalCollUrlFailed = Integer.parseInt(split[1]);
					int totalSizeOfProdUrls = Integer.parseInt(split[2])+sizeOfProdUrlsInThis;
					int totalNumberPutInXml = Integer.parseInt(split[3])+noOfProductsPutIntoXML;
					int totalNumberFailed = Integer.parseInt(split[4])+noOfProductsFailed;
					value =totalCollUrlCount+"|"+totalCollUrlFailed+"|"+totalSizeOfProdUrls+"|"+totalNumberPutInXml+"|"+totalNumberFailed;
				}
				OutputStream output = null;
				try {
					output = new FileOutputStream(fileName);
					daProps.setProperty(merchantName.toUpperCase(), value);
					daProps.store(output, null);
					log.info("Updated count for "+ merchantName);
				}catch(Exception e){
					log.warn(e.getMessage(),e);
				}
			}catch(Exception e){
				log.warn(e.getMessage(),e);
			}

		}
		else{
			try
			{
			    FileWriter fw = new FileWriter(resultFailureFile,true); //the true will append the new data
			    fw.write(merchantName.toLowerCase()+"="+url+"\n");//appends the string to the file
			    fw.close();
			}
			catch(IOException ioe)
			{
			    log.warn("IOException: " + ioe.getMessage());
			}
		}
	}
	
	
	
}
