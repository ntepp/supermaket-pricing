package kata.supermarket.discount;

import kata.supermarket.model.Item;

import java.math.BigDecimal;

public interface IDiscount {
    BigDecimal apply(Item item, Discount discount);

    float computeDiscountPercentage(Discount discount);

    boolean isDiscountApplicable(Item item, Discount discount);

    void addDiscount(String code, Discount discount);
}
