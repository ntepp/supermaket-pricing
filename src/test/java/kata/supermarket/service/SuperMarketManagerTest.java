package kata.supermarket.service;

import kata.supermarket.discount.Discount;
import kata.supermarket.model.Article;
import org.junit.Assert;
import org.junit.Test;

public class SuperMarketManagerTest {
    private static final float QUANTITY_ONE = 1;
    private static final float QUANTITY_TWO = 2;
    public static final String PROMO_001 = "PROMO_001";
    private static final float QUANTITY_THREE = 3;
    public static final SuperMarketManager superMarket = SuperMarketManager.getInstance();
    private Cart cart;
    final SuperMarketManager instance = SuperMarketManager.getInstance();
    DiscountManager discountManager = DiscountManager.getInstance();

    @Test
    public void computeBill_withoutDiscount() {
        Article pen = new Article("SKU-000000001", "Pen", QUANTITY_ONE, 2.5f);
        Article book = new Article("SKU-000000002", "Book", QUANTITY_ONE, 50f);

        cart = new Cart();
        cart.addToCart(pen, QUANTITY_TWO);
        cart.addToCart(book,QUANTITY_ONE);

        float totalPrice = instance.computeBill(cart);
        Assert.assertEquals(55, totalPrice, 0.0002);
    }

    @Test
    public void computeBill_withDiscountPercentage() {
        Article pen = new Article("SKU-000000001", "Pen", QUANTITY_ONE, 2.5f);
        Article book = new Article("SKU-000000002", "Book", QUANTITY_ONE, 50f);
        Discount discount = new Discount(PROMO_001,true,0.5f,0,0);
        book.setDiscountCode(PROMO_001);
        discountManager.clear();
        discountManager.addDiscount(PROMO_001, discount);

        cart = new Cart();
        cart.addToCart(pen, QUANTITY_TWO);
        cart.addToCart(book,QUANTITY_ONE);
        float totalPrice = superMarket.computeBill(cart);
        Assert.assertEquals(30, totalPrice, 0.0002);
    }

    // Test buy 2, get 1. Test when customer buy 1
    @Test
    public void computeBill_withoutDiscountPackaged_0() {
        Article pen = new Article("SKU-000000001", "Pen", QUANTITY_ONE, 2.5f);
        Article book = new Article("SKU-000000002", "Book", QUANTITY_ONE, 50f);
        Discount discount = new Discount(PROMO_001,false,0,2,1);
        book.setDiscountCode(PROMO_001);
        discountManager.clear();
        discountManager.addDiscount(PROMO_001, discount);

        cart = new Cart();
        cart.addToCart(pen, QUANTITY_THREE);
        cart.addToCart(book,QUANTITY_ONE);
        float totalPrice = superMarket.computeBill(cart);
        Assert.assertEquals(57.5, totalPrice, 0.0002);
    }

    // Test buy 2, get 1. Test when customer buy more than 2
    @Test
    public void computeBill_withoutDiscountPackaged() {
        Article pen = new Article("SKU-000000001", "Pen", QUANTITY_ONE, 2.5f);
        Article book = new Article("SKU-000000002", "Book", QUANTITY_ONE, 50f);
        Discount discount = new Discount(PROMO_001,false,0,2,1);
        book.setDiscountCode(PROMO_001);
        discountManager.clear();
        discountManager.addDiscount(PROMO_001, discount);

        cart = new Cart();
        cart.addToCart(pen, QUANTITY_THREE);
        cart.addToCart(book,QUANTITY_THREE);
        float totalPrice = SuperMarketManager.getInstance().computeBill(cart);
        Assert.assertEquals(107.5, totalPrice, 0.0002);
    }
}