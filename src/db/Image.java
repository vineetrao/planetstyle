package db;

/**
 * @author VineetR
 *
 */

public class Image {

	private String imageUrl;
	private String productUrl;
	private byte[] productImage;

	public Image() {
		// TODO Auto-generated constructor stub
	}

	public Image(String imageUrl, String productUrl, byte[] productImage) {
		super();
		this.imageUrl = imageUrl;
		this.productUrl = productUrl;
		this.productImage = productImage;
	}


	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}
}
