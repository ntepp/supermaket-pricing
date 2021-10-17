package kata.supermarket.discount;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Discount {
    private String code;
    private boolean byPercentage;
    private float percentage;
    private float quantityBought;
    private float quantityFree;
}
