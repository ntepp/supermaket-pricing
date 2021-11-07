package kata.supermarket.service;

import kata.supermarket.discount.Discount;
import kata.supermarket.model.Article;
import kata.supermarket.model.Item;
import kata.supermarket.pricing.Price;
import lombok.Data;

import java.util.*;

@Data
public class SuperMarketManager {
    private static SuperMarketManager instance;
    DiscountManager discountManager = DiscountManager.getInstance();
    Price price = new Price();
    private final List<Article> articleList = new ArrayList<>();

    /**
     * compute bill for the given card
     * @param cart
     * @return
     */
    float computeBill(Cart cart) {
        Map<String, Item> items = cart.getItems();
        float sum = 0;
        return items.entrySet().stream().map(item -> {
            Optional<Discount> discount = discountManager.getDiscountFromCode(item.getValue().getArticle().getDiscountCode());
            if(discount.isPresent()){
                return discountManager.apply(item.getValue(), discount.get());
            }else {
                return price.computePrice(item.getValue());
            }
        }).reduce(sum, (a,b) -> a+b);
    }

    public static SuperMarketManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SuperMarketManager();
        }
        return instance;
    }
}
