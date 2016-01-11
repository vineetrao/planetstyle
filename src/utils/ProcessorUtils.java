package utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * Utility methods for processing
 * 
 * @author Mrigank Shekhar
 *
 */
public class ProcessorUtils {

	private static String SERVER_IDENTIFIER;

	/**
	 * Common method for generating Unique Identifiers
	 * 
	 * @return
	 */
	public static String generateUUID(){

		if(StringUtils.isEmpty(SERVER_IDENTIFIER)){
			Properties properties = new Properties();
			InputStream inputStream = null;
			String serverIdentifierFromFile = null;

			try{
				inputStream = ClassLoader.getSystemResourceAsStream("config.properties"); 
				properties.load(inputStream);
				serverIdentifierFromFile = properties.getProperty("server.identifier");
			}catch(Exception e){

			}

			if (StringUtils.isEmpty(serverIdentifierFromFile)){
				Random r = new Random( System.currentTimeMillis() );
				int num = Math.abs(r.nextInt() % 100);
				if (num<10)
					SERVER_IDENTIFIER = "0" + num;
				else
					SERVER_IDENTIFIER = "" + num;
			}
			else
				SERVER_IDENTIFIER = serverIdentifierFromFile;
		}

		return SERVER_IDENTIFIER + "-" + UUID.randomUUID().toString();
	}

	/**
	 * Increments a String by 1
	 * 
	 * @param s
	 * @return
	 */
	public static String incrementStringValue(String s){
		if(StringUtils.isEmpty(s))
			return "1";

		int newVal = (new Integer(s.trim())).intValue() + 1;
		return new String("" + newVal);
	}

	/**
	 * Decrements a String by 1
	 * NOTE: If value is less than O, then return 0.
	 * 
	 * @param s
	 * @return
	 */
	public static String decrementStringValue(String s){
		if(StringUtils.isEmpty(s))
			return "0";

		int newVal = (new Integer(s.trim())).intValue() - 1;
		if(newVal < 0)
			newVal = 0;
		return new String("" + newVal);
	}

	/**
	 * Adds a String which is a number to a String which is also a number
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String addStringToString(String s1, String s2){
		if(StringUtils.isEmpty(s1) && StringUtils.isEmpty(s2))
			return "0";

		if(StringUtils.isEmpty(s1))
			return s2;

		if(StringUtils.isEmpty(s2))
			return s1;

		int newVal = (new Integer(s1)).intValue() + (new Integer(s2)).intValue();
		return new String("" + newVal);
	}


	/**
	 * Adds a String which is a number to a String 
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String addStringToStringAsCommaSeperated(String s1, String s2){
		if(StringUtils.isEmpty(s1) && StringUtils.isEmpty(s2))
			return "";

		if(StringUtils.isEmpty(s1))
			return s2;

		if(StringUtils.isEmpty(s2))
			return s1;

		return new String(s1 + "," +s2);
	}

	/**
	 * Substracts a String which is a number from a String which is also a number
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String substractStringFromString(String s1, String s2){
		if(StringUtils.isEmpty(s1) && StringUtils.isEmpty(s2))
			return "0";

		if(StringUtils.isEmpty(s1))
			return "-" + s2;

		if(StringUtils.isEmpty(s2))
			return s1;

		int newVal = (new Integer(s1)).intValue() - (new Integer(s2)).intValue();
		return new String("" + newVal);
	}

	/**
	 * Converts a collection of Strings to a comma separated String
	 * 
	 * @param strings
	 * @return
	 */
	public static String convertListOfStringsToCommaSeparated(List<String> strings){
		if(strings == null || strings.size() == 0)
			return "";

		StringBuffer sb = new StringBuffer();

		for (String string : strings) {
			sb.append(string);
			sb.append(",");
		}

		// Remove last comma and return
		return sb.toString().substring(0, sb.length() - 1);
	}
	
	/**
	 * Converts a collection of Strings to a comma separated String
	 * 
	 * @param strings
	 * @return
	 */
	public static String convertSetOfStringsToCommaSeparated(Set<String> strings){
		if(strings == null || strings.size() == 0)
			return "";

		StringBuffer sb = new StringBuffer();

		for (String string : strings) {
			sb.append(string);
			sb.append(",");
		}

		// Remove last comma and return
		return sb.toString().substring(0, sb.length() - 1);
	}
	
