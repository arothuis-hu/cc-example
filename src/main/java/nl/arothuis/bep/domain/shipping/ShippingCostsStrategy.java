package nl.arothuis.bep.domain.shipping;

import nl.arothuis.bep.domain.Customer;

import java.math.BigDecimal;

public interface ShippingCostsStrategy {
    BigDecimal apply(Customer customer, BigDecimal basePrice);
}
