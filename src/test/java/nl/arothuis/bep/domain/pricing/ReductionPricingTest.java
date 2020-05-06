package nl.arothuis.bep.domain.pricing;

import nl.arothuis.bep.domain.Customer;
import nl.arothuis.bep.domain.Region;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReductionPricingTest {
    @ParameterizedTest
    @MethodSource("reductionExamples")
    @DisplayName("calculate price with reduction based on customer")
    void calculateReduction(int price, boolean recentlyJoined, int age, int expectedPrice) {
        var customer = new Customer(recentlyJoined, age, Region.Netherlands);
        var basePrice = new BigDecimal(price);

        var pricing = new ReductionPricing();
        BigDecimal actualPrice = pricing.apply(customer, basePrice);

        assertEquals(new BigDecimal(expectedPrice), actualPrice);
    }

    static Stream<Arguments> reductionExamples() {
        return Stream.of(
                Arguments.of(1000, false, 64, 1000),
                Arguments.of(1000, true, 64, 950),
                Arguments.of(1000, false, 65, 900),
                Arguments.of(1000, true, 65, 850)
        );
    }
}

