package kata.supermarket.discount;

import kata.supermarket.model.Item;
import kata.supermarket.pricing.Price;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DiscountManager implements IDiscount {

    private final List<Discount> discountList = new ArrayList<>();

    @Override
    public float apply(Item item, Discount discount) {
        if (isDiscountApplicable(item, discount)) {
            Price price = new Price();
            float totalPrice = price.getUnitaryPrice(item.getArticle()) * item.getQuantity();
            float reduction;
            if (!discount.isByPercentage() && discount.getQuantityBought() > item.getQuantity()) {
                reduction = 0;
            } else {
                reduction = totalPrice * computeDiscountPercentage(discount);
            }
            return totalPrice - reduction;
        } else {
            throw new DiscountNotFoundException("Discount does not exist");
        }
    }

    @Override
    public float computeDiscountPercentage(@NonNull Discount discount) {
        final float sumQuantityFreeAndFree = discount.getQuantityBought() + discount.getQuantityFree();
        float totalCost = discount.getQuantityBought() + discount.getQuantityFree() * discount.getPercentage();
        float discountQte = sumQuantityFreeAndFree - totalCost;
        float percentage = discountQte / sumQuantityFreeAndFree;

        return discount.isByPercentage() ? discount.getPercentage() : percentage;
    }

    @Override
    public boolean isDiscountApplicable(Item item, Discount discount) {
        if (!discountList.isEmpty() && item.getQuantity() >= discount.getQuantityBought()) {
            return discountList.stream().anyMatch(d -> d.getSku().equalsIgnoreCase(item.getArticle().getSku()));
        }
        return false;
    }

    public void addDiscount(Discount discount) {
        discountList.add(discount);
    }
}
