package pojo;

import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validators {

	final String REGEX_EMAIL = "^[\\w\\.]+@[\\w\\.]+\\.[\\w]{2,6}$";
	
	public boolean isEmail(String s) {
        return Pattern.compile(REGEX_EMAIL)
        				.matcher(s)
        				.find();
	}
	
}
