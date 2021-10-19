package kata.supermarket.discount;

import kata.supermarket.model.Item;

public interface IDiscount {
    float apply(Item item, Discount discount);

    float computeDiscountPercentage(Discount discount);

    boolean isDiscountApplicable(Item item, Discount discount);

    void addDiscount(String code, Discount discount);
}
