package kata.supermarket.pricing;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;

public interface IPrice {
    float computePrice(Item item);

    float getUnitaryPrice(Article cup);
}
