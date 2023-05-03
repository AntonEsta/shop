package pojo;

import lombok.Value;

@Value
public class CartCardView {
	String id;
	String title;
	String info;
	String price;
	String image;
	String count;
}
