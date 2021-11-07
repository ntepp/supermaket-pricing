package kata.supermarket.discount;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;
import kata.supermarket.service.DiscountManager;
import org.junit.Assert;
import org.junit.Test;

public class DiscountTest {
    public static final String SKU_000000001 = "SKU-000000001";
    public static final String XMASS = "NOEL";
    public static final String CUP = "Cup";
    private Discount discount;
    private DiscountManager discountManager;
    private Double delta = 0.002;

    @Test
    public void testDiscount() {
        discount = new Discount(XMASS, false, 0, 2, 1);
        discountManager = DiscountManager.getInstance();
        discountManager.addDiscount(XMASS, discount);
        Article cup = new Article(SKU_000000001, CUP, 1, 10);
        cup.setDiscountCode(XMASS);
        Item item = new Item(cup, 6);
        float expected = 40f;
        Assert.assertEquals(expected, discountManager.apply(item, discount), delta);
    }

    @Test
    public void testDiscount_NotFound() throws Exception {
        discount = new Discount(SKU_000000001, false, 0, 2, 1);
        discountManager = DiscountManager.getInstance();
        Article cup = new Article(SKU_000000001, CUP, 1, 10);
        Item item = new Item(cup, 3);
        DiscountNotFoundException exception = Assert.assertThrows(DiscountNotFoundException.class, () -> discountManager.apply(item, discount));
        Assert.assertEquals("Discount does not exist", exception.getMessage());
    }

    @Test
    public void testDiscountPercentage() {
        discount = new Discount(SKU_000000001, false, 0, 2, 1);
        discountManager = DiscountManager.getInstance();
        float expected = 0.333f;
        Assert.assertEquals(expected, discountManager.computeDiscountPercentage(discount), delta);
    }

    @Test
    public void testDiscountApplicable() {
        discountManager = DiscountManager.getInstance();
        discount = new Discount("HAPPY_NEW_YEAR", false, 0, 3, 1);
        discountManager.addDiscount(discount.getCode(), discount);
        Article cup = new Article(SKU_000000001, CUP, 5, 10, "HAPPY_NEW_YEAR");
        Item item = new Item(cup, 5);
        Assert.assertTrue(discountManager.isDiscountApplicable(item, discount));
    }
}