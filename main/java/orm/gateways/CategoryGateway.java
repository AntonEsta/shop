package orm.gateways;

import java.sql.SQLException;

import orm.ORM;
import pojo.Category;
import java.util.List;

import lombok.experimental.UtilityClass;
import java.util.ArrayList;
import java.util.Arrays;

@UtilityClass
public class CategoryGateway {

	public Category findById(int id) throws SQLException {
		if (id > 0) {
			var rs = ORM.getInstance().select("categories", null, "WHERE category_id=" + id);
			if (rs.next()) {
				return new Category(rs.getInt("category_id"), rs.getString("title"), getSubcategories(rs.getInt("parent")));
			}
		}
		return null;
	}
	
	public Category[] getAllCategories() throws SQLException {
		List<Category> list = new ArrayList<>();
		var rs = ORM.getInstance().select("categories", null, "WHERE parent IS NULL");
		while (rs.next()) {
			
			list.add(new Category(rs.getInt("category_id"),
									rs.getString("title"),
									getSubcategories(rs.getInt("category_id"))));
		}
		
		return list.toArray(new Category[0]);
	}
	
	public Category[] getSubcategories(int parrentId) throws SQLException {
		List<Category> list = new ArrayList<>();
		var rs = ORM.getInstance().select("categories", null, "WHERE parent=" + parrentId);
		while (rs.next()) {
			list.add(new Category(rs.getInt("category_id"), rs.getString("title"), getSubcategories(rs.getInt("category_id"))));
		}
		return list.toArray(new Category[0]);
	}
	
	public static void main(String[] args) throws SQLException {
//		Util.showSystemProperties();
		var c = CategoryGateway.getAllCategories();
		System.out.println("main/narray-size: " + c.length);
		System.out.println(Arrays.toString(c));
	}
	
}
