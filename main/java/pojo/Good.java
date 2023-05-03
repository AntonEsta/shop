package pojo;

import lombok.Value;

@Value
public class Good {
    int id;
    String title;
    int price;
    String info;
    String image;
}
