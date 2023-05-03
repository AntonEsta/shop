package pojo;

import lombok.Value;

@Value
public class CartItem {
	int id;
	Good good;
	int count;
}
