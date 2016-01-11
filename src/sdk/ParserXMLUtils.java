package sdk;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;

public class ParserXMLUtils {

	public static String getProductXML(List<HTMLProduct> prods, String merchantId, String merchantName, String merchantUrl) throws Exception{
		
		if(StringUtils.isBlank(merchantId))
			throw new Exception("Merchant Id is blank");
		
		if(StringUtils.isBlank(merchantName))
			throw new Exception("Merchant Name is blank");
		
		if(StringUtils.isBlank(merchantUrl))
			throw new Exception("Merchant Url is blank");
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<partner id=\"" + merchantId + "\" name=\"" + merchantName + "\" url=\"" + merchantUrl + "\">\n");
		sb.append("<products>\n");
		
		for (HTMLProduct prod : prods) {
			
			sb.append("<product sku=\"\">\n");
			
			sb.append("<basic>\n");
			
			sb.append(appendToTag("name", prod.getName()));
			if(prod.getDesc() != null && prod.getDesc().length() > 999)
				prod.setDesc(prod.getDesc().substring(0, 999));
			sb.append(appendToTag("description", prod.getDesc().replaceAll("\\;", "")));
			sb.append(appendToTagAsIs("url", prod.getUrl()));
			sb.append(appendToTag("price", prod.getPrice() + ""));
			sb.append(appendToTag("discountPrice", prod.getDiscountedPrice() + ""));
			sb.append(appendToTag("imageUrl", prod.getSrcUrlOfImage()));
			sb.append(appendToTag("collectionName", prod.getColl()));
			sb.append(appendToTag("category", prod.getCategory()));
			
			sb.append("</basic>\n");
			
			sb.append("<specific>\n");
			sb.append(appendToTag("brand", prod.getBrand()));
			sb.append(appendToTag("style", prod.getStyle()));
			sb.append(appendToTag("color", prod.getColor()));
			
			String price = prod.getPrice() + "";
			if(prod.getDiscountedPrice() > 0)
				price = prod.getDiscountedPrice() + "";
			
			sb.append(appendToTag("priceRange", getPriceRangeForPrice(price)));
			
			sb.append("</specific>\n");
			
			/*
			 * <gift>	
			 * 		<isGift>Y/N</isGift>
			 * 		<ocassion></ocassion>
			 * 		<gender></gender>
			 * 		<ageRange></ageRange>
			 * 		<mood></mood>
			 * 		<interest></interest>
			 * 		<relation></relation>
			 * </gift>
			 */
			sb.append("<gift>\n");
			
			if(prod.isGiftable())
				sb.append("<isgift>Y</isgift>\n");
			else
				sb.append("<isgift>N</isgift>\n");
			
			sb.append(appendToTag("ocassion", prod.getOcassion()));
			sb.append(appendToTag("gender", prod.getGender()));
			sb.append(appendToTag("ageRange", prod.getAgeRange()));
			
			sb.append(appendToTag("mood", prod.getMood()));
			sb.append(appendToTag("interest", prod.getInterest()));
			sb.append(appendToTag("relation", prod.getRelationship()));
			
			sb.append("</gift>\n");
			
			sb.append("</product>\n");
		}
		
		
		sb.append("</products>\n");
		sb.append("</partner>\n");
		
		return sb.toString();
		
	}
	
	public static String getPriceRangeForPrice(String price){
		
		String allPriceRanges = "1000-2500,2500-5000,250-500,5000-10000,500-1000,0-250,10000-10000000";
		String priceRange ="";
		
		try{
			float priceInFloat = Float.parseFloat(price);
			String[] splitPrice = allPriceRanges.split("\\,");
			for(int i=0;i<splitPrice.length;i++){
				String oneSplit = splitPrice[i];
				String leftItem = oneSplit.split("\\-")[0];
				String rightItem = oneSplit.split("\\-")[1];
				long leftItemInInt = Long.parseLong(leftItem);
				long rightItemInInt = Long.parseLong(rightItem);
				if(priceInFloat<=rightItemInInt && priceInFloat>leftItemInInt){
					priceRange = oneSplit;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return priceRange;
	}
	
	public static StringBuffer appendToTag(String tagName,String tagValue){
		
		StringBuffer sb = new StringBuffer();
		sb.append("<");
		sb.append(cleanHTML(tagName));
		sb.append(">");
		
		if(!StringUtils.isBlank(tagValue))
			sb.append(cleanHTML(tagValue).replaceAll("&","&amp;").trim());
		
		sb.append("</");
		sb.append(cleanHTML(tagName));
		sb.append(">\n");
		return sb;
	}

	public static StringBuffer appendToTagAsIs(String tagName,String tagValue){
		
		StringBuffer sb = new StringBuffer();
		sb.append("<");
		sb.append(tagName);
		sb.append(">");
		
		if(!StringUtils.isBlank(tagValue))
			sb.append(tagValue.trim());
		
		sb.append("</");
		sb.append(tagName);
		sb.append(">\n");
		return sb;
	}
	
	public static StringBuffer appendToTag(String tagName,List<String> tagValues){
		StringBuffer sb = new StringBuffer();
		sb.append("<");
		sb.append(cleanHTML(tagName));
		sb.append(">");
		sb.append(convertListOfStringsToCommaSeparated(tagValues).replaceAll("&","&amp;"));
		sb.append("</");
		sb.append(cleanHTML(tagName));
		sb.append(">\n");
		return sb;
	}
	
	public static String cleanHTML(String html){
		if(StringUtils.isBlank(html))
			return "";
		
		html = html.replaceAll(" & ", " n ");
		
		return StringEscapeUtils.unescapeHtml(Jsoup.parse(html).text()).replaceAll("[^\\x20-\\x7e]", "");
	}
	
	public static String convertListOfStringsToCommaSeparated(List<String> strings){
		if(strings == null || strings.size() == 0)
			return "";

		StringBuffer sb = new StringBuffer();

		for (String string : strings) {
			sb.append(cleanHTML(string).trim().toLowerCase());
			sb.append(",");
		}

		// Remove last comma and return
		return sb.toString().substring(0, sb.length() - 1);
	}

}