	/**
	 * Converts a collection of Strings to a Pipe separated String
	 * 
	 * @param strings
	 * @return
	 */
	public static String convertListOfStringsToPipeSeparated(List<String> strings){
		if(strings == null || strings.size() == 0)
			return "";

		StringBuffer sb = new StringBuffer();

		for (String string : strings) {
			sb.append(string);
			sb.append("|");
		}

		// Remove last comma and return
		return sb.toString().substring(0, sb.length() - 1);
	}

	/**
	 * Converts a comma separated list of String to a List of String objects
	 * 
	 * @param string
	 * @return
	 */
	public static List<String> convertCommaSeparatedListToStringCollection(String string){
		List<String> strings = new ArrayList<String>();
		if(StringUtils.isEmpty(string))
			return strings;

		String[] strArray = string.split(",");
		for (String strVal : strArray) {
			strings.add(strVal);
		}
		return strings;
	}


	public static List<String> convertCommaSeparatedListToStringCollectionInLoweCase(String string){
		List<String> strings = new ArrayList<String>();
		if(StringUtils.isEmpty(string))
			return strings;

		String[] strArray = string.split(",");
		for (String strVal : strArray) {
			strings.add(strVal.toLowerCase());
		}
		return strings;
	}

	/**
	 * Converts a comma separated list of String, List to a Map of List objects
	 * 
	 * Example:
	 * The String "shoes:a|b|c|d,books:a|e|f|g" gets converted to a Map whose
	 * - Keys are (shoes, books)
	 * - Values is a List of String
	 * - For shoes, values is a list of String having a,b,c,d as entries
	 * - For books, values is a list of String having a,e,f,g as entries 
	 * 
	 * @param string
	 * @return
	 */
	public static Map<String, List<String>> convertCommaSeparatedListToMap(String string){

		Map<String, List<String>> lists = new HashMap<String, List<String>>();

		if(StringUtils.isEmpty(string))
			return lists;

		String[] strArray = string.split(",");
		for (String strVal : strArray) {
			String[] listKeyValuePairs = strVal.split(":");

			if(listKeyValuePairs != null && listKeyValuePairs.length == 2){
				String key = listKeyValuePairs[0];
				String values = listKeyValuePairs[1];
				lists.put(key, convertCommaSeparatedListToStringCollection(values));
			}
		}
		return lists;
	}

	/**
	 * Converts a Map of List objects to comma separated list of String, List
	 * 
	 * Example:
	 * A Map with
	 * - Keys are (shoes, books)
	 * - Values is a List of String
	 * - For shoes, values is a list of String having a,b,c,d as entries
	 * - For books, values is a list of String having a,e,f,g as entries
	 * 
	 * gets converted to "shoes:a|b|c|d,books:a|e|f|g"
	 *  
	 * 
	 * @param string
	 * @return
	 */
	public static String convertMapToCommaSeparatedString(Map<String, List<String>> lists){

		StringBuffer string = new StringBuffer();
		if(lists == null || lists.size() == 0)
			return string.toString();

		Set<String> keys = lists.keySet();
		if (keys == null || keys.size() == 0)
			return string.toString();

		for (String key : keys) {
			List<String> values = lists.get(key);
			if(values == null || values.size() == 0)
				continue;

			// Now add the entry to the Map
			string.append(key);
			string.append(":");
			string.append(convertListOfStringsToPipeSeparated(values));
			string.append(",");
		}

		if(string.indexOf(",") > 0)
			return string.toString().substring(0, string.length() - 1);
		else
			return string.toString();
	}

//	public static String prepareWishlistedByUserDetails(Transmit clientReq) {
//		if(clientReq.getUser().getLoginVia().equalsIgnoreCase(User.LOGIN_VIA_GOOGLE)){
//			return prepareWishlistedByUserDetails("G"+clientReq.getUser().getUserName()+"&&"+clientReq.getUser().getFbId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//		else if(clientReq.getUser().getLoginVia().equalsIgnoreCase(User.LOGIN_VIA_ALLMEMOIRS)){
//			return prepareWishlistedByUserDetails("M&&"+clientReq.getUser().getFbId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		} 
//		else{
//			return prepareWishlistedByUserDetails(clientReq.getUser().getId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//	}
//
//	public static String prepareWishlistedByUserDetails(String userId, String userFullName, String userName) {
//		return userId + "|" + userFullName + "|" + userName + "|" + System.currentTimeMillis();
//	}
//
//	public static String prepareAcquiredByUserDetails(Transmit clientReq) {
//		if(clientReq.getUser().getLoginVia().equalsIgnoreCase(User.LOGIN_VIA_GOOGLE)){
//			return prepareWishlistedByUserDetails("G"+clientReq.getUser().getUserName()+"&&"+clientReq.getUser().getFbId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//		else if(clientReq.getUser().getLoginVia().equalsIgnoreCase(User.LOGIN_VIA_ALLMEMOIRS)){
//			return prepareWishlistedByUserDetails("M&&"+clientReq.getUser().getFbId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//		else{
//			return prepareAcquiredByUserDetails(clientReq.getUser().getId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//	}

