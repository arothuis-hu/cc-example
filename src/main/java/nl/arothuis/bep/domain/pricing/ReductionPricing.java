package nl.arothuis.bep.domain.pricing;

import nl.arothuis.bep.domain.Customer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReductionPricing implements PricingStrategy {
    @Override
    public BigDecimal apply(Customer customer, BigDecimal basePrice) {
        var percentage = new BigDecimal(100);

        if (customer.hasRecentlyJoined()) {
            percentage = percentage.subtract(new BigDecimal(5));
        }

        if (customer.isSenior()) {
            percentage = percentage.subtract(new BigDecimal(10));
        }

        return basePrice
                .multiply(percentage)
                .divide(new BigDecimal(100), RoundingMode.CEILING);
    }
}
