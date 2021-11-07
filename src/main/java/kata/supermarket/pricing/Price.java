package kata.supermarket.pricing;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Price implements IPrice {

    @Override
    public BigDecimal computePrice(Item item) {
        return getUnitaryPrice(item.getArticle()).multiply(BigDecimal.valueOf(item.getQuantity()));
    }

    @Override
    public BigDecimal getUnitaryPrice(Article cup) {
        return cup.getPrice().divide(BigDecimal.valueOf(cup.getQuantity()),2, RoundingMode.HALF_UP);
    }
}
