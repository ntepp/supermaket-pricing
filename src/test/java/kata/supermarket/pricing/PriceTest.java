package kata.supermarket.pricing;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;
import org.junit.Assert;
import org.junit.Test;

public class PriceTest {
    Price price;
    Double delta = 0.00002;

    @Test
    public void testComputePriceDefault() {
        price = new Price();
        Article pen = new Article("SKU-000000001", "Pen", 1, 2.5f);
        Item item = new Item(pen, 3);
        float expected = 7.5f;
        Assert.assertEquals(expected, price.computePrice(item), delta);

    }

    @Test
    public void testComputePricePackage() {
        price = new Price();
        Article cup = new Article("SKU-000000001", "Cup", 3, 1);
        Item item = new Item(cup, 6);
        float expected = 2f;
        Assert.assertEquals(expected, price.computePrice(item), delta);
    }

    @Test
    public void testGetUnitaryPrice() {
        price = new Price();
        Article cup = new Article("SKU-000000001", "Cup", 3, 12);
        float expected = 4f;
        Assert.assertEquals(expected, price.getUnitaryPrice(cup), delta);
    }
}