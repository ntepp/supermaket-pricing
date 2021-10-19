package kata.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class Article {
    private String sku;
    private String name;
    private float quantity;
    private float price;
    private String discountCode;

    public Article(String sku, String name, float quantity, float price) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
