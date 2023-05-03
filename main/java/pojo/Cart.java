package pojo;

import java.sql.SQLException;
import java.util.ArrayList;

import lombok.Data;
import orm.gateways.CartGateway;

public class Cart {

    private final ArrayList<CartPosition> cartPositions = new ArrayList<>();

    @Data
    public class CartPosition {
        private final Good good;
        private final int count;

    }

    public Cart() throws SQLException {
        update();
    }

    public int getSumPrice() {
        int sum = 0;
        for (CartPosition cartPosition : cartPositions) {
            sum += cartPosition.getGood().getPrice() * cartPosition.getCount();
        }
        return sum;
    }

    public int getGoodCount() {
        int count = 0;
        for (CartPosition cartPosition : cartPositions) {
            count += cartPosition.getCount();
        }
        return count;
    }

    public CartPosition[] getPositions() {
        return cartPositions.toArray(new CartPosition[0]);
    }

    public void update() throws SQLException {
        Good[] goods = CartGateway.getAllGoods();
        cartPositions.clear();
        for (Good good : goods) {
            cartPositions.add(new CartPosition(good, CartGateway.getGoodCountById(good.getId())));
        }
    }

}
