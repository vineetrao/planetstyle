package sdk;

import java.util.ArrayList;
import java.util.List;

public class HTMLProduct {

	private String srcUrlOfImage;
	private String name;
	private String desc;
	private String url;
	private float price;
	private float discountedPrice;
	private String coll;
	private boolean isOutOfStock;
	
	private String gender;
	private List<String> brand = new ArrayList<String>();
	private List<String> category = new ArrayList<String>();
	private List<String> style = new ArrayList<String>();
	private List<String> color = new ArrayList<String>();
	
	private boolean isGiftable;
	private String ageRange;
	private List<String> ocassion = new ArrayList<String>();
	private List<String> mood = new ArrayList<String>();
	private List<String> interest = new ArrayList<String>();
	private List<String> relationship = new ArrayList<String>();
	
	//categories
	public static final int CATEGORY_UNKNOWN = 0;
	public static final int CATEGORY_BAGS = 1;
	public static final int CATEGORY_SHOES = 2;
	public static final int CATEGORY_CLOTHING = 3;
	public static final int CATEGORY_WATCHES = 4;
	
	
	public boolean isOutOfStock() {
		return isOutOfStock;
	}
	public void setOutOfStock(boolean isOutOfStock) {
		this.isOutOfStock = isOutOfStock;
	}
	public String getSrcUrlOfImage() {
		return srcUrlOfImage;
	}
	public boolean isGiftable() {
		return isGiftable;
	}
	public void setGiftable(boolean isGiftable) {
		this.isGiftable = isGiftable;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public List<String> getOcassion() {
		return ocassion;
	}
	public void setOcassion(List<String> ocassion) {
		this.ocassion = ocassion;
	}
	public List<String> getMood() {
		return mood;
	}
	public void setMood(List<String> mood) {
		this.mood = mood;
	}
	public List<String> getInterest() {
		return interest;
	}
	public void setInterest(List<String> interest) {
		this.interest = interest;
	}
	public List<String> getRelationship() {
		return relationship;
	}
	public void setRelationship(List<String> relationship) {
		this.relationship = relationship;
	}
	public void setSrcUrlOfImage(String srcUrlOfImage) {
		this.srcUrlOfImage = srcUrlOfImage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public String getColl() {
		return coll;
	}
	public void setColl(String coll) {
		this.coll = coll;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<String> getBrand() {
		return brand;
	}
	public void setBrand(List<String> brand) {
		this.brand = brand;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	public List<String> getStyle() {
		return style;
	}
	public void setStyle(List<String> style) {
		this.style = style;
	}
	public List<String> getColor() {
		return color;
	}
	public void setColor(List<String> color) {
		this.color = color;
	}
	
	
}
