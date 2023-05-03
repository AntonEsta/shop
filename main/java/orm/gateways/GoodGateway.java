package orm.gateways;

import pojo.Good;
import orm.ORM;
import lombok.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.catalina.tribes.util.Arrays;

public class GoodGateway {

    private static final String TABLE_NAME = "goods";

    public static int addGood(@NonNull String title, int price, @NonNull String info) throws SQLException {
        var valuesMap = new HashMap<String, String>();
        valuesMap.put("title", title);
        valuesMap.put("price", String.valueOf(price));
        valuesMap.put("info", info);
        return ORM.getInstance().insert(TABLE_NAME, valuesMap);

    }

    public static Good[] getAllGoods() throws SQLException {
    	var rs = ORM.getInstance().select(TABLE_NAME, new String[0], "");
        return getGoodsFromResultSet(rs);
    }


    public static Good findById(int goodId) throws SQLException {
        var rs = ORM.getInstance().select(TABLE_NAME, new String[0], "WHERE good_id =" + goodId);
        var goods = getGoodsFromResultSet(rs);
        if (goods.length > 0) {
            return goods[0];
        }
        return null;
    }
    
    public static Good[] findByCategory(int catId) throws SQLException {
        var sql = "SELECT * FROM goods INNER JOIN good_category_relations ON goods.good_id = good_category_relations.good_id WHERE good_category_relations.category_id = "+ catId;
    	var rs = ORM.getInstance().select(sql);
        var goods = getGoodsFromResultSet(rs);
        System.out.println(Arrays.toString(goods));
        return goods;
    }

    private static Good[] getGoodsFromResultSet(@NonNull ResultSet rs) throws SQLException {
        var goods = new ArrayList<>();
        while (rs.next()) {
            goods.add(new Good(
                    rs.getInt("good_id"),
                    rs.getString("title"),
                    rs.getInt("price"),
                    rs.getString("info"),
            		rs.getString("image")));
        }
        return goods.toArray(new Good[0]);
    }

}
