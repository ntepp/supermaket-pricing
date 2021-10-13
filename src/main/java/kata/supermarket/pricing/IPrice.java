package kata.supermarket.pricing;

import kata.supermarket.model.Item;

public interface IPrice {
    abstract float computePrice(Item item);
}
