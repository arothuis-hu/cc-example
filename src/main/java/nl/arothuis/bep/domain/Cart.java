package nl.arothuis.bep.domain;

import nl.arothuis.bep.domain.pricing.PricingStrategy;
import nl.arothuis.bep.domain.shipping.ShippingCostsStrategy;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private final Customer customer;
    private final List<Product> products;
    private final PricingStrategy pricing;
    private final ShippingCostsStrategy shippingCosts;

    public Cart(
            Customer customer,
            List<Product> products,
            PricingStrategy pricing,
            ShippingCostsStrategy shippingCosts
    ) {
        this.customer = customer;
        this.products = products;
        this.pricing = pricing;
        this.shippingCosts = shippingCosts;
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal basePrice = this.calculateBasePrice();
        BigDecimal reducedPrice = pricing.apply(this.customer, basePrice);
        return shippingCosts.apply(this.customer, reducedPrice);
    }

    private BigDecimal calculateBasePrice() {
        BigDecimal basePrice = BigDecimal.valueOf(0);

        for (Product product : this.products) {
            basePrice = basePrice.add(product.calculateTotalPrice());
        }

        return basePrice;
    }
}
