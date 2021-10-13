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

}