	public static String prepareAcquiredByUserDetails(String userId, String userFullName, String userName) {
		return userId + "|" + userFullName + "|" + userName + "|" + System.currentTimeMillis();
	}

//	public static String prepareShortlistedByUserDetails(Transmit clientReq) {
//		if(clientReq.getUser().getLoginVia().equalsIgnoreCase(User.LOGIN_VIA_GOOGLE)){
//			return prepareWishlistedByUserDetails("G"+clientReq.getUser().getUserName()+"&&"+clientReq.getUser().getFbId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//		else if(clientReq.getUser().getLoginVia().equalsIgnoreCase(User.LOGIN_VIA_ALLMEMOIRS)){
//			return prepareWishlistedByUserDetails("M&&"+clientReq.getUser().getFbId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//		else{
//			return prepareAcquiredByUserDetails(clientReq.getUser().getId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//	}
//
//	public static String prepareShortlistedByUserDetails(String userId, String userFullName, String userName) {
//		return userId + "|" + userFullName + "|" + userName + "|" + System.currentTimeMillis();
//	}
//	
//	public static String prepareNewLikeByUserDetails(Transmit clientReq) {
//		if(clientReq.getUser().getLoginVia().equalsIgnoreCase(User.LOGIN_VIA_GOOGLE)){
//			return prepareNewLikesByUserDetails("G"+clientReq.getUser().getUserName()+"&&"+clientReq.getUser().getFbId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//		else if(clientReq.getUser().getLoginVia().equalsIgnoreCase(User.LOGIN_VIA_ALLMEMOIRS)){
//			return prepareNewLikesByUserDetails("M&&"+clientReq.getUser().getFbId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//		else{
//			return prepareNewLikesByUserDetails(clientReq.getUser().getId(), clientReq.getUser().getFullName(), clientReq.getUser().getUserName());
//		}
//	}

	public static String prepareNewLikesByUserDetails(String userId, String userFullName, String userName) {
		return userId + "|" + userFullName + "|" + userName + "|" + System.currentTimeMillis();
	}

	
	/**
	 * Note - fb id in case of google is the image
	 * @param wishGiftAcquiredString
	 * @return
	 */
	public static String getFBIdForImage(String wishGiftAcquiredString){
		if(wishGiftAcquiredString==null){
			return null;
		}
		if(wishGiftAcquiredString.startsWith("G") || wishGiftAcquiredString.startsWith("M")){
			String[] split = wishGiftAcquiredString.split("\\|");
			String imageText = split[0];
			//split with @@
			String[] splitImg = imageText.split("&&");
			return splitImg[1];
		}
		else{
			return getUserNameFromWishlistedByUserDetails(wishGiftAcquiredString);
		}
	}
	
	public static String getUserFullNameForImage(String wishGiftAcquiredString){
		if(wishGiftAcquiredString==null){
			return null;
		}
		String[] split = wishGiftAcquiredString.split("\\|");
		if(split.length > 0)
			return split[1];
		
		return "";
	}
	
