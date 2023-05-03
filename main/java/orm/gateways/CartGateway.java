package orm.gateways;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pojo.CartItem;
import pojo.Good;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import orm.ORM;

@UtilityClass
public class CartGateway {

    private static final String TABLE_NAME = "cart";

    public static int addGood(int usrId, int goodId) throws SQLException {
        return addGood(usrId, goodId, 1);
    }

    public static int addGood(int usrId, int goodId, int goodCount) throws SQLException {
        var valuesMap = new HashMap<String, String>(3);
        valuesMap.put("user_id", String.valueOf(usrId));
        valuesMap.put("good_id", String.valueOf(goodId));
        valuesMap.put("count", "count+" + goodCount);
        int resCount = ORM.getInstance().update(TABLE_NAME, valuesMap, "WHERE `good_id` = " + goodId);
        if (resCount == 0) {
            valuesMap.remove("count");
            return ORM.getInstance().insert(TABLE_NAME, valuesMap);
        } else {
            return resCount;
        }
    }

    public static @NonNull Good[] getAllGoods() throws SQLException {
        var goodIds = ORM.getInstance().select(TABLE_NAME, new String[]{"good_id"}, "");
        List<Good> goods = new ArrayList<>();
        while (!goodIds.isClosed() && goodIds.next()) {
            goods.add(GoodGateway.findById(goodIds.getInt("good_id")));
        }
        return goods.toArray(new Good[0]);
    }

    public CartItem[] getCart(int usrId) throws SQLException {
    	var rs = ORM.getInstance().select(TABLE_NAME, null, "WHERE user_id="+usrId);
    	var items = new ArrayList<CartItem>();
    	while (rs.next()) {
    		Good good = GoodGateway.findById(rs.getInt("good_id"));
    		items.add(new CartItem(rs.getInt("id"), good, rs.getInt("count")));
    	}
    	return items.toArray(new CartItem[0]);
    }
    
    public static int getGoodCountById(int goodId) throws SQLException {
        var rs = ORM.getInstance().select(TABLE_NAME, new String[]{"count"}, "WHERE `good_id` = " + goodId);
        rs.next();
        return rs.getInt("count");
    }
}
