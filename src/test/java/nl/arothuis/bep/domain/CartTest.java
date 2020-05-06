package nl.arothuis.bep.domain;

import nl.arothuis.bep.domain.pricing.PricingStrategy;
import nl.arothuis.bep.domain.shipping.ShippingCostsStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartTest {
    @Test
    @DisplayName("calculates base price")
    void basePrice() {
        var pricingMock = mock(PricingStrategy.class);
        var shippingCostMock = mock(ShippingCostsStrategy.class);

        // Configure mocks to pass through base price (second argument)
        when(pricingMock.apply(any(), any()))
                .thenAnswer(invocation -> invocation.getArgument(1));
        when(shippingCostMock.apply(any(), any()))
                .thenAnswer(invocation -> invocation.getArgument(1));

        var customer = new Customer(false, 30, Region.Netherlands);
        var products = List.of(
                new Product("Studio lamp", 2, BigDecimal.valueOf(50)),
                new Product("PC", 1, BigDecimal.valueOf(250))
        );

        var cart = new Cart(customer, products, pricingMock, shippingCostMock);
        var totalPrice = cart.calculateTotalPrice();

        assertEquals(BigDecimal.valueOf(350), totalPrice);
    }
}
