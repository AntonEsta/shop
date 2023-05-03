package pojo;

import lombok.Value;

@Value
public class CatalogCardView {
	String image;
	String imageAlt;
	String brandName;
	String src;
	String price;
	String text;
	String buttonHref;
	String buttonTitle;
	boolean buttonVisible;
	
	public boolean isButtonVisible() {
		return buttonHref.isBlank() ? false : buttonVisible ;
	}
}
