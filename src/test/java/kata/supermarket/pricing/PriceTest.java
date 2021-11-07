package kata.supermarket.pricing;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PriceTest {
    private Price price;
    private final static Double DELTA = 0.02;

    @Test
    public void testComputePriceDefault() {
        price = new Price();
        Article pen = new Article("SKU-000000001", "Pen", 1, BigDecimal.valueOf(2.5f));
        Item item = new Item(pen, 3);
        BigDecimal expected = BigDecimal.valueOf(7.5);
        Assert.assertEquals(expected.floatValue(), price.computePrice(item).floatValue(), DELTA);

    }

    @Test
    public void testComputePricePackage() {
        price = new Price();
        Article cup = new Article("SKU-000000001", "Cup", 3, BigDecimal.valueOf(1));
        Item item = new Item(cup, 6);
        BigDecimal expected = BigDecimal.valueOf(2);
        Assert.assertEquals(expected.floatValue(), price.computePrice(item).floatValue(), DELTA);
    }

    @Test
    public void testGetUnitaryPrice() {
        price = new Price();
        Article cup = new Article("SKU-000000001", "Cup", 3, BigDecimal.valueOf(12));
        BigDecimal expected = BigDecimal.valueOf(4);
        Assert.assertEquals(expected.floatValue(),price.getUnitaryPrice(cup).floatValue(), DELTA);
    }
}