	public static String getDateFromShortlistedByUserDetails(String shortlistedString){
		if(shortlistedString==null){
			return null;
		}
		
		String[] split = shortlistedString.split("\\|");
		if(split.length>3){
			return split[3];
		}
		return null;
	}
	
	public static String getUserNameFromWishlistedByUserDetails(String wishlistedString){
		if(wishlistedString==null){
			return null;
		}
		String[] split = wishlistedString.split("\\|");
		if(split.length>2){
			return split[2];
		}
		return null;
	}
	
	public static String getDateFromWishlistedByUserDetails(String wishlistedString){
		if(wishlistedString==null){
			return null;
		}
		String[] split = wishlistedString.split("\\|");
		if(split.length>3){
			return split[3];
		}
		return null;
	}
	
	public static boolean checkIfWishlistedByUserDetailsContainsUsername(List<String> wishlistedByUserDetails, String userName){
		for (String wishlistedByUserDetail : wishlistedByUserDetails) {
			if (wishlistedByUserDetail.contains(userName))
				return true;
		}
		return false;
	}

	public static boolean checkIfLikeListContainsUsername(String usersWhoLike, String userName){
		if(StringUtils.isBlank(usersWhoLike))
			return false;

		return usersWhoLike.contains(userName);
	}

	public static boolean checkIfAcquiredByUserDetailsContainsUsername(List<String> acquiredByUserDetails, String userName){
		for (String acquiredByUserDetail : acquiredByUserDetails) {
			if (acquiredByUserDetail.contains(userName))
				return true;
		}
		return false;
	}

//	public static boolean checkIfProductSame(Product src, Product dest){
//		if(src == null || dest == null)
//			return false;
//		if(src.getId().equalsIgnoreCase(dest.getId()))
//			return true;
//		if(src.getName().equalsIgnoreCase(dest.getName()))
//			return true;
//		return false;
//	}
//
//	public static boolean checkIfContainsProduct(List<Product> srcProds, Product dest){
//		if(srcProds == null || dest == null)
//			return false;
//
//		for (Product product : srcProds) {
//			if(checkIfProductSame(product, dest))
//				return true;
//		}
//		return false;
//	}
//
//	public static List<Product> removeDuplicates(List<Product> original){
//		if(original == null || original.isEmpty())
//			return new LinkedList<Product>();
//
//		List<Product> pruned = new LinkedList<Product>();
//		for (Product product : original) {
//			if(!checkIfContainsProduct(pruned, product))
//				pruned.add(product);
//		}
//
//		return pruned;
//	}
//
//	public static boolean checkIfMerchantSame(Merchant src, Merchant dest){
//		if(src == null || dest == null)
//			return false;
//		if(src.getId().equalsIgnoreCase(dest.getId()))
//			return true;
//		if(src.getName().equalsIgnoreCase(dest.getName()))
//			return true;
//		return false;
//	}
//
//	public static boolean checkIfContainsMerchant(List<Merchant> srcMerchants, Merchant dest){
//		if(srcMerchants == null || dest == null)
//			return false;
//
//		for (Merchant merchant : srcMerchants) {
//			if(checkIfMerchantSame(merchant, dest))
//				return true;
//		}
//		return false;
//	}
	
	public static List<String> getIDsForPage(List<String> allIds, int pageNum, int pageSize, int totalProds){
		
		if(allIds==null || allIds.size()==0)
			return new ArrayList<String>();
		
		int totalPages = (int)Math.ceil((double)totalProds/(double)pageSize);
		
		if(pageNum >= totalPages)
			return new ArrayList<String>();
		
		int startIndex = pageNum * pageSize;
		int endIndex = startIndex + pageSize;
		if(endIndex > totalProds)
			endIndex = totalProds;
		
		return allIds.subList(startIndex, endIndex);
	}
	
	public static List<?> getObjectsForPage(List<?> allIds, int pageNum, int pageSize, int totalProds){
		
		if(allIds==null || allIds.size()==0)
			return new ArrayList<String>();
		
		int totalPages = (int)Math.ceil((double)totalProds/(double)pageSize);
		
		if(pageNum >= totalPages)
			return new ArrayList<String>();
		
		int startIndex = pageNum * pageSize;
		int endIndex = startIndex + pageSize;
		if(endIndex > totalProds)
			endIndex = totalProds;
		
		return allIds.subList(startIndex, endIndex);
	}

