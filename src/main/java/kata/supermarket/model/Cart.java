package kata.supermarket.model;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    void addToCart(Article article, float quantity) {

    }

    void removeFromCart(Article article, float quantity) {

    }

    void clearCart() {

    }

    boolean containsItem(Item item) {
        return false;
    }
}
