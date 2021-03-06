package kata.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class Article {
    private String sku;
    private String name;
    private float quantity;
    private BigDecimal price;
    private String discountCode;

    public Article(String sku, String name, float quantity, BigDecimal price) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
