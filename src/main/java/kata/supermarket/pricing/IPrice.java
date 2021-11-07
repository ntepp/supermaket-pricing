package kata.supermarket.pricing;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;

import java.math.BigDecimal;

public interface IPrice {
    BigDecimal computePrice(Item item);

    BigDecimal getUnitaryPrice(Article cup);
}
