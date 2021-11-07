package kata.supermarket.service;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;
import kata.supermarket.service.Cart;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartTest {
    public static final int QUANTITY_ONE = 1;
    public static final int QUANTITY_THREE = 3;
    private final Cart cart = new Cart();

    @Test
    public void addToCart_DifferentArticleType() {
        Article pen = new Article("SKU-000000001", "Pen", QUANTITY_ONE, 2.5f);
        Article book = new Article("SKU-000000002", "Book", QUANTITY_ONE, 50f);
        Item item1 = new Item(pen, QUANTITY_THREE);
        Item item2 = new Item(book, QUANTITY_ONE);

        cart.addToCart(pen, QUANTITY_THREE);
        cart.addToCart(book, QUANTITY_ONE);

        // The two articles must be added to the cart
        Assert.assertTrue(Boolean.logicalAnd(cart.containsItem(item1), cart.containsItem(item2)));
    }

    @Test
    public void addToCart_SameArticle() {
        Article pen = new Article("SKU-000000001", "Pen", QUANTITY_ONE, 2.5f);
        Article book = new Article("SKU-000000002", "Book", QUANTITY_ONE, 50f);
        Item expectedItem = new Item(pen, 4);
        Item item2 = new Item(book, QUANTITY_ONE);

        cart.addToCart(pen, QUANTITY_THREE);
        cart.addToCart(book, QUANTITY_ONE);
        cart.addToCart(pen, QUANTITY_ONE);

        // The two articles must be added to the cart. The quantity of pens is equals to QUANTITY_THREE + QUANTITY_ONE
        Assert.assertTrue(Boolean.logicalAnd(cart.containsItem(expectedItem), cart.containsItem(item2)));
    }

    @Test
    public void removeFromCart_ReduceQuantityForArticle() {
        final double delta = 0.0002;
        Article pen = new Article("SKU-000000001", "Pen", QUANTITY_ONE, 2.5f);
        Article book = new Article("SKU-000000002", "Book", QUANTITY_ONE, 50f);

        cart.addToCart(pen, QUANTITY_THREE);
        cart.addToCart(book, QUANTITY_ONE);
        cart.removeFromCart(pen, QUANTITY_ONE);

        Item actualItem = cart.getItems().get(pen.getSku());

        Assert.assertEquals(2, actualItem.getQuantity(),delta);
    }

    @Test
    public void removeFromCart() {
        Article pen = new Article("SKU-000000001", "Pen", QUANTITY_ONE, 2.5f);
        Article book = new Article("SKU-000000002", "Book", QUANTITY_ONE, 50f);

        cart.addToCart(pen, QUANTITY_THREE);
        cart.addToCart(book, QUANTITY_ONE);
        cart.removeFromCart(pen, QUANTITY_THREE);

        Item actualItem = cart.getItems().get(pen.getSku());

        Assert.assertNull(actualItem);
    }
    @Test
    public void clearCart() {
        Article pen = new Article("SKU-000000001", "Pen", QUANTITY_ONE, 2.5f);
        Article book = new Article("SKU-000000002", "Book", QUANTITY_ONE, 60f);

        cart.addToCart(pen, QUANTITY_THREE);
        cart.addToCart(book, QUANTITY_ONE);
        cart.clearCart();
        assertTrue(cart.getItems().isEmpty());
    }
}