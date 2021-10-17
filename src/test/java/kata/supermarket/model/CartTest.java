package kata.supermarket.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CartTest {
    public static final int NUMBER_OF_ITEMS = 2;
    Cart cart = new Cart();

    @Test
    public void addToCart_should_add_one_item() {
        Article pen = new Article("SKU-000000001", "Pen", 1, 2.5f);
        Article book = new Article("SKU-000000002", "Book", 1, 50f);
        Item item1 = new Item(pen, 3);
        Item item2 = new Item(book, 1);

        cart.addToCart(pen, 3);
        cart.addToCart(book, 1);

        Assert.assertTrue(Boolean.logicalAnd(cart.containsItem(item1), cart.containsItem(item2)));
    }

    @Test
    public void addToCart_should_update_quantity_for_same_article() {
        Article pen = new Article("SKU-000000001", "Pen", 1, 2.5f);
        Article book = new Article("SKU-000000002", "Book", 1, 50f);
        Item expectedItem = new Item(pen, 4);
        Item item2 = new Item(book, 1);

        cart.addToCart(pen, 3);
        cart.addToCart(book, 1);
        cart.addToCart(pen, 1);

        Assert.assertTrue(Boolean.logicalAnd(cart.containsItem(expectedItem), cart.containsItem(item2)));
    }

    @Test
    public void removeFromCart() {
        Article pen = new Article("SKU-000000001", "Pen", 1, 2.5f);
        Article book = new Article("SKU-000000002", "Book", 1, 50f);
        Item item1 = new Item(pen, 2);

        cart.addToCart(pen, 3);
        cart.addToCart(book, 1);
        cart.removeFromCart(pen, 1);
        Assert.assertFalse(cart.containsItem(item1));
    }

    @Test
    public void clearCart() {
        Article pen = new Article("SKU-000000001", "Pen", 1, 2.5f);
        Article book = new Article("SKU-000000002", "Book", 1, 60f);

        cart.addToCart(pen, 3);
        cart.addToCart(book, 1);
        cart.clearCart();
        assertTrue(cart.getItems().isEmpty());
    }
}