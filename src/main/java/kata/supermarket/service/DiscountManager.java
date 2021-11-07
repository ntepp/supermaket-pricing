package kata.supermarket.service;

import kata.supermarket.discount.Discount;
import kata.supermarket.discount.DiscountNotFoundException;
import kata.supermarket.discount.IDiscount;
import kata.supermarket.model.Item;
import kata.supermarket.pricing.Price;
import lombok.NonNull;

import java.util.*;

public class DiscountManager implements IDiscount {
    private static DiscountManager instance;
    private final Map<String, Discount> discountMap = new HashMap<>();

    private DiscountManager() {
    }

    /**
     * Apply discount on Item
     *
     * @param item
     * @param discount
     * @return
     */
    @Override
    public float apply(@NonNull Item item, @NonNull Discount discount) {
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

    /**
     * Compute discount by percentage
     * @param discount
     * @return
     */
    @Override
    public float computeDiscountPercentage(@NonNull Discount discount) {
        final float sumQuantityFreeAndFree = discount.getQuantityBought() + discount.getQuantityFree();
        float totalCost = discount.getQuantityBought() + discount.getQuantityFree() * discount.getPercentage();
        float discountQte = sumQuantityFreeAndFree - totalCost;
        float percentage = discountQte / sumQuantityFreeAndFree;

        return discount.isByPercentage() ? discount.getPercentage() : percentage;
    }

    @Override
    public boolean isDiscountApplicable(@NonNull Item item, @NonNull Discount discount) {
        return Optional.ofNullable(discountMap.get(item.getArticle().getDiscountCode())).isPresent();
    }

    @Override
    public void addDiscount(String code, Discount discount) {
        discountMap.put(code, discount);
    }


    public void clear() {
        discountMap.clear();
    }

    public Optional<Discount> getDiscountFromCode(String discountCode) {
        return Optional.ofNullable(discountMap.get(discountCode));
    }

    public static DiscountManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DiscountManager();
        }
        return instance;
    }
}
