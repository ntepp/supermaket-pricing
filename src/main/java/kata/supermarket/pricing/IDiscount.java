package kata.supermarket.pricing;

import kata.supermarket.model.Item;

public interface IDiscount {
    float apply(Item item, Discount discount);
}
