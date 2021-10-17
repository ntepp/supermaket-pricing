package kata.supermarket.service;

import kata.supermarket.discount.Discount;
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
        discountList.add(discount);
    }

    void applyPromotion(Article article, Discount discount) {
        // TODO: apply discount to article
    }

    void computeBill(Cart cart) {
        // TODO: comput bill from cart
    }

    public static SuperMarketManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SuperMarketManager();
        }
        return instance;
    }
}
