package pojo;

import lombok.Value;

@Value
public class Category {
	int id;
	String title;
	Category[] subcategories;
}
