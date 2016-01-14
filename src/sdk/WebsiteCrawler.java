package sdk;

import java.util.List;
import java.util.Map.Entry;

public interface WebsiteCrawler {

	public abstract List<String> getAllProductUrlsForCollection(String url) throws Exception;
	
	public abstract List<Entry<String, Integer>> getAllCollectionUrlsForSite(String url) throws Exception;
}
