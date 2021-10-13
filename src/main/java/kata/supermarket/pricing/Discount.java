package kata.supermarket.pricing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Discount {
    private String sku;
    private float quantity;
    private float quantityFree;
}
