package kata.supermarket.pricing;

import kata.supermarket.model.Item;

public class DiscountTool implements IDiscount {
    @Override
    public float apply(Item item, Discount discount) {
        return 0;
    }
}
