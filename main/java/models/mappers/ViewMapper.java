package models.mappers;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import pojo.CartCardView;
import pojo.CartItem;
import pojo.CatalogCardView;
import pojo.Good;

@UtilityClass
public class ViewMapper {
	
	public CatalogCardView[] goodsToCatalogCardView(Good... goods) {
		if (goods == null) {
			return null;
		}
		CatalogCardView[] cards = new CatalogCardView[goods.length];
		for (int i = 0; i < goods.length; i++) {
			cards[i] = goodToCatalogCardView(goods[i]);
		}
		return cards;
	}
	
	public CatalogCardView goodToCatalogCardView(Good good) {
		var srcGoodPagePrefix = "main?good&id=";
		return new CatalogCardView(good.getImage(), 
							good.getImage(), 
							good.getTitle(),  
							srcGoodPagePrefix + good.getId(),
							"" + good.getPrice(),
							good.getInfo(),
							"goodToCart(" + good.getId() + ",1)",
							"В КАРЗИНУ",
							true);
	}
	
	public CartCardView[] cartItemsToCartCardViews(CartItem... items) {
		if (items == null) {
			return null;
		}
		CartCardView[] views = new CartCardView[items.length];
		for (int i = 0; i < views.length; i++) {
			views[i] = cartItemToCartCardView(items[i]);
		}
		return views;
	}

	private CartCardView cartItemToCartCardView(@NonNull CartItem item) {
		return new CartCardView(String.valueOf(item.getId()), 
								item.getGood().getTitle(), 
								item.getGood().getInfo(), 
								String.valueOf(item.getGood().getPrice()), 
								item.getGood().getImage(), 
								String.valueOf(item.getCount()));
	}
	
	
	
}
