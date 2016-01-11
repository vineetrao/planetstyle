package sdk.auto;

import sdk.WebsiteCrawler;

public interface DailyWebsiteCrawler extends WebsiteCrawler{

	public static final String baseDir = "C:\\Code\\workspace\\data\\";
	
	public String getMerchantId();
	
	public String getMerchantName();
	
	public String getMerchantURL();
}