	/**
	 * IMPORTANT: Keep in mind that this adds 5 queries to the whole add process
	 * This is an area to optimize for Add Operations
	 * The trade off is client processing vs Server Queries
	 * 
	 * @param clientReq
	 * @param response
	 * @param holderMap
	 * @throws Exception
	 *//*
	public static void reloadUserOnly(Transmit clientReq,Transmit response,Map<String, String> holderMap) throws Exception{
		User user = new User();
		FinderService fs = new FinderService();
		String userName = clientReq.getUser().getUserName();
		user.setUserName(userName);
		user.setSetting(fs.findSettingsForUser(userName));
		user.setColls(fs.findCollectionsForUser(userName));
		user.setProds(fs.findProductsForUser(userName));
		user.setShops(fs.findShopsForUser(userName));
		// Do we need to refresh Friends for User here??? It is an extra call
		user.setFriends(fs.findFriendsForUser(userName));
		response.setUser(user);


	  * IMPORTANT: If we return this User Object, then the Feed is not set, should we update the feed as well?
	  * So either the client handles this fact or we populate the Feed after fetching from DB
	  * If we fetch the feed from DB, then it becomes an OnLoad request, which is very heavy  
	  * 
	  * Let the client take care of it. Throwing an Exception below so that it does miss review.
	  * 

		throw new UnsupportedOperationException("Incomplete Logic in this method, should we populate feed object as well?");
	}*/

	public static void main(String[] args) throws Exception{

		System.out.println(getUserNameFromWishlistedByUserDetails("01-82fb7baa-31a5-4357-8e92-4cf00ce2215c|Mrigank Shekhar|666010675|1371901827355"));

		System.out.println(convertCommaSeparatedListToMap("shoes:a|b|c|d,books:a|e|f|g"));
		System.out.println(convertCommaSeparatedListToMap(""));
		System.out.println(convertCommaSeparatedListToMap(null));
		System.out.println(convertCommaSeparatedListToMap("shoes:,books:a|e|f|g"));
		System.out.println(convertCommaSeparatedListToMap("shoes:a|b|c|d,books"));
		System.out.println(convertCommaSeparatedListToMap("shoes:,books"));

		System.out.println("DONE");
		System.out.println(convertMapToCommaSeparatedString(convertCommaSeparatedListToMap("shoes:a|b|c|d,books:a|e|f|g")));
		System.out.println(convertMapToCommaSeparatedString(convertCommaSeparatedListToMap("")));
		System.out.println(convertMapToCommaSeparatedString(convertCommaSeparatedListToMap(null)));
		System.out.println(convertMapToCommaSeparatedString(convertCommaSeparatedListToMap("shoes:,books:a|e|f|g")));
		System.out.println(convertMapToCommaSeparatedString(convertCommaSeparatedListToMap("shoes:a|b|c|d,books")));
		System.out.println(convertMapToCommaSeparatedString(convertCommaSeparatedListToMap("shoes:,books")));
		System.out.println(convertMapToCommaSeparatedString(null));

		//System.out.println(generateUUID());

		/*List<String> strings = null;
		System.out.println(convertListOfStringsToCommaSeparated(strings));

		strings = new ArrayList<String>();

		strings.add("One");
		System.out.println(convertListOfStringsToCommaSeparated(strings));

		strings.add("Two");
		strings.add("Three");
		strings.add("Four");
		System.out.println(convertListOfStringsToCommaSeparated(strings));

		System.out.println("-------------------------------------");

		String string = null;
		System.out.println(convertCommaSeparatedListToStringCollection(string));

		string = "";
		System.out.println(convertCommaSeparatedListToStringCollection(string));

		string = "One";
		System.out.println(convertCommaSeparatedListToStringCollection(string));

		string = "One,Two,Three,Four";
		System.out.println(convertCommaSeparatedListToStringCollection(string));

		string = "One,Two,Three,Four,";
		System.out.println(convertCommaSeparatedListToStringCollection(string));

		System.out.println("-------------------------------------");*/

	}

}
