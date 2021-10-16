package kata.supermarket.pricing;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;

public class Price implements IPrice {

    @Override
    public float computePrice(Item item) {
        return getUnitaryPrice(item.getArticle()) * item.getQuantity();
    }

    @Override
    public float getUnitaryPrice(Article cup) {
        return cup.getPrice()/cup.getQuantity();
    }
}
