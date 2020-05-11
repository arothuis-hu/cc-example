package nl.arothuis.bep.domain.shipping;

import nl.arothuis.bep.domain.Customer;
import nl.arothuis.bep.domain.Region;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShippingCostsTest {
    @ParameterizedTest
    @MethodSource("shippingCostsExamples")
    @DisplayName("calculate price with shipping costs based on customer's region")
    void calculateReductionForRegion(int price, Region region, int expectedPrice) {
        var customer = new Customer(false, 64, region);
        var basePrice = new BigDecimal(price);

        var shippingCosts = new ShippingCosts();
        BigDecimal actualPrice = shippingCosts.apply(customer, basePrice);

        assertEquals(new BigDecimal(expectedPrice), actualPrice);
    }

    static Stream<Arguments> shippingCostsExamples() {
        return Stream.of(
                Arguments.of(1000, Region.Netherlands, 1005),
                Arguments.of(1000, Region.Europe, 1010),
                Arguments.of(1000, Region.International, 1015)
        );
    }
}
