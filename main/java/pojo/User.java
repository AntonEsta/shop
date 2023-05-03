package pojo;

import lombok.Value;

@Value
public class User {
	int id;
	String email;
	String name;
	String pwd;
}
