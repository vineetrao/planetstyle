package sdk;

import java.util.List;

public interface WebsiteCrawler {

	public abstract List<String> getAllProductUrlsForCollection(String url) throws Exception;
	
	public abstract List<String> getAllCollectionUrlsForSite(String url) throws Exception;
}
