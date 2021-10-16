package kata.supermarket.pricing;

import kata.supermarket.discount.Discount;
import kata.supermarket.discount.DiscountManager;
import kata.supermarket.discount.DiscountNotFoundException;
import kata.supermarket.model.Article;
import kata.supermarket.model.Item;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DiscountTest {
    Discount discount;
    DiscountManager discountManager;
    Double delta = 0.002;

    @Test
    public void testDiscount() {
        discount = new Discount("SKU-000000001", false, 0, 2, 1);
        discountManager = new DiscountManager();
        discountManager.addDiscount(discount);
        Article cup = new Article("SKU-000000001", "Cup", 1, 10);
        Item item = new Item(cup, 6);
        float expected = 40f;
        Assert.assertEquals(expected, discountManager.apply(item, discount), delta);
    }

    @Test
    public void testDiscount_NotFound() throws Exception {
        discount = new Discount("SKU-000000001", false, 0, 2, 1);
        discountManager = new DiscountManager();
        Article cup = new Article("SKU-000000001", "Cup", 1, 10);
        Item item = new Item(cup, 3);
        DiscountNotFoundException exception = Assert.assertThrows(DiscountNotFoundException.class, () -> discountManager.apply(item, discount));
        Assert.assertEquals("Discount does not exist", exception.getMessage());
    }

    @Test
    public void testDiscountPercentage() {
        discount = new Discount("SKU-000000001", false, 0, 2, 1);
        discountManager = new DiscountManager();
        float expected = 0.333f;
        Assert.assertEquals(expected, discountManager.computeDiscountPercentage(discount), delta);
    }

    @Test
    public void testDiscountApplicable() {
        discountManager = new DiscountManager();
        discount = new Discount("SKU-000000001", false, 0, 3, 1);
        discountManager.addDiscount(discount);
        Article cup = new Article("SKU-000000001", "Cup", 5, 10);
        Item item = new Item(cup, 5);
        Assert.assertTrue(discountManager.isDiscountApplicable(item, discount));
    }
}