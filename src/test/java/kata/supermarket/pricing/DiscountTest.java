package kata.supermarket.pricing;

import junit.framework.TestCase;
import kata.supermarket.model.Article;
import kata.supermarket.model.Item;
import org.junit.Assert;

public class DiscountTest extends TestCase {
    Discount discount;
    DiscountTool discountTool;
    Double delta = 0.00002;

    public void testDiscount() {
        discount = new Discount("SKU-000000001", 2, 1);
        discountTool = new DiscountTool();
        Article cup = new Article("SKU-000000001", "Cup", 1, 10);
        Item item = new Item(cup, 3);
        float expected = 20f;
        Assert.assertEquals(expected, discountTool.apply(item, discount), delta);
    }
}