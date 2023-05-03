package models;

import java.util.Map.Entry;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

	public void showSystemProperties(){
		System.out.println("/nSystem properties:");
		java.util.Properties properties = System.getProperties();
    	for (Entry<Object,Object> property : properties.entrySet()) {
    		System.out.println(property.getKey() + " -> " + property.getValue());
    	}
	}
	
}
