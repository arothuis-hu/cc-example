package nl.arothuis.bep.domain.pricing;

import nl.arothuis.bep.domain.Customer;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal apply(Customer customer, BigDecimal basePrice);
}
