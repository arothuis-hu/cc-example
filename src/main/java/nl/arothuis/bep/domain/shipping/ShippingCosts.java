package nl.arothuis.bep.domain.shipping;

import nl.arothuis.bep.domain.Customer;

import java.math.BigDecimal;

public class ShippingCosts implements ShippingCostsStrategy {
    @Override
    public BigDecimal apply(Customer customer, BigDecimal basePrice) {
        if (customer.livesInTheNetherlands()) {
            return basePrice.add(new BigDecimal(5));
        }

        if (customer.livesOutsideEurope()) {
            return basePrice.add(new BigDecimal(15));
        }

        return basePrice.add(new BigDecimal(10));
    }
}
