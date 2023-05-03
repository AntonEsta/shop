package pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPrivelage {
	boolean admin;
	boolean guest;
	boolean client;
}
