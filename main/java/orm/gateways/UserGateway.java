package orm.gateways;

import pojo.User;
import pojo.UserPermitionType;
import orm.ORM;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import controllers.Main;

@UtilityClass
public class UserGateway {

	private static final String TABLE_NAME = "users";

	public static User addUser(@NonNull String email, @NonNull String pass, @NonNull String name,
			@NonNull UserPermitionType permitionType) throws SQLException {
		var valuesMap = new HashMap<String, String>();
		valuesMap.put("email", email);
		valuesMap.put("pass", pass);
		valuesMap.put("name", name);
		if (ORM.getInstance().insert(TABLE_NAME, valuesMap) > 0) {
			var usr = findByEmail(email);
			if (usr != null) {
				setUserPermition(usr.getId(),permitionType);
			}
			return usr;
		}
		return null;
	}

	public static User[] getAllUsers() throws SQLException {
		var rs = ORM.getInstance().select(TABLE_NAME, new String[0], "");
		return getUsersFromResultSet(rs);
	}

	private static int setUserPermition(@NonNull int usrId, @NonNull UserPermitionType perm) throws SQLException {
		var permId = getPermitionTypeId(perm);
		if (permId != 0) {
			var values = new HashMap<String, String>(1);
			values.put("permition_id", "" + permId);
			if (ORM.getInstance().select("user_permition_relations", null, "where user_id=" + usrId).next()) {
				return ORM.getInstance().update("user_permition_relations", values, "where user_id=" + usrId);
			} else {
				values.put("user_id", "" + usrId);
				return ORM.getInstance().insert("user_permition_relations", values);
			}
		}
		return 0;
	}

	private int getPermitionTypeId(@NonNull UserPermitionType perm) throws SQLException {
    	var rs = ORM.getInstance().select("permition_types", new String[]{"permition_id"}, "where title='" + perm.toString().toLowerCase()+"'");
    	if (rs.next()) {
    		return rs.getInt(1);
    	}
    	return 0;
    }

	public static User findById(int usrId) throws SQLException {
		var rs = ORM.getInstance().select(TABLE_NAME, new String[0], "WHERE user_id =" + usrId);
		var users = getUsersFromResultSet(rs);
		if (users.length > 0) {
			return users[0];
		}
		return null;
	}

	private static User[] getUsersFromResultSet(@NonNull ResultSet rs) throws SQLException {
		var users = new ArrayList<>();
		while (rs.next()) {
			users.add(new User(rs.getInt("id"), rs.getString("email"), rs.getString("name"), rs.getString("pass")));
		}
		return users.toArray(new User[0]);
	}

	// TODO test in main()
	public static User findByEmail(@NonNull String email) throws SQLException {
		var rs = ORM.getInstance().select(TABLE_NAME, new String[0], "WHERE email =\"" + email + "\"");
		var users = getUsersFromResultSet(rs);
		if (users.length > 0) {
			return users[0];
		}
		return null;
	}

	public UserPermitionType getPermitionType(int usrId) throws SQLException {
		var rs = ORM.getInstance().select("SELECT " + "tp.title " + "FROM " + "permition_types tp "
				+ "INNER JOIN user_permition_relations  ON tp.permition_id = user_permition_relations.permition_id "
				+ "INNER JOIN users ON users.id = user_permition_relations.user_id " + "WHERE " + " users.id = " + usrId
				+ ";");
		if (rs.next()) {
			return UserPermitionType.valueOf(rs.getString("title").toUpperCase());
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(addUser("email", "pass", "name", UserPermitionType.CLIENT));
	}

}
