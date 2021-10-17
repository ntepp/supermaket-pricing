package kata.supermarket.service;

import kata.supermarket.discount.Discount;
import kata.supermarket.discount.DiscountManager;
import kata.supermarket.model.Article;
import kata.supermarket.model.Cart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class SuperMarketManager {
    private static SuperMarketManager instance;
    private final List<Discount> discountList = new ArrayList<>();
    private final List<Article> articleList = new ArrayList<>();

    void addDiscount(Discount discount) {

    }

    void applyPromotion(Article article, Discount discount) {

    }

    void computeBill(Cart cart) {

    }

    public static SuperMarketManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SuperMarketManager();
        }
        return instance;
    }
}
