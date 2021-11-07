package kata.supermarket.service;

import kata.supermarket.model.Article;
import kata.supermarket.model.Item;
import lombok.Data;
import lombok.NonNull;

import java.util.*;

@Data
public class Cart {
    private Map<String, Item> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    /**
     * Add x=quantity article to cart
     *
     * @param article
     * @param quantity
     */
    void addToCart(Article article, float quantity) {
        if (items.containsKey(article.getSku())) {
            Item item = items.get(article.getSku());
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            Item item = new Item(article, quantity);
            items.put(article.getSku(), item);
        }
    }

    /**
     * remove one or many occurrences of one article in item list
     *
     * @param article
     * @param quantity
     */
    void removeFromCart(Article article, float quantity) {
        if (items.containsKey(article.getSku())) {
            Item item = items.get(article.getSku());
            final float diff = (int) (item.getQuantity() - quantity);
            if (diff > 0) {
                item.setQuantity(diff);
            } else if (diff == 0) {
                items.remove(article.getSku());
            }
        }
    }

    void clearCart() {
        this.items.clear();
    }

    boolean containsItem(@NonNull Item item) {
        String sku = item.getArticle().getSku();
        return Objects.nonNull(items.get(sku));
    }
}